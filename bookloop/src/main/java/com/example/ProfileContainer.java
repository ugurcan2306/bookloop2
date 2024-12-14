package com.example;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ProfileContainer {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox profileContainer;
    @FXML
    private Button btn;

    private FriendsController friendsController;

    @FXML
    void initialize() {
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'ProfileContainer.fxml'.";
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ProfileContainer.fxml'.";
        assert profileContainer != null : "fx:id=\"profileComntainer\" was not injected: check your FXML file 'ProfileContainer.fxml'.";
    }

    public ProfileContainer(VBox ProfileContainer){
        this.profileContainer = profileContainer;
    }
    public void setFriendsController(FriendsController friendsController) {
        this.friendsController = friendsController;
    }

    public void setVisible(boolean visible) {
        profileContainer.setVisible(visible);
    }

     


   
}
