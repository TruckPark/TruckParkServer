package de.htwg.moco.truckparkserver.controller;

import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.persistence.FirestoreRepository;
import de.htwg.moco.truckparkserver.service.FirestoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class Controller {

    @Resource
    FirestoreService firestoreService;

    @Resource
    FirestoreRepository firestoreRepository;

    @GetMapping("/online")
    public String online() {
        return "Online";
    }

    @GetMapping("/testGet")
    public void testFirestoreGet() {
        //firestoreService.getTest();
    }

    @GetMapping("/testAdd")
    public void testFirestoreAdd() {
        ParkingLot parkingLotHtwgKonstanz = new ParkingLot(40,
                new com.google.maps.model.LatLng(47.668340, 9.169379),
                new com.google.maps.model.LatLng(47.667807, 9.169234),
                new com.google.maps.model.LatLng(47.667902, 9.168608),
                new com.google.maps.model.LatLng(47.668447, 9.168759));

        parkingLotHtwgKonstanz.setName("HTWG");

        parkingLotHtwgKonstanz.setGeofencePosition(new com.google.maps.model.LatLng(47.668110, 9.169001));
        firestoreRepository.addHtwgParkingLotForTesting(parkingLotHtwgKonstanz);
    }
}
