package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import client.FireStoreHelper;
import groupChat.chatWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ReceivedTradeController {
    private User currentFriend;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Text basliktext;

    @FXML
    private VBox buttonluVbox;

    @FXML
    private VBox mainvbox;

    @FXML
    void initialize() throws IOException {
        assert anchorpane != null : "fx:id=\"anchorpane\" was not injected: check your FXML file 'tradeRequest.fxml'.";
        assert basliktext != null : "fx:id=\"basliktext\" was not injected: check your FXML file 'tradeRequest.fxml'.";
        assert buttonluVbox != null : "fx:id=\"buttonluVbox\" was not injected: check your FXML file 'tradeRequest.fxml'.";
        assert mainvbox != null : "fx:id=\"mainvbox\" was not injected: check your FXML file 'tradeRequest.fxml'.";
        loadRequestsFromFirestore();
    }
    private void loadRequestsFromFirestore() throws IOException {
        try {
            Firestore db = FireStoreHelper.getFirestore();
    
            // Reference to ReceivedTradeRequests subcollection
            CollectionReference recTraRef = db.collection("users")
                .document(SessionManager.getCurrentUser().getUsername())
                .collection("ReceivedTradeRequests");
    
            // Fetch the data
            QuerySnapshot collection = recTraRef.get().get();
    
            // Clear previous UI elements
            //mainvbox.getChildren().clear();
    
            // Loop through the documents
            for (DocumentSnapshot document : collection.getDocuments()) {
                // Convert Firestore data to TradeRequest object
                TradeRequest tradeRequest = document.toObject(TradeRequest.class);
    
                if (tradeRequest != null) {
                    // Access the receiver's username
                    String receiverUsername = tradeRequest.getSendername();
                    showRecRequests(receiverUsername);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while loading trade requests: " + e.getMessage());
        }
    }
    
    @FXML
    void showRecRequests(String username) throws IOException {
 //String username = receiverUsername;
    
    //currentFriend= FinderFromDatabase.UserFinder(username);
    HBox requestSection = new HBox(10); // Spacing of 10 between elements

    // Create avatar placeholder (can be an ImageView instead)
    Label avatar = new Label("👤"); // Replace with an actual ImageView for real avatars
    avatar.setStyle("-fx-font-size: 36px;");

    // Create username label
    Label usernameLabel = new Label(username);

    // Create buttons
    Button acceptButton = new Button("Accept");
    //Button startChatButton = new Button("Start Chat");
    Button rejectButton = new Button("Reject");

    // Add event handling
    acceptButton.setOnAction(event -> AcceptButtonPressed1(event));
    rejectButton.setOnAction(event -> {
        buttonluVbox.getChildren().remove(requestSection);
        /*currentUser.getFriends().remove(FinderFromDatabase.UserFinder(currentFriend.getUsername()));
        
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
               
            }*/
    });

    // Add elements to the HBox
    requestSection.getChildren().addAll(avatar, usernameLabel, acceptButton, rejectButton);

    // Add the friend's section to the main VBox container
    buttonluVbox.getChildren().add(requestSection);
}

@FXML
    void AcceptButtonPressed1(ActionEvent event) {
       mainvbox.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatWindow.fxml"));
            VBox panel = loader.load();
            mainvbox.getChildren().add(panel);
            chatWindowController controller = loader.getController();
            //System.out.println(currentUser.getUsername());
            controller.initializeChat("animalfarm", "ugurcan23"); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}