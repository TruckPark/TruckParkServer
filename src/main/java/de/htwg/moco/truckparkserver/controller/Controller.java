package de.htwg.moco.truckparkserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.Http;
import com.google.maps.model.LatLng;
import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.persistence.FirestoreRepository;
import de.htwg.moco.truckparkserver.service.FirestoreService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class Controller {

    @Resource
    FirestoreService firestoreService;

    @Resource
    FirestoreRepository firestoreRepository;

    @Resource
    ObjectMapper objectMapper;

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

    @PostMapping("/parkinglots")
    public ResponseEntity getParkingLotsOnRoute(@RequestBody String s) throws IOException {
        System.out.println(s);
        JSONObject jsonObject = new JSONObject(s);
        List<LatLng> path = new ArrayList<>();
        for(int i = 0; i < jsonObject.length(); i++){
            System.out.println(jsonObject.toString());
            path.add(objectMapper.readValue(jsonObject.getString(String.valueOf(i)),LatLng.class));
        }
        System.out.println(jsonObject);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
