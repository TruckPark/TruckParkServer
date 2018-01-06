package de.htwg.moco.truckparkserver.service;

import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.model.ParkingLotHistory;
import de.htwg.moco.truckparkserver.persistence.ParkingLotsRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PredictionsService {
    @Autowired
    ParkingLotsRepository parkingLotsRepository;

    public void history() {
        List<ParkingLot> parkingLots = parkingLotsRepository.getParkingLots();
        DateTime timeSlot = DateTime.now().withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

        parkingLots.forEach(parkingLot -> {
            ParkingLotHistory.History history = new ParkingLotHistory.History(timeSlot, parkingLot.getDevicesAtParkingArea().size());

            Map<String, ParkingLotHistory.History> historyMap = new HashMap<>();
            historyMap.put(timeSlot.toString(), history);

            ParkingLotHistory parkingLotHistory = new ParkingLotHistory(parkingLot.getName(), historyMap);

            parkingLotsRepository.addParkingLotHistory(parkingLotHistory);
        });
    }

    public void calc() {

    }
}
