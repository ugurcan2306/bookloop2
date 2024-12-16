package com.bookloop.firestore;

import com.bookloop.firebase.FirebaseStorageUtils;
import com.example.Book;
import com.example.Comment;
import com.example.GroupChat;
import com.example.OneToOneChat;
import com.example.TradeRequest;
import com.example.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.awt.Image;

import java.io.IOException;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public static void addUserToFirestore(Firestore db, String username, String password, String email,String Country, String City, ArrayList<Book> booksToTrade,ArrayList<Book> MarkedAsRead, ArrayList<GroupChat> GroupChat,ArrayList<OneToOneChat> OnetoOneChat,ArrayList<User> Friends,ArrayList<TradeRequest> receivedTradeRequests,ArrayList<TradeRequest> senTradeRequests) {
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
        user.put("ReceivedTradeRequests", receivedTradeRequests);
        user.put("SentTradeRequests", senTradeRequests);
       
        try {
            db.collection("users").document(username).set(user);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addedUsersToTheFirestore(Firestore db){
        ArrayList<Book> booksttotrase= new ArrayList<>();
        booksttotrase.add(new Book("Animal Farm","George Orwell", "Fabl"));
        booksttotrase.add(new Book("1984","George Orwell", "Fabl"));
        

        addUserToFirestore(db, "ugurcan23", "bilkentcs", "ugurcan@bilkent.edu.tr","Turkey", "Ankara",booksttotrase, booksttotrase,new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        addUserToFirestore(db, "ece", "bilkentcs", "ugurcan@bilkent.edu.tr","Turkey", "Ankara",booksttotrase, booksttotrase,new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        addUserToFirestore(db, "efe", "bilkentcs", "ugurcan@bilkent.edu.tr","Turkey", "Ankara",booksttotrase, booksttotrase,new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        addUserToFirestore(db, "emre", "bilkentcs", "ugurcan@bilkent.edu.tr","Turkey", "Ankara",booksttotrase, booksttotrase,new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
    }
    public static void addBookstoTheFirestore(Firestore db,String name, String author, String genre, double rate, ArrayList<Comment> comments,ArrayList<User>bookowners, String description,int numberofRates){
        //image ekleme işi nasıl olur
        Map<String, Object> books = new HashMap<>();
        books.put("Name",name);
        books.put("Author", author);
        books.put("Genre", genre);
        books.put("Rate",rate);
        books.put("Comments",comments);
        books.put("Bookowners",bookowners);
        books.put("Description",description);
        books.put("NumberOfRatings", numberofRates);
        
        try {
            ApiFuture<WriteResult> result1 = db.collection("books").document(name).set(books);
            System.out.println("Update time: " + result1.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void addedBooksToTheFirestore(Firestore db) throws IOException{
    
    ArrayList<Comment> comments= new ArrayList<>();
    comments.add(new Comment("great", new User("a", "", "", "", ""),Timestamp.now()));
    comments.add(new Comment("ok", new User("b", "", "", "", ""),Timestamp.now()));
    ArrayList<User> BookOwners= new ArrayList<>();
    BookOwners.add(new User("aa", "", "", "", ""));
    // Upload the image and get its URL
    
    // Store the image URL in Firestore
        addBookstoTheFirestore(db, "Animal Farm","George Orwell","Fabl", 4.8, comments,BookOwners, "George Orwell's fable book published in 1945",5);
    }

    public static void addTradeRequeststoTheFirestore(Firestore db,TradeRequest tr){
        Map<String, Object> tradeRequests= new HashMap<>();
        tradeRequests.put("TradeRequest", tr);
        db.collection("tradeRequests").document(tr.toString()).set(tradeRequests);
    }

}
