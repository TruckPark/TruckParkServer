package de.htwg.moco.truckparkserver.persistence;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import de.htwg.moco.truckparkserver.model.ParkingLot;
import de.htwg.moco.truckparkserver.model.ParkingLotHistory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ParkingLotsRepository {

    void addParkingLot(ParkingLot parkingLot);
    List<ParkingLot> findParkingLotsWithinADefinedArea(Map<String, Double> definedArea);

    List<ParkingLot> getParkingLots();
    List<String> getParkingLotIds();
    ParkingLot getParkingLot(String parkingLotId);

    ApiFuture<WriteResult> addParkingLotHistory(ParkingLotHistory parkingLotHistory);

    List<DocumentSnapshot> getParkingLotHistories();
}
