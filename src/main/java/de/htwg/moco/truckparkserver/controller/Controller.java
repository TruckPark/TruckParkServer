package de.htwg.moco.truckparkserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.service.ParkingLotsOnRouteService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Set;

@RestController
@CrossOrigin
public class Controller {

    @Resource
    private
    ParkingLotsOnRouteService parkingLotsOnRouteService;

    @Resource
    private ObjectMapper objectMapper;

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

    }

    @PostMapping("/parkinglots")
    public ResponseEntity getParkingLotsOnRoute(@RequestBody String s) throws IOException {
        JSONObject parkingLotsOnRoute = parkingLotsOnRouteService.getParkingLotsOnRoute(s);

        System.out.println(parkingLotsOnRoute.toString());
        return new ResponseEntity<>(parkingLotsOnRoute.toString(), HttpStatus.OK);
    }
}
