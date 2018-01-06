package de.htwg.moco.truckparkserver.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.model.ParkingLotHistory;
import de.htwg.moco.truckparkserver.persistence.ParkingLotsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class PredictionsService {
    @Autowired
    ParkingLotsRepository parkingLotsRepository;

    @Value("${predictions.history.createMockData}")
    boolean createMockData;

    /**
     * builds the history data
     * creates mock data if enabled in config and usage reported is zero
     */
    public void history() {
        List<ParkingLot> parkingLots = parkingLotsRepository.getParkingLots();
        DateTime timeSlot = DateTime.now().withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

        parkingLots.forEach(parkingLot -> {
            int usage = parkingLot.getDevicesAtParkingArea().size();
            if (createMockData && usage == 0) usage = new Random().nextInt(parkingLot.getMaxParkingLots());
            ParkingLotHistory.History history = new ParkingLotHistory.History(timeSlot.getMillis(), usage);

            Map<String, ParkingLotHistory.History> historyMap = new HashMap<>();
            historyMap.put(timeSlot.toString(), history);

            ParkingLotHistory parkingLotHistory = new ParkingLotHistory(parkingLot.getName(), historyMap);

            parkingLotsRepository.addParkingLotHistory(parkingLotHistory);
        });
    }

    /**
     * calculates prediction, prediction average and predictionLinearRegression
     */
    public void calc() {
        List<ParkingLotHistory> parkingLotHistories = parkingLotsRepository.getParkingLotHistories();
        parkingLotHistories.forEach(parkingLotHistory -> {
            Map<String, List<Integer>> prediction = new HashMap<>();
            Map<String, Integer> predictionAvg = new HashMap<>();
            Map<String, Integer> predictionLinearRegression = new HashMap<>();

            String parkingLot = parkingLotHistory.getName();
            parkingLotHistory.getHistory().forEach((string, history) -> {
                ParkingLot parkingLotObject = parkingLotsRepository.getParkingLot(parkingLot);
                int usage = history.getUsage();
                DateTime dateTime = new DateTime().withMillis(history.getMillis());
                String dayOfWeek = dateTime.dayOfWeek().getAsShortText(Locale.ENGLISH);
                String hourOfDay = dateTime.hourOfDay().getAsShortText(Locale.ENGLISH);
                String key = dayOfWeek + "_" + hourOfDay;

                //build list of history values paired by dayOfWeek and hourOfDay
                if (prediction.containsKey(key)) {
                    List<Integer> list = prediction.get(key);
                    list.add(usage);
                    prediction.put(key, list);
                } else {
                    prediction.put(key, new ArrayList<>(Arrays.asList(usage)));
                }

                //calc average
                int avg = (int) Math.round(prediction.get(key).stream().mapToInt(Integer::intValue).average().getAsDouble());
                predictionAvg.put(key, avg);

                //calc linearRegression
                SimpleRegression simpleRegression = new SimpleRegression(true);
                List<Integer> predictions = prediction.get(key);
                double[][] data;
                if(predictions != null){

                    data = new double[predictions.size()][2];
                    for(int i = 0; i < data.length; i++){
                        data[i][0] = i+1;
                        data[i][1] = predictions.get(i);
                    }

                    simpleRegression.addData(data);
                    simpleRegression.getSlope();
                    simpleRegression.getIntercept();
                    //predict occupation for the next week at same time
                    double predict = simpleRegression.predict(predictions.size()+1);

                    //predict could be "not a number" if predictions size is < 2
                    if(!Double.isNaN(predict)){
                        if(predict < 0){
                            predictionLinearRegression.put(key, 0);
                        } else if (predict > parkingLotObject.getMaxParkingLots()){
                            predictionLinearRegression.put(key, parkingLotObject.getMaxParkingLots());
                        } else {
                            predictionLinearRegression.put(key, (int)Math.round(predict));
                        }
                    } else {
                        predictionLinearRegression.put(key, predictions.get(0));
                    }
                }
            });

            ApiFuture<WriteResult> result = parkingLotsRepository.addPrediction(parkingLot, prediction);
            try {
                System.out.println("addPrediction - "+parkingLot+" : "+result.get().getUpdateTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            result = parkingLotsRepository.addPredictionAvg(parkingLot, predictionAvg);
            try {
                System.out.println("addPredictionAvg - "+parkingLot+" : "+result.get().getUpdateTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            result = parkingLotsRepository.addPredictionLinearRegression(parkingLot, predictionLinearRegression);
            try {
                System.out.println("addPredictionLinearRegression - "+parkingLot+" : "+result.get().getUpdateTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });
    }
}
