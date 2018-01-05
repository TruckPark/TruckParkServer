package de.htwg.moco.truckparkserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwg.moco.truckparkserver.service.CleanUserUpdateService;
import de.htwg.moco.truckparkserver.service.ParkingLotsOnRouteService;
import de.htwg.moco.truckparkserver.service.PredictionsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@CrossOrigin
public class Controller {

    @Resource
    private ParkingLotsOnRouteService parkingLotsOnRouteService;

    @Autowired
    private CleanUserUpdateService cleanUserUpdateService;

    @Autowired
    private PredictionsService predictionsService;

    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/online")
    public String online() {
        return "Online";
    }


    @GetMapping("/clean")
    public void clean() {
        cleanUserUpdateService.clean();
    }

    @GetMapping("/predict")
    public void predict() {
        predictionsService.calc();
    }

    @PostMapping("/parkinglots")
    public ResponseEntity getParkingLotsOnRoute(@RequestBody String s) throws IOException {
        JSONObject parkingLotsOnRoute = parkingLotsOnRouteService.getParkingLotsOnRoute(s);

        System.out.println(parkingLotsOnRoute.toString());
        return new ResponseEntity<>(parkingLotsOnRoute.toString(), HttpStatus.OK);
    }
}
