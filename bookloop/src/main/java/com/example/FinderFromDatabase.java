package com.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import client.FireStoreHelper;

public class FinderFromDatabase {
    private static User userToFind=null;

    public static User UserFinder(String username){
    try {
            Firestore db = FireStoreHelper.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("Username", username).get();
            QuerySnapshot querySnapshot = future.get(); // This is a blocking call. Prefer using .addListener or .getAsync()
            if (querySnapshot != null && !querySnapshot.isEmpty()) {
               
                for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                    String storedUsername = document.getString("Username");
                    if (storedUsername.equals(username)) {
                        userToFind= document.toObject(User.class);
                       
                    }
                }
                
            } else {
                userToFind=null;
                System.out.println("User not found in Firestore.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userToFind;
    }
    public static User getUserToFind() {
        return userToFind;
    }
}