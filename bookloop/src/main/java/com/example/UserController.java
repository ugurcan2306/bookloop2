package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import client.FireStoreHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
public class UserController {
    User currentUser= SessionManager.getCurrentUser();
    @FXML
    private VBox profileContainer;
    @FXML
    private VBox markeVbox;
   
    @FXML
    private Button addToTradebtn;

    @FXML
    private Pane addbookPane;

    @FXML
    private AnchorPane anchorpane1;
    @FXML
    private VBox addTradeVbox;
    @FXML
    private ScrollPane scrollpane;



    @FXML
    private TextField bookAuthorText1;

    @FXML
    private TextField bookNameText;

    @FXML
    private TextField bookgenretext;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label labelAddABook;

    @FXML
    private Pane markedAsREadPane;

    @FXML
    private Text markedAsReadBaslik;

    @FXML
    private Button markedAsReadbtn;

    @FXML
    private ScrollBar scrollBar2;

    @FXML
    private ScrollBar scrollbar1;

    @FXML
    private Pane willTradeBoooksPane;

    @FXML
    private Text yourBooks;

    @FXML
    private ScrollPane scrPane;

    @FXML
    void initialize() throws IOException {
        assert addToTradebtn != null : "fx:id=\"addToTradebtn\" was not injected: check your FXML file 'UserController.fxml'.";
        assert addbookPane != null : "fx:id=\"addbookPane\" was not injected: check your FXML file 'UserController.fxml'.";
        assert anchorpane1 != null : "fx:id=\"anchorpane1\" was not injected: check your FXML file 'UserController.fxml'.";
        assert bookAuthorText1 != null : "fx:id=\"bookAuthorText1\" was not injected: check your FXML file 'UserController.fxml'.";
        assert bookNameText != null : "fx:id=\"bookNameText\" was not injected: check your FXML file 'UserController.fxml'.";
        assert bookgenretext != null : "fx:id=\"bookgenretext\" was not injected: check your FXML file 'UserController.fxml'.";
        assert label1 != null : "fx:id=\"label1\" was not injected: check your FXML file 'UserController.fxml'.";
        assert label2 != null : "fx:id=\"label2\" was not injected: check your FXML file 'UserController.fxml'.";
        assert labelAddABook != null : "fx:id=\"labelAddABook\" was not injected: check your FXML file 'UserController.fxml'.";
        assert markedAsREadPane != null : "fx:id=\"markedAsREadPane\" was not injected: check your FXML file 'UserController.fxml'.";
        assert scrPane != null : "fx:id=\"scrPane\" was not injected: check your FXML file 'UserController.fxml'.";
        assert markedAsReadBaslik != null : "fx:id=\"markedAsReadBaslik\" was not injected: check your FXML file 'UserController.fxml'.";
        assert scrollpane != null : "fx:id=\"scrollpane\" was not injected: check your FXML file 'UserController.fxml'.";
        assert markedAsReadbtn != null : "fx:id=\"markedAsReadbtn\" was not injected: check your FXML file 'UserController.fxml'.";
        assert profileContainer != null : "fx:id=\"profileContainer\" was not injected: check your FXML file 'UserController.fxml'.";
        assert scrollBar2 != null : "fx:id=\"scrollBar2\" was not injected: check your FXML file 'UserController.fxml'.";
        assert addTradeVbox != null : "fx:id=\"addTradeVbox\" was not injected: check your FXML file 'UserController.fxml'.";
        assert scrollbar1 != null : "fx:id=\"scrollbar1\" was not injected: check your FXML file 'UserController.fxml'.";
        assert markeVbox != null : "fx:id=\"markeVbox\" was not injected: check your FXML file 'UserController.fxml'.";
        assert willTradeBoooksPane != null : "fx:id=\"willTradeBoooksPane\" was not injected: check your FXML file 'UserController.fxml'.";
        assert yourBooks != null : "fx:id=\"yourBooks\" was not injected: check your FXML file 'UserController.fxml'.";
        loadBooksFromFirestore();
    }
  private void loadBooksFromFirestore() throws IOException {
    Firestore db = FireStoreHelper.getFirestore();
    DocumentReference userRef = db.collection("users").document(currentUser.getUsername());

    ApiFuture<DocumentSnapshot> future = userRef.get(); // Firestore isteğini başlat
    try {
        DocumentSnapshot document = future.get(); // Bloklu olarak sonucu al
        if (document.exists()) {
            // "BookstoTrade" verilerini yükle
            ArrayList<Map<String, Object>> booksToTrade = (ArrayList<Map<String, Object>>) document.get("BookstoTrade");
            if (booksToTrade != null) {
                for (Map<String, Object> bookData : booksToTrade) {
                    String bookName = (String) bookData.get("name");
                    String author = (String) bookData.get("author");
                    String genre = (String) bookData.get("genre");
                    Book book = new Book(bookName, author, genre);
                    addBook2TradeV(book);
                }
            }

            // "MarkedasRead" verilerini yükle
            ArrayList<Map<String, Object>> markedAsRead = (ArrayList<Map<String, Object>>) document.get("MarkedasRead");
            if (markedAsRead != null) {
                for (Map<String, Object> bookData : markedAsRead) {
                    String bookName = (String) bookData.get("name");
                    String author = (String) bookData.get("author");
                    String genre = (String) bookData.get("genre");
                    Book book = new Book(bookName, author, genre);
                    addBook2Mark(book);
                }
            }
        }
    } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
    }
}

    @FXML
    void addTrade(ActionEvent event) throws Exception {
        String bookname= bookNameText.getText();
        String author= bookAuthorText1.getText();
        String genre= bookgenretext.getText();
        //önce setCurrentUser?// listeleme işi nasıl olur?
        currentUser.addtoTrade(bookname, author, genre);
        Firestore db = FireStoreHelper.getFirestore();
        DocumentReference userRef = db.collection("users").document(currentUser.getUsername());
        userRef.update("BookstoTrade", currentUser.getBookstoTrade()).get();
        Book book = new Book(bookname, author, genre);
        addBook2TradeV(book);
       
    }

    @FXML
    void markAsRead(ActionEvent event) throws Exception {
        String bookname= bookNameText.getText();
        String author= bookAuthorText1.getText();
        String genre= bookgenretext.getText();
        currentUser.markedAsRead(bookname, author, genre);
        Firestore db = FireStoreHelper.getFirestore();
        DocumentReference userRef = db.collection("users").document(currentUser.getUsername());
        userRef.update("MarkedasRead", currentUser.getMarkedasRead()).get();
        Book book = new Book(bookname, author, genre);
        addBook2Mark(book);

        
    }
    public void addBook2TradeV(Book book) {

        
        HBox bookSection = new HBox(10); // Spacing of 10 between elements

        // Create avatar placeholder (can be an ImageView instead)
        Label avatar = new Label("📚"); // Replace with an actual ImageView for real avatars
        avatar.setStyle("-fx-font-size: 36px;");

        // Create username label
        
        Label bookNameLabel = new Label(book.getName());

        // Create buttons
        Button removeBookButton = new Button("Remove Book");

        // Add event handling (optional)
        removeBookButton.setOnAction(event -> {
            addTradeVbox.getChildren().remove(bookSection); // UI'dan kaldır
            currentUser.getBookstoTrade().remove(book); // Modelden kaldır
            
            
            // Firestore güncellemesi
            Firestore db;
            try {
                db = FireStoreHelper.getFirestore();
                DocumentReference userRef = db.collection("users").document(currentUser.getUsername());
        
                // Güncelleme işlemi
                userRef.update("BookstoTrade", currentUser.getBookstoTrade()).get();
                ;
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
               
            }
        });
        
        // Add elements to the HBox
        bookSection.getChildren().addAll(avatar, bookNameLabel, removeBookButton);
        /*String listContent = String.join("\n", bookSection);
        label1.setText(listContent);
        bookNameText.clear();*/

        // Add the friend's section to the main VBox container
        addTradeVbox.getChildren().add(bookSection);
        
        // Create the HBox container for the friend's section
        
    }


    public void addBook2Mark(Book book) {
        
        HBox bookSection = new HBox(10); // Spacing of 10 between elements

        // Create avatar placeholder (can be an ImageView instead)
        Label avatar = new Label("📚"); // Replace with an actual ImageView for real avatars
        avatar.setStyle("-fx-font-size: 36px;");

        // Create username label
        
        Label bookNameLabel = new Label(book.getName());

        // Create buttons
        Button removeBookButton = new Button("Remove Book");

        // Add event handling (optional)
        removeBookButton.setOnAction(event -> {
            markeVbox.getChildren().remove(bookSection);
            currentUser.getMarkedasRead().remove(book); 
            
            Firestore db;
            try {
                db = FireStoreHelper.getFirestore();
                DocumentReference userRef = db.collection("users").document(currentUser.getUsername());
        
                // Güncelleme işlemi
                userRef.update("MarkedasRead", currentUser.getMarkedasRead()).get();
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
               
            }
        });

        // Add elements to the HBox
        bookSection.getChildren().addAll(avatar, bookNameLabel, removeBookButton);
        /*String listContent = String.join("\n", bookSection);
        label1.setText(listContent);
        bookNameText.clear();*/

        // Add the friend's section to the main VBox container
        markeVbox.getChildren().add(bookSection);
        
        // Create the HBox container for the friend's section
        
    }

}