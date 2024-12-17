package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import client.FireStoreHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class myTradeReqsController {

    User currentFriend;
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
    private Text text;

    @FXML
    void initialize() throws IOException {
        assert altvbox != null : "fx:id=\"altvbox\" was not injected: check your FXML file 'myTradeReqs.fxml'.";
        assert anchorpane != null : "fx:id=\"anchorpane\" was not injected: check your FXML file 'myTradeReqs.fxml'.";
        assert mainvbox != null : "fx:id=\"mainvbox\" was not injected: check your FXML file 'myTradeReqs.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'myTradeReqs.fxml'.";
        loadRequestsFromFirestore();
    }
    void loadRequestsFromFirestore() throws IOException {
        try {
            Firestore db = FireStoreHelper.getFirestore();
            // Reference to the user document
            DocumentReference userDocRef = db.collection("users")
            .document(SessionManager.getCurrentUser().getUsername());
    
            // Fetch the document
            DocumentSnapshot userDoc = userDocRef.get().get();
           
            // Check if the document exists and contains the ReceivedTradeRequests field
            if (userDoc.exists()) {
                // Retrieve the ReceivedTradeRequests field as a List of Maps
                ArrayList<Map<String, Object>> sentTradeRequests = (ArrayList<Map<String, Object>>) userDoc.get("SentTradeRequests");
    
                if (sentTradeRequests != null) {
                    // Loop through each trade request map
                    for (Map<String, Object> tradeRequestMap : sentTradeRequests) {
                        // Manually map the map to a TradeRequest object
                        TradeRequest tradeRequest = new TradeRequest();
                        
                        // Assuming your TradeRequest class has methods to set the fields
                        tradeRequest.setSendername((String) tradeRequestMap.get("sendername"));
                        tradeRequest.setReceivername((String) tradeRequestMap.get("receivername"));
                        // Add other fields as necessary
    
                        // Now you can use the tradeRequest object
                        String receivedUserName = tradeRequest.getReceivername();
                        showMyRecRequests(receivedUserName);  // Call your method to display requests
                    }
                } else {
                    System.out.println("No received trade requests found.");
                }
            } else {
                System.out.println("User document does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while loading trade requests: " + e.getMessage());
        }
    }
    
    
    @FXML
    void showMyRecRequests(String username) throws IOException {
    User currentUser= SessionManager.getCurrentUser();
    
    currentFriend= FinderFromDatabase.UserFinder(username);
   
    HBox requestSection = new HBox(10); // Spacing of 10 between elements

    // Create avatar placeholder (can be an ImageView instead)
    Label avatar = new Label("📚"); // Replace with an actual ImageView for real avatars
    avatar.setStyle("-fx-font-size: 36px;");

    // Create username label
    Label usernameLabel = new Label(username);
    Label yaziLabel = new Label("You have sent a book trade request to" + currentFriend);

    // Create buttons
    Button cancelButton = new Button("Cancel");
    //Button startChatButton = new Button("Start Chat");
    //Button rejectButton = new Button("Reject");

    // Add event handling
    //acceptButton.setOnAction(event -> AcceptButtonPressed1(event, username));
    cancelButton.setOnAction(event -> {
        altvbox.getChildren().remove(requestSection);
        System.out.println(currentUser.getUsername());
        System.out.println(currentFriend.getUsername());
        TradeRequest currRequest = FinderFromDatabase.TradeReqFinder(currentUser.getUsername(), currentFriend.getUsername());
        currentUser.getSentTradeRequests().remove(currRequest);
        currentFriend.getReceivedTradeRequests().remove(currRequest);
        
        Firestore db;
            try {
                db = FireStoreHelper.getFirestore();
                DocumentReference tradeRef = db.collection("users").document(currentUser.getUsername());
                tradeRef.update("SentTradeRequests", currentUser.getSentTradeRequests()).get();
                DocumentReference tradeRef2 = db.collection("users").document(currentFriend.getUsername());
                tradeRef2.update("ReceivedTradeRequests", currentFriend.getReceivedTradeRequests()).get();
                db.collection("tradeRequests").document(currRequest.toString()).delete();

            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
               
            }
    });

    // Add elements to the HBox
    requestSection.getChildren().addAll(avatar, usernameLabel, yaziLabel, cancelButton);

    // Add the friend's section to the main VBox container
    altvbox.getChildren().add(requestSection);
}

}
