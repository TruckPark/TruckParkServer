package de.htwg.moco.truckparkserver.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
//https://firebase.google.com/docs/firestore/quickstart
public class FirestoreService {





/*
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
*/
}
