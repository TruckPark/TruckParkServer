package de.htwg.moco.truckparkserver.service;

import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.model.ParkingLotHistory;
import de.htwg.moco.truckparkserver.persistence.ParkingLotsRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class PredictionsService {
    @Autowired
    ParkingLotsRepository parkingLotsRepository;

    @Value("${predictions.history.createMockData}")
    boolean createMockData;

    public void history() {
        List<ParkingLot> parkingLots = parkingLotsRepository.getParkingLots();
        DateTime timeSlot = DateTime.now().withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

        parkingLots.forEach(parkingLot -> {
            int usage = parkingLot.getDevicesAtParkingArea().size();
            if (createMockData && usage == 1) usage = new Random().nextInt(parkingLot.getMaxParkingLots());
            ParkingLotHistory.History history = new ParkingLotHistory.History(timeSlot.getMillis(), usage);

            Map<String, ParkingLotHistory.History> historyMap = new HashMap<>();
            historyMap.put(timeSlot.toString(), history);

            ParkingLotHistory parkingLotHistory = new ParkingLotHistory(parkingLot.getName(), historyMap);

            parkingLotsRepository.addParkingLotHistory(parkingLotHistory);
        });
    }

    public void calc() {
        List<ParkingLotHistory> parkingLotHistories = parkingLotsRepository.getParkingLotHistories();
        Map<String, List<Integer>> prediction = new HashMap<>();
        parkingLotHistories.forEach(parkingLotHistory -> {

            String parkingLot = parkingLotHistory.getName();
            parkingLotHistory.getHistory().forEach((string, history) -> {
                int usage = history.getUsage();
                DateTime dateTime = new DateTime().withMillis(history.getMillis());
                String dayOfWeek = dateTime.dayOfWeek().getAsShortText(Locale.ENGLISH);
                String hourOfDay = dateTime.hourOfDay().getAsShortText(Locale.ENGLISH);
                String key = dayOfWeek + "_" + hourOfDay;

                if (prediction.containsKey(key)) {
                    List<Integer> list = prediction.get(key);
                    list.add(usage);
                    prediction.put(key, list);
                } else {
                    prediction.put(key, new ArrayList<>(Arrays.asList(usage)));
                }

            });

            parkingLotsRepository.addPrediction(parkingLot, prediction);
        });
    }
}
