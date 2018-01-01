package de.htwg.moco.truckparkserver.persistence;

import de.htwg.moco.truckparkserver.model.ParkingLot;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ParkingLotsRepository {

    void addParkingLot(String documentId, ParkingLot parkingLot);
    List<ParkingLot> findParkingLotsWithinADefinedArea(Map<String, Double> definedArea);

}
