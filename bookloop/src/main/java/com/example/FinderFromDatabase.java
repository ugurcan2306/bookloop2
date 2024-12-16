package com.example;

import java.util.ArrayList;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import client.FireStoreHelper;

public class FinderFromDatabase {
    private static User userToFind=null;
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
