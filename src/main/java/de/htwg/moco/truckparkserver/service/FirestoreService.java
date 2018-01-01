package de.htwg.moco.truckparkserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;
import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.persistence.FirestoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
//https://firebase.google.com/docs/firestore/quickstart
public class FirestoreService {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private FirestoreRepository firestoreRepository;

    public List<ParkingLot> getParkingLotsOnRoute(String path){
        List<ParkingLot> parkingLotsOnRoute = new ArrayList<>();
        List<LatLng> latLngList = convertJsonPathToLatLngList(path);
        Map<String, Double> definedArea = getLatLngDefinedArea(latLngList);
        List<ParkingLot> parkingLotsInsideDefinedArea = firestoreRepository.findParkingLotsWithinADefinedArea(definedArea);



        return parkingLotsOnRoute;
    }


    private List<LatLng> convertJsonPathToLatLngList(String path){
        JSONObject jsonObject = new JSONObject(path);
        List<LatLng> latlngPath = new ArrayList<>();
        for(int i = 0; i < jsonObject.length(); i++){
            System.out.println(jsonObject.toString());
            try {
                latlngPath.add(objectMapper.readValue(jsonObject.getString(String.valueOf(i)),LatLng.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return latlngPath;
    }

    private Map<String, Double> getLatLngDefinedArea(List<LatLng> latLngs){
        Map<String, Double> minMaxValuesMap = new HashMap<>();
        double maxLat = -180;
        double minLat = 180;
        double maxLng = -180;
        double minLng = 180;
        for(LatLng latLng : latLngs){
            if(latLng.lat > maxLat){
                maxLat = latLng.lat;
            }
            if(latLng.lat < minLat){
                minLat = latLng.lat;
            }
            if(latLng.lng > maxLng){
                maxLng = latLng.lng;
            }
            if(latLng.lng < minLng){
                minLng = latLng.lng;
            }
        }
        minMaxValuesMap.put("maxLat", maxLat);
        minMaxValuesMap.put("minLat", minLat);
        minMaxValuesMap.put("minLng", minLng);
        minMaxValuesMap.put("maxLng", maxLng);

        return minMaxValuesMap;
    }



}
