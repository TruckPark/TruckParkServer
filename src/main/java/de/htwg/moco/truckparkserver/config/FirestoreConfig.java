package de.htwg.moco.truckparkserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class FirestoreConfig {
    @Bean
    public Firestore firestore() {
        return FirestoreClient.getFirestore();
    }

    @Resource
    private ObjectMapper objectMapper;

    @PostConstruct
    public void firebaseService() throws IOException {
        String path = "src\\main\\resources\\TruckParkMoco-31609a1b4551.json";

        // if file exists use configuration of file, else use env vars
        if (new File(path).exists()) {
            InputStream serviceAccount = new FileInputStream(path);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
        } else {
            FirebaseConfig firebaseConfig = new FirebaseConfig(
                    System.getenv("firebase.config.type"),
                    System.getenv("firebase.config.project_id"),
                    System.getenv("firebase.config.private_key_id"),
                    System.getenv("firebase.config.private_key"),
                    System.getenv("firebase.config.client_email"),
                    System.getenv("firebase.config.client_id"),
                    System.getenv("firebase.config.auth_uri"),
                    System.getenv("firebase.config.token_uri"),
                    System.getenv("firebase.config.auth_provider_x509_cert_url"),
                    System.getenv("firebase.config.client_x509_cert_url"),
                    null
            );

            byte[] firebaseConfigBytes = objectMapper.writeValueAsBytes(firebaseConfig);
            GoogleCredentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(firebaseConfigBytes));
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
        }
    }

    @AllArgsConstructor
    class FirebaseConfig {
        public String type;
        public String projectId;
        public String privateKeyId;
        public String privateKey;
        public String clientEmail;
        public String clientId;
        public String authUri;
        public String tokenUri;
        public String authProviderX509CertUrl;
        public String clientX509CertUrl;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    }
}
