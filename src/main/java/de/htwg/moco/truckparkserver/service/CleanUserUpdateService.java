package de.htwg.moco.truckparkserver.service;

import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.persistence.ParkingLotsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class CleanUserUpdateService {

    @Autowired
    ParkingLotsRepository parkingLotsRepository;

    //after which duration manual entries get deleted
    private Duration duration = Duration.ofHours(9); //9h = reduced daily resting time, unlikely

    /**
     * deletes all manually-user-added "devices" after defined duration of time
     * this in necessary because user-added entries are trucks of non-app-users and we can't know when they leave/left
     */
    public void clean() {
        List<String> lotIds = parkingLotsRepository.getParkingLotIds();

        for (String lotId : lotIds) {
            ParkingLot parkingLot = parkingLotsRepository.getParkingLot(lotId);
            List<String> devices = parkingLot.getDevicesAtParkingArea();

            devices.removeIf(device -> {
                String[] parts = device.split("\\.");

                if (parts.length == 2) {
                    long millisParkedAt = Long.parseLong(parts[1]);
                    return Instant.ofEpochMilli(millisParkedAt).plus(duration).isBefore(Instant.now());
                } else {
                    return false;
                }
            });
            parkingLot.setDevicesAtParkingArea(devices);
            parkingLotsRepository.addParkingLot(parkingLot);
        }
    }
}
