package de.htwg.moco.truckparkserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;
import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.persistence.ParkingLotsRepository;
import lombok.extern.slf4j.Slf4j;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
//https://firebase.google.com/docs/firestore/quickstart
public class ParkingLotsOnRouteService {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private ParkingLotsRepository parkingLotsRepository;

    public Set<ParkingLot> getParkingLotsOnRoute(String path){

        List<LatLng> latLngList = convertJsonPathToLatLngList(path);
        Map<String, Double> definedArea = getLatLngDefinedArea(latLngList);
        List<ParkingLot> parkingLotsInsideDefinedArea = parkingLotsRepository.findParkingLotsWithinADefinedArea(definedArea);

        return findParkingLotsOnRoute(parkingLotsInsideDefinedArea, latLngList);
    }

    private Set<ParkingLot> findParkingLotsOnRoute(List<ParkingLot> parkingLotsInsideDefinedArea, List<LatLng> route){
        return parkingLotsInsideDefinedArea.parallelStream()
                .filter(parkingLot -> isParkingLotOnRoute(parkingLot, route))
                .collect(Collectors.toSet());
    }

    private boolean isParkingLotOnRoute(ParkingLot parkingLot, List<LatLng> route){
        return route.parallelStream().anyMatch(latLng -> isParkingLotNextToWaypoint(parkingLot, latLng));
    }

    private boolean isParkingLotNextToWaypoint(ParkingLot parkingLot, LatLng latLng){
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        GlobalPosition pointA = new GlobalPosition(latLng.lat, latLng.lng, 0.0);
        GlobalPosition userPos = new GlobalPosition(parkingLot.getGeofencePosition().lat,parkingLot.getGeofencePosition().lng, 0.0 );

        return geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance() < 2000;
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