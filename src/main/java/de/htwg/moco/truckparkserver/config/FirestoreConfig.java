package de.htwg.moco.truckparkserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
        InputStream serviceAccount;
        GoogleCredentials credentials;
        // if file exists use configuration of file, else use env vars
        if (new File(path).exists()) {
            serviceAccount = new FileInputStream(path);
            credentials = GoogleCredentials.fromStream(serviceAccount);
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

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); //pretty-print
            String configString = objectMapper.writeValueAsString(firebaseConfig);
            //replaced escaped backslashs by single backslashs (in private key)
            String configStringUnescaped = configString.replaceAll("\\\\\\\\", "\\\\");
            serviceAccount = new ByteArrayInputStream(configStringUnescaped.getBytes());
            credentials = GoogleCredentials.fromStream(serviceAccount);
        }

        //GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
    }

    @AllArgsConstructor
    class FirebaseConfig {
        public String type;
        public String project_id;
        public String private_key_id;
        public String private_key;
        public String client_email;
        public String client_id;
        public String auth_uri;
        public String token_uri;
        public String auth_provider_x509_cert_url;
        public String client_x509_cert_url;
        private Map<String, Object> additional_properties = new HashMap<String, Object>();
    }
}
