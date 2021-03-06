package de.htwg.moco.truckparkserver.model;




import com.google.maps.model.LatLng;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebastian Thümmel on 30.12.2017.
 */
@Data
public class ParkingLot {

    private String name;

    private List<LatLng> parkingLotDimensions;

    private List<String> devicesAtParkingArea;

    private int maxParkingLots;

    private LatLng geofencePosition;

    private String documentId;

    private Directions drivingDirection;

    private Map<String, List<Integer>> prediction;

    private Map<String, Integer> predictionAvg;

    private Map<String, Integer> predictionLinearRegression;

    public ParkingLot(String name, List<LatLng> parkingLotDimensions, int maxParkingLots, LatLng geofencePosition) {
        this.name = name;
        this.parkingLotDimensions = parkingLotDimensions;
        this.maxParkingLots = maxParkingLots;
        this.geofencePosition = geofencePosition;
    }

    public ParkingLot(){
        this.devicesAtParkingArea = new ArrayList<>();
        this.parkingLotDimensions = new ArrayList<>();
    }

    public ParkingLot(LatLng... latLngs){
        this.devicesAtParkingArea = new ArrayList<>();
        this.parkingLotDimensions = Arrays.asList(latLngs);
    }

    public ParkingLot( int maxParkingLots, LatLng... latLngs){
        this.devicesAtParkingArea = new ArrayList<>();
        this.parkingLotDimensions = Arrays.asList(latLngs);
        this.maxParkingLots = maxParkingLots;
    }

    public Map<String, List<Integer>> getPrediction() {
        return prediction;
    }

    public void setPrediction(Map<String, List<Integer>> prediction) {
        this.prediction = prediction;
    }

    public List<LatLng> getParkingLotDimensions() {
        return parkingLotDimensions;
    }

    public void setParkingLotDimensions(List<LatLng> parkingLotDimensions) {
        this.parkingLotDimensions = parkingLotDimensions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getPredictionAvg() {
        return predictionAvg;
    }

    public void setPredictionAvg(Map<String, Integer> predictionAvg) {
        this.predictionAvg = predictionAvg;
    }

    public Map<String, Integer> getPredictionLinearRegression() {
        return predictionLinearRegression;
    }

    public void setPredictionLinearRegression(Map<String, Integer> predictionLinearRegression) {
        this.predictionLinearRegression = predictionLinearRegression;
    }

    public int getMaxParkingLots() {
        return maxParkingLots;
    }

    public void setMaxParkingLots(int maxParkingLots) {
        this.maxParkingLots = maxParkingLots;
    }

    public LatLng getGeofencePosition() {
        return geofencePosition;
    }

    public void setGeofencePosition(LatLng geofencePosition) {
        this.geofencePosition = geofencePosition;
    }


    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public List<String> getDevicesAtParkingArea() {
        return devicesAtParkingArea;
    }

    public void setDevicesAtParkingArea(List<String> devicesAtParkingArea) {
        this.devicesAtParkingArea = devicesAtParkingArea;
    }

    public void addDeviceToParkingLot(String deviceId){
        if(!this.devicesAtParkingArea.contains(deviceId)){
            this.devicesAtParkingArea.add(deviceId);
        }
    }

    public void removeDeviceFromParkingLot(String deviceId){
        if(this.devicesAtParkingArea.contains(deviceId)){
            this.devicesAtParkingArea.remove(deviceId);
        }
    }

    public Directions getDrivingDirection() {
        return drivingDirection;
    }

    public void setDrivingDirection(Directions drivingDirection) {
        this.drivingDirection = drivingDirection;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "name='" + name + '\'' +
                ", parkingLotDimensions=" + parkingLotDimensions +
                ", maxParkingLots=" + maxParkingLots +
                ", geofencePosition=" + geofencePosition +
                '}';
    }

    public enum Directions {
        NORTH (270,90), EAST(0,180), SOUTH(90,270), WEST(180,360), ALL(0,360);

        private double lowerBoundaryDirection;
        private double upperBoundaryDirection;

        Directions(double lowerBoundaryDirection, double upperBoundaryDirection){
            this.lowerBoundaryDirection = lowerBoundaryDirection;
            this.upperBoundaryDirection = upperBoundaryDirection;
        }

        public double getLowerBoundaryDirection() {
            return lowerBoundaryDirection;
        }

        public void setLowerBoundaryDirection(double lowerBoundaryDirection) {
            this.lowerBoundaryDirection = lowerBoundaryDirection;
        }

        public double getUpperBoundaryDirection() {
            return upperBoundaryDirection;
        }

        public void setUpperBoundaryDirection(double upperBoundaryDirection) {
            this.upperBoundaryDirection = upperBoundaryDirection;
        }
    }
}
