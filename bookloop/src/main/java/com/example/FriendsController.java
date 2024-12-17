package com.example;

//import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.example.FinderFromDatabase;

import client.FireStoreHelper;
import com.example.ProfileContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;


public class FriendsController {

    private User currentUser = SessionManager.getCurrentUser();
    private User currentFriend;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addFriendbtn;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TextField enterUsrnameField;

    @FXML
    private Text friendstext;

    @FXML
    private VBox mainVbox;

    @FXML
    private ScrollPane scroll2friend;

    @FXML
    private VBox vboxscr;
    @FXML
    private VBox ustVbox;


    @FXML
    void initialize() throws IOException {
        assert addFriendbtn != null : "fx:id=\"addFriendbtn\" was not injected: check your FXML file 'FriendsController.fxml'.";
        assert anchorpane != null : "fx:id=\"anchorpane\" was not injected: check your FXML file 'FriendsController.fxml'.";
        assert enterUsrnameField != null : "fx:id=\"enterUsrnameField\" was not injected: check your FXML file 'FriendsController.fxml'.";
        assert ustVbox != null : "fx:id=\"ustVbox\" was not injected: check your FXML file 'FriendsController.fxml'.";
        assert friendstext != null : "fx:id=\"friendstext\" was not injected: check your FXML file 'FriendsController.fxml'.";
        assert mainVbox != null : "fx:id=\"mainVbox\" was not injected: check your FXML file 'FriendsController.fxml'.";
        assert scroll2friend != null : "fx:id=\"scroll2friend\" was not injected: check your FXML file 'FriendsController.fxml'.";
        assert vboxscr != null : "fx:id=\"vboxscr\" was not injected: check your FXML file 'FriendsController.fxml'.";
        loadFriendsFromFirestore();

    }

    private void loadFriendsFromFirestore() throws IOException {
        
        try {
            Firestore db = FireStoreHelper.getFirestore();
            DocumentReference userRef = db.collection("users").document(currentUser.getUsername());
            ApiFuture<DocumentSnapshot> future = userRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                // Firestore'dan "Friends" verisini al
                ArrayList<Map<String, Object>> friendsDataList = (ArrayList<Map<String, Object>>) document.get("Friends");
                if (friendsDataList != null) {
                    // Ekranı temizle, eski verileri kaldır
                    vboxscr.getChildren().clear();
                    
                    for (Map<String, Object> friendData : friendsDataList) {
                        // Firestore verilerini User nesnesine dönüştür
                        String username = (String) friendData.get("username");
                        System.out.println(username);
                        System.out.println(username);
                        System.out.println(username);
                        System.out.println(username);
                        System.out.println(username);
                        System.out.println(username);
                        addFriendToUI(username);
                    }
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

   
    @FXML
void addFriend(ActionEvent event) throws Exception {
    String username = enterUsrnameField.getText();
    

    // Step 1: Handle database logic
    boolean isFriendAdded = addFriendToDatabase(username);

    // Step 2: Update UI if the friend was added successfully
    if (isFriendAdded) {
        addFriendToUI(username);
    }
    enterUsrnameField.clear();
}

/**
 * Handles the logic for finding and adding the friend to the database.
 *
 * @param username The username of the friend to add.
 * @return true if the friend was successfully added, false otherwise.
 * @throws Exception if an error occurs during database operations.
 */
private boolean addFriendToDatabase(String username) throws Exception {
    currentFriend = FinderFromDatabase.UserFinder(username);

    if (currentFriend != null &&!currentUser.getFriends().contains(currentFriend)) {
        Firestore db = FireStoreHelper.getFirestore();
        DocumentReference userRef = db.collection("users").document(currentUser.getUsername());

        // Add to local friend list and update Firestore
        currentUser.addToFriends(currentFriend);
        userRef.update("Friends", currentUser.getFriends()).get();

        return true;
    }

    return false; // Friend not found
}
@FXML
void addFriendToUI(String username) throws IOException {
   
    currentFriend= FinderFromDatabase.UserFinder(username);
    HBox friendSection = new HBox(10); // Spacing of 10 between elements

    // Create avatar placeholder (can be an ImageView instead)
    Label avatar = new Label("👤"); // Replace with an actual ImageView for real avatars
    avatar.setStyle("-fx-font-size: 36px;");

    // Create username label
    Label usernameLabel = new Label(username);

    // Create buttons
    Button seeProfileButton = new Button("See Profile");
    Button startChatButton = new Button("Start Chat");
    Button removeFriendButton = new Button("Remove Friend");

    // Add event handling
    seeProfileButton.setOnAction(event -> handleSeeProfile(event));
    startChatButton.setOnAction(event -> vboxscr.setVisible(false));
    removeFriendButton.setOnAction(event -> {
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
    });

    // Add elements to the HBox
    friendSection.getChildren().addAll(avatar, usernameLabel, seeProfileButton, startChatButton, removeFriendButton);

    // Add the friend's section to the main VBox container
    vboxscr.getChildren().add(friendSection);
}
@FXML
void handleSeeProfile(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/profileContainer.fxml"));
        VBox panel = loader.load();
        //loader.setController(ProfileContainer);
        ProfileContainer profileContainer = loader.getController();
        profileContainer.setTradelencek(currentFriend);
        profileContainer.setCurrentFriend(currentFriend);
        profileContainer.profileView(currentFriend);
        ustVbox.getChildren().clear();
        vboxscr.getChildren().clear();
        vboxscr.getChildren().add(panel);
    } catch (IOException e) {
        e.printStackTrace();}
        
}

@FXML
void handleStartChat(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bookloop/client.fxml"));
        VBox panel = loader.load();
        vboxscr.getChildren().clear();
        vboxscr.getChildren().add(panel);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public User getCurrentFriend() {
    return currentFriend;
}
    
}