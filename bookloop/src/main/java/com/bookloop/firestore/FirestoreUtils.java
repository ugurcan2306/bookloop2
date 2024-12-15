package com.bookloop.firestore;

import com.bookloop.firebase.FirebaseStorageUtils;
import com.example.Book;
import com.example.GroupChat;
import com.example.OneToOneChat;
import com.example.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.awt.Image;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FirestoreUtils {
// -password
// -Username
// -Books to Trade(Kitap1)
// -MarkedAsRead(Kitap2)
// -friends
// -trade number
// -person traded
// -Chatler


    public static void addUserToFirestore(Firestore db, String username, String password, String email,String Country, String City, ArrayList<Book> booksToTrade,ArrayList<Book> MarkedAsRead, ArrayList<GroupChat> GroupChat,ArrayList<OneToOneChat> OnetoOneChat,ArrayList<User> Friends ) {
        Map<String, Object> user = new HashMap<>();
        user.put("Username", username);
        user.put("Password", password);
        user.put("Email", email);
        user.put("Country", Country);
        user.put("City", City);
        user.put("BookstoTrade", booksToTrade);
        user.put("MarkedasRead", MarkedAsRead);
        user.put("GroupChat", GroupChat);
        user.put("OneToOneChat", OnetoOneChat);
        user.put("Friends", Friends);
       
        try {
            db.collection("users").document(username).set(user);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addedUsersToTheFirestore(Firestore db){
        //ArrayList<Book> booksttotrase= new ArrayList<>();
        //booksttotrase.add(new Book("Animal Farm","George Orwell", "Fabl"));
        //booksttotrase.add(new Book("1984","George Orwell", "Fabl"));
        

        //addUserToFirestore(db, "ugurcan23", "bilkentcs", "ugurcan@bilkent.edu.tr","Turkey", "Ankara",booksttotrase, booksttotrase,new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
      
    }
    public static void addBookstoTheFirestore(Firestore db,String name, double rate, ArrayList<String> comments,ArrayList<String>bookowners, String description){
        //image ekleme işi nasıl olur
        Map<String, Object> books = new HashMap<>();
        books.put("Name",name);
        books.put("Rate",rate);
        books.put("Comments",comments);
        books.put("Book owners",bookowners);
        books.put("Description",description);
        // books.put("Image",Url);
        try {
            ApiFuture<WriteResult> result1 = db.collection("books").document(name).set(books);
            System.out.println("Update time: " + result1.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void addedBooksToTheFirestore(Firestore db) throws IOException{
        
    ArrayList<String> comments= new ArrayList<>();
    comments.add("Great");
    comments.add("Okay");
    ArrayList<String> BookOwners= new ArrayList<>();
    BookOwners.add("ugurcan23");
    // Upload the image and get its URL
    
    // Store the image URL in Firestore
        addBookstoTheFirestore(db, "Animal Farm", 4.8, comments,BookOwners, "George Orwell's fable book published in 1945");
    }
}
