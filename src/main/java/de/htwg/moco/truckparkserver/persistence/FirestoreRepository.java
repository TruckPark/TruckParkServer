package de.htwg.moco.truckparkserver.persistence;


import com.google.cloud.firestore.Firestore;

import de.htwg.moco.truckparkserver.model.ParkingLot;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;


@Repository
public class FirestoreRepository {

    @Resource
    private Firestore firestore;

    public void addHtwgParkingLotForTesting(ParkingLot parkingLot) {
        firestore.collection("parkingLots").document("HTWG").set(parkingLot);

    }


}
