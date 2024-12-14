package client;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FireStoreHelper {
    private static Firestore db;

    public static Firestore getFirestore() throws IOException {
        if (db == null) {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("bookloop-5d51a") 
                    .build();

            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
        }
        return db;
    }
}
