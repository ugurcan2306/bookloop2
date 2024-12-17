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

    private FireStoreHelper() {} // Singleton tasarım deseni için özel bir yapılandırıcı.

    public static synchronized Firestore getFirestore() throws IOException {
        if (db == null) {
            // Service Account JSON dosyasını okuyun
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

            // FirebaseOptions'u yapılandırın
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("bookloop-5d51a") // Projeye özgü kimlik
                    .build();

            // FirebaseApp'in bir kere başlatıldığından emin olun
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // Firestore istemcisini başlatın
            db = FirestoreClient.getFirestore();
        }
        return db;
    }

    public static synchronized void closeFirestore() {
        if (db != null) {
            db = null; // Firestore istemcisi için bir kapatma işlemi eklenebilir.
        }
    }
}
