package de.htwg.moco.truckparkserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Map;

@Data
@AllArgsConstructor
public class ParkingLotHistory {
    String name;
    Map<String, History> history;

    @Data
    @AllArgsConstructor
    public static class History {
        DateTime dateTime;
        int usage;
    }
}
