package com.example;

import client.FireStoreHelper;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class SessionManager {

    // Static field to hold the current Firebase User
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String username) {
        try {
            Firestore db = FireStoreHelper.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("Username", username).get();
            QuerySnapshot querySnapshot = future.get(); // This is a blocking call. Prefer using .addListener or .getAsync()
            if (querySnapshot != null && !querySnapshot.isEmpty()) {
               
                for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                    String storedUsername = document.getString("Username");
                    if (storedUsername.equals(username)) {
                        currentUser = document.toObject(User.class);
                        break;
                    }
                }
                
            } else {
                System.out.println("User not found in Firestore.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // try {
        //     Firestore db = FireStoreHelper.getFirestore();
        //     ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("Username", username).get();
        //     QuerySnapshot querySnapshot = future.get(); // This is a blocking call. Prefer using .addListener or .getAsync()
        //     if (querySnapshot != null && !querySnapshot.isEmpty()) {
               
        //         for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
        //             String storedUsername = document.getString("Username");
        //             if (storedUsername.equals(username)) {
        //                 currentUser = document.toObject(User.class);
        //                 break;
        //             }
        //         }
                
        //     } else {
        //         System.out.println("User not found in Firestore.");
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
    
}