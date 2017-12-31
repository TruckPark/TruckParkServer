package de.htwg.moco.truckparkserver.persistence;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;

import com.google.cloud.firestore.WriteResult;
import de.htwg.moco.truckparkserver.model.ParkingLot;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;


@Repository
public class FirestoreRepository {

    @Resource
    private Firestore firestore;

    public void addParkingLot(String documentId, ParkingLot parkingLot) {
        ApiFuture<WriteResult> apiFuture = firestore.collection("parkingLots").document(documentId).set(parkingLot);
        try {
            System.out.println("Update time : " + apiFuture.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
