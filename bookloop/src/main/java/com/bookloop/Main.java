package com.bookloop;

import java.io.IOException;

import com.bookloop.firebase.FirebaseInit;
import com.bookloop.firestore.FirestoreUtils;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class Main {
    public static void main(String[] args) throws IOException {
        // Set up Google Application credentials for Firestore
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "src/main/resources/serviceAccountKey.json");

        // Initialize Firebase
        FirebaseInit.initializeFirebase();

        // Initialize Firestore instance
        Firestore db = FirestoreOptions.newBuilder()
                .setProjectId("bookloop-5d51a")
                .build()
                .getService();

        // Add users to Firestore
       FirestoreUtils.addedUsersToTheFirestore(db);
       FirestoreUtils.addedBooksToTheFirestore(db);
        // Set up real-time listeners for Firestore
        setupRealTimeListeners(db);
        keepApplicationRunning();
    
    }

    // Set up real-time listeners for the 'users' collection
    private static void setupRealTimeListeners(Firestore db) {
        // Listener for the 'users' collection
        db.collection("users").addSnapshotListener((snapshots, e) -> {
            if (e != null) {
                System.err.println("Error listening to users collection: " + e.getMessage());
                return;
            }
    
            if (snapshots != null && !snapshots.isEmpty()) {
                System.out.println("Real-time update detected in 'users' collection:");
                snapshots.getDocumentChanges().forEach(change -> {
                    switch (change.getType()) {
                        case ADDED:
                            System.out.println("Added: " + change.getDocument().getData());
                            break;
                        case MODIFIED:
                            System.out.println("Modified: " + change.getDocument().getData());
                            break;
                        case REMOVED:
                            System.out.println("Removed: " + change.getDocument().getId());
                            break;
                    }
                });
            }
        });
    }
    
    private static void keepApplicationRunning() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

