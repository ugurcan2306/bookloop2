package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.bookloop.firestore.FirestoreUtils;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

import client.FireStoreHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProfileContainer {
    User currentFriend;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchpane;

    @FXML
    private VBox bookVbox;

    @FXML
    private AnchorPane booksPane;

    @FXML
    private Text currBookText;

    @FXML
    private Button filterBtn;

    @FXML
    private HBox profileSection;

    @FXML
    private VBox profileVbox;

    @FXML
    private Button sendReqBtn;

    @FXML
    private Button sortBtn;

    @FXML
    private VBox vbox;

    @FXML
    void filterbtn(ActionEvent event) {

    }

    @FXML
    void sendReq(ActionEvent event) throws InterruptedException, ExecutionException {
        User currentUser= SessionManager.getCurrentUser();
        Firestore db=null;
        try {
            db = FireStoreHelper.getFirestore();
            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Trade Request");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Trade Request sent successfully!");

        // Show the alert and wait for user response
        alert.showAndWait();*/

            //showAlert("Trade Request", "Trade Request sent succesfully!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
        TradeRequest tr= new TradeRequest(currentUser.getUsername(), currentFriend.getUsername());
        FirestoreUtils.addTradeRequeststoTheFirestore(db, tr);
        currentUser.getSentTradeRequests().add(tr);
        currentFriend.getReceivedTradeRequests().add(tr);
        DocumentReference userRef = db.collection("users").document(currentUser.getUsername());
        userRef.update("SentTradeRequests", currentUser.getSentTradeRequests()).get();
        
        DocumentReference userRef2 = db.collection("users").document(currentFriend.getUsername());
        userRef2.update("ReceivedTradeRequests", currentFriend.getReceivedTradeRequests()).get();
        

    }

    @FXML
    void sortbtn(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert anchpane != null : "fx:id=\"anchpane\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert bookVbox != null : "fx:id=\"bookVbox\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert booksPane != null : "fx:id=\"booksPane\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert currBookText != null : "fx:id=\"currBookText\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert filterBtn != null : "fx:id=\"filterBtn\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert profileSection != null : "fx:id=\"profileSection\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert profileVbox != null : "fx:id=\"profileVbox\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert sendReqBtn != null : "fx:id=\"sendReqBtn\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert sortBtn != null : "fx:id=\"sortBtn\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'profileContainer.fxml'.";
        
    }

    @FXML
    void profileView(String userName){
        currentFriend= FinderFromDatabase.UserFinder(userName);
        profileSection = new HBox(10); // Spacing of 10 between elements

        // Create avatar placeholder (can be an ImageView instead)
        Label avatar = new Label("👤"); // Replace with an actual ImageView for real avatars
        avatar.setStyle("-fx-font-size: 36px;");

        // Create username label
        Label usernameLabel = new Label(currentFriend.getUsername());
        profileSection.getChildren().addAll(avatar, usernameLabel);
        profileVbox.getChildren().add(profileSection);

        // Add the friend's section to the main VBox container
        //vbox.getChildren().add(profileSection);

    }
    @FXML
    public void setTradelencek(User currentFriend){
        
        ArrayList<Book> canTrade = currentFriend.getBookstoTrade();
        //currentFriend= FinderFromDatabase.UserFinder(username);
        for(int i = 0; i<canTrade.size(); i++){
            HBox bookSection = new HBox(10); // Spacing of 10 between elements

            // Create avatar placeholder (can be an ImageView instead)
                Label avatar = new Label("📚"); // Replace with an actual ImageView for real avatars
                avatar.setStyle("-fx-font-size: 36px;");
        
            // Create username label
                Label booknameLabel = new Label(canTrade.get(i).getName());
        
        
        
            // Add elements to the HBox
            bookSection.getChildren().addAll(avatar, booknameLabel);
        
            // Add the friend's section to the main VBox container
            bookVbox.getChildren().add(bookSection);
        
        }
        
    }
    /*public void setCurrentFriend(User currentFriend) {
        this.currentFriend = currentFriend;
    }*/
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
