package de.htwg.moco.truckparkserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotHistory {
    String name;
    Map<String, History> history;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class History {
        long millis;
        int usage;
    }
}
