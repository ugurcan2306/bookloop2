package com.example;
//import java.awt.TextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import client.FireStoreHelper;

import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class forYouFeedController {

    private int likeCount;
    private int dislikeCount;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox altvbox;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private VBox mainvbox;

    @FXML
    private ScrollPane scrollPaneFeedPage;

    @FXML
    private Text textForYou;
    @FXML
    private Button newComButton;

    private int currentCommentIndex = 0; // Keeps track of which comment to show next
    private List<Map<String, Object>> allComments = new ArrayList<>(); 


      @FXML
    void showNewComment(ActionEvent event) throws IOException {
        Firestore db = FireStoreHelper.getFirestore();

        try {
            // If the comments are already fetched, avoid re-fetching
            if (allComments.isEmpty()) {
                // Query for documents in the 'books' collection
                ApiFuture<QuerySnapshot> future = db.collection("books").get();
                QuerySnapshot querySnapshot = future.get();

                // Iterate over each document in the 'books' collection
                for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                    System.out.println("Fetching comments for book: " + document.getId()); // Debugging line

                    // Get the 'Comments' array from the document
                   List<Map<String, Object>> commentsList = (List<Map<String, Object>>) document.get("Comments");

if (commentsList != null) {
    for (Map<String, Object> commentData : commentsList) {
        allComments.add(commentData); // Add each comment map to allComments
    }
}
                }
            }

            // Display the next comment if available
            if (currentCommentIndex < allComments.size()) {
                Map<String, Object> commentData = allComments.get(currentCommentIndex);

                String bookName = (String) commentData.get("bookname");
                String comment = (String) commentData.get("commentMade");
                String usrname = (String) commentData.get("usernameOfTheOwner");

                String finalComment = "Book Name: " + bookName + "\n" + comment;
                addCommentToUI(finalComment, usrname);

                currentCommentIndex++; // Move to the next comment
            } else {
                System.out.println("No more comments to display.");
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace(); // Handle error
        }
    }

    // Your method to add comments to the UI

            
    
        


    @FXML
    void initialize() throws IOException {
        assert altvbox != null : "fx:id=\"altvbox\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert anchorpane != null : "fx:id=\"anchorpane\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert mainvbox != null : "fx:id=\"mainvbox\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert newComButton != null : "fx:id=\"newComButton\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert scrollPaneFeedPage != null : "fx:id=\"scrollPaneFeedPage\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textForYou != null : "fx:id=\"textForYou\" was not injected: check your FXML file 'feedpage.fxml'.";
        loadCommentsFromFirestore();
    }
  private void loadCommentsFromFirestore() throws IOException {
   ArrayList<Book> allbooks=FinderFromDatabase.ShowBookList();
    
   for (Book book : allbooks) {
    book.getComments().
   }
    
     try {
     DocumentSnapshot document = future.get(); // Bloklu olarak sonucu al
    //     if (document.exists()) {
    //         // "BookstoTrade" verilerini yükle
    //         ArrayList<Map<String, Object>> booksToTrade = (ArrayList<Map<String, Object>>) document.get("BookstoTrade");
    //         if (booksToTrade != null) {
    //             for (Map<String, Object> bookData : booksToTrade) {
    //                 String bookName = (String) bookData.get("name");
    //                 String author = (String) bookData.get("author");
    //                 String genre = (String) bookData.get("genre");
    //                 Book book = new Book(bookName, author, genre);
    //                 addBook2TradeV(book);
    //             }
    //         }
            
    //     }
    // } catch (InterruptedException | ExecutionException e) {
    //     e.printStackTrace();
    // }
}

    @FXML
void addCommentToUI(String comment, String username) throws IOException {
   
    //currentFriend= FinderFromDatabase.UserFinder(username);
    HBox commentContainer = new HBox(10);
    commentContainer.setPrefHeight(150); // Adjust as needed
    commentContainer.setMaxWidth(600); 
    TextArea commentSection = new TextArea(comment);
    commentSection.setEditable(false);
    commentSection.setPrefWidth(400);
    commentSection.setPrefHeight(100);
    commentSection.setWrapText(true); // Allow text to wrap in the TextArea

    commentSection.setText(comment);
    
     // Spacing of 10 between elements

    // Create avatar placeholder (can be an ImageView instead)
    Label avatar = new Label("👤"); // Replace with an actual ImageView for real avatars
    avatar.setStyle("-fx-font-size: 36px;");

    // Create username label
    Label usernameLabel = new Label(username);

    // Create buttons
    // Button likeButton = new Button("See Profile");
    // Button dislikeButton = new Button("Start Chat");
    //Button newComment = new Button("Remove Friend");

    // Add event handling
    //likeButton.setOnAction(event -> );
    //dislikeButton.setOnAction(event ->handleStartChat(event, username));
    /*removeFriendButton.setOnAction(event -> {
        vboxscr.getChildren().remove(friendSection);
        currentUser.getFriends().remove(FinderFromDatabase.UserFinder(currentFriend.getUsername()));
        
        System.out.println(currentUser.getFriends());
        System.out.println(currentUser.getFriends());
        System.out.println(currentUser.getFriends());
        Firestore db;
            try {
                db = FireStoreHelper.getFirestore();
                DocumentReference userRef = db.collection("users").document(currentUser.getUsername());
                userRef.update("Friends", currentUser.getFriends()).get();
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
               
            }
    });*/

    // Add elements to the HBox
    commentContainer.getChildren().addAll(avatar, usernameLabel, commentSection);

    // Add the friend's section to the main VBox container
    altvbox.getChildren().add(commentContainer);
}

}
