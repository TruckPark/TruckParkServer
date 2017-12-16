package de.htwg.moco.truckparkserver.persistence;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
//https://firebase.google.com/docs/firestore/quickstart
public class FirestoreService {

    private final Firestore firestore;

    @Autowired
    public FirestoreService(Firestore firestore) {
        this.firestore = firestore;
    }

    public void addTest() {
        DocumentReference docRef = firestore.collection("users").document("alovelace");
// Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Ada");
        data.put("last", "Lovelace");
        data.put("born", 1815);
//asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
// ...
        try {
// result.get() blocks on response
            log.info("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
        }

    }

    public void getTest() {
        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> query = firestore.collection("users").get();
// ...
// query.get() blocks on response
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
        }
        List<DocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents) {
            System.out.println("User: " + document.getId());
            System.out.println("First: " + document.getString("first"));
            if (document.contains("middle")) {
                System.out.println("Middle: " + document.getString("middle"));
            }
            System.out.println("Last: " + document.getString("last"));
            System.out.println("Born: " + document.getLong("born"));
        }
    }

}
