package com.example;

import java.util.ArrayList;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import client.FireStoreHelper;

public class FinderFromDatabase {
    private static User userToFind=null;
    private static TradeRequest tradeReqToFind=null;
    private static Book bookToFind=null;

    public static User UserFinder(String username){
    try {
            Firestore db = FireStoreHelper.getFirestore();
            QuerySnapshot querySnapshot = db.collection("users").whereEqualTo("Username", username).get().get();// The second .get() (used after the first one) is a blocking call on the returned Task or Future. This second .get() forces your program to wait until the task completes and fetches the actual result.
            if (querySnapshot != null && !querySnapshot.isEmpty()) {
            userToFind= querySnapshot.getDocuments().get(0).toObject(User.class);
            }    
             else {
                userToFind=null;
                System.out.println("User not found in Firestore.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userToFind;
    }
    public static TradeRequest TradeReqFinder(String sender, String receiver) {
    TradeRequest tradeReqToFind = null;
    try {
        Firestore db = FireStoreHelper.getFirestore();
        QuerySnapshot querySnapshot = db.collection("tradeRequests")
            .whereEqualTo("TradeRequest.receivername", receiver)
            .whereEqualTo("TradeRequest.sendername", sender)
            .get()
            .get(); // Blocking call

        if (querySnapshot != null && !querySnapshot.isEmpty()) {
            DocumentSnapshot document = querySnapshot.getDocuments().get(0);
            Map<String, Object> tradeRequestMap = (Map<String, Object>) document.get("TradeRequest");
            
            if (tradeRequestMap != null) {
                String sendername = (String) tradeRequestMap.get("sendername");
                String receivername = (String) tradeRequestMap.get("receivername");
                tradeReqToFind = new TradeRequest(sendername, receivername);
            }
        } else {
            System.out.println("No trade request found for the given sender and receiver.");
        }
    } catch (Exception e) {
        System.err.println("Failed to retrieve trade request: " + e.getMessage());
        e.printStackTrace();
    }
    return tradeReqToFind;
}

    
    
    
    public static User getUserToFind() {
        return userToFind;
    }
    public static Book BookFinder(String bookName){
        try {
            Firestore db = FireStoreHelper.getFirestore();
            QuerySnapshot querySnapshot = db.collection("books").whereEqualTo("Name", bookName).get().get();
            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    bookToFind= querySnapshot.getDocuments().get(0).toObject(Book.class);  
                    }
            else {
                userToFind=null;
                System.out.println("Book not found in Firestore.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookToFind;
    }
    public static ArrayList<Book> ShowBookList(){
        ArrayList<Book> newarrr= new ArrayList<>();
        try {
            Firestore db = FireStoreHelper.getFirestore();
            QuerySnapshot querySnapshot = db.collection("books").get().get();
           for (int i = 0; i < querySnapshot.size(); i++) {
            newarrr.add(querySnapshot.getDocuments().get(i).toObject(Book.class));
           }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newarrr;
    }

    
}
