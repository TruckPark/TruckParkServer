package de.htwg.moco.truckparkserver;

import de.htwg.moco.truckparkserver.persistence.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
    FirestoreService firestoreService;

    @GetMapping("/online")
    public String online() {
        return "Online";
    }

    @GetMapping("/testGet")
    public void testFirestoreGet() {
        firestoreService.getTest();
    }

    @GetMapping("/testAdd")
    public void testFirestoreAdd() {
        firestoreService.addTest();
    }
}
