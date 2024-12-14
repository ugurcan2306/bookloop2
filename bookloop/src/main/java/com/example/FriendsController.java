package com.example;

//import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.HBox;


public class FriendsController {

    private User user = new User();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField enterUsrnameField;


    @FXML
    private Button addFriendbtn;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private VBox friendsContainer;
    @FXML
    private ProfileContainer profileContainer;
    

    @FXML
    private Text friendstext;

    //static HBox friendSection;

    @FXML
    void initialize() {
        assert addFriendbtn != null : "fx:id=\"addFriendbtn\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert enterUsrnameField != null : "fx:id=\"enterUsrnameField\" was not injected: check your FXML file 'FriendsController.fxml'.";
        assert anchorpane != null : "fx:id=\"anchorpane\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert friendsContainer != null : "fx:id=\"friendsContainer\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert friendstext != null : "fx:id=\"friendstext\" was not injected: check your FXML file 'Untitled.fxml'.";

    }

    

    public void setProfileContainer(ProfileContainer profileContainer) {
        this.profileContainer = profileContainer; // Set the profileContainer when initializing
    }
    @FXML
    void addFriend(ActionEvent event) {
        String username = enterUsrnameField.getText();
        addFriend(username);
        
    }

    // Method to add a new friend section
    public void addFriend(String username) {
        
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

        // Add event handling (optional)
        seeProfileButton.setOnAction(event -> handleSeeProfile(event));
        startChatButton.setOnAction(event -> {
            friendsContainer.setVisible(false);
        });
        removeFriendButton.setOnAction(event -> {
            friendsContainer.getChildren().remove(friendSection);
        });

        // Add elements to the HBox
        friendSection.getChildren().addAll(avatar, usernameLabel, seeProfileButton, startChatButton, removeFriendButton);

        // Add the friend's section to the main VBox container
        friendsContainer.getChildren().add(friendSection);
        
        // Create the HBox container for the friend's section
        
    }
    @FXML
void handleSeeProfile(ActionEvent event) {
    System.out.println("aaaaa");
    friendsContainer.setVisible(false); // Hide friends container
    profileContainer.setVisible(true); // Show profile container
}
    
    

}


