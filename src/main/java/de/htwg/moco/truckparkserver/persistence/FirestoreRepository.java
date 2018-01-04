package de.htwg.moco.truckparkserver.persistence;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import de.htwg.moco.truckparkserver.model.ParkingLot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Repository
@Slf4j
public class FirestoreRepository implements ParkingLotsRepository {

    @Resource
    private Firestore firestore;

    public void addParkingLot(String documentId, ParkingLot parkingLot) {
        ApiFuture<WriteResult> apiFuture = firestore.collection("parkingLots").document(documentId).set(parkingLot);
        try {
            log.debug("Update time : " + apiFuture.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * In this method, the existing parking spaces are retrieved from the database and the results are reduced to
     * suitable parking spaces in order to minimize the necessary comparisons. Due to the previously determined max and
     * min values of Lat/Long we received a rectangle in which the suitable parking spaces are located. Unfortunately,
     * it is only possible to filter a single attribute in Firestore, so the restriction to the other attribute
     * (Latitude in this case) has to be done by us.
     * @param definedArea
     * @return
     */
    public List<ParkingLot> findParkingLotsWithinADefinedArea(Map<String, Double> definedArea){
        List<ParkingLot> parkingLotsWithinDefinedArea = new ArrayList<>();
        CollectionReference parkingLots = firestore.collection("parkingLots");
        Query query = parkingLots
                .whereGreaterThan("geofencePosition.lng", definedArea.get("minLng"))
                .whereLessThan("geofencePosition.lng", definedArea.get("maxLng"));

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
        try {
            for(DocumentSnapshot documentSnapshot : querySnapshotApiFuture.get().getDocuments()){
                log.debug(documentSnapshot.getId());
                ParkingLot parkingLot = documentSnapshot.toObject(ParkingLot.class);
                if(parkingLot.getGeofencePosition().lat < definedArea.get("maxLat") && parkingLot.getGeofencePosition().lat > definedArea.get("minLat")){
                    parkingLotsWithinDefinedArea.add(parkingLot);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return parkingLotsWithinDefinedArea;
    }


}
