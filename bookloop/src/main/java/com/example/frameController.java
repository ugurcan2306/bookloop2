package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle.Control;

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

public class frameController {
    //private frameController frameController = new frameController();
    // fx:id of the container where you place the panel

    @FXML
    void initialize(String fxml) {
        //friendsChatsPane.getChildren().clear();
        showFriendChat(fxml);
  
        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserController.fxml"));
            VBox panel = loader.load();
            profilePane.getChildren().add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    @FXML
    void showForYouBtn(String fxml) {
        /*friendsChatsPane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("forYou.fxml"));
            VBox panel = loader.load();
            forYouPane.getChildren().add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    @FXML
    void showFriendChat(String fxml) {
        friendsChatsPane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            VBox panel = loader.load();
            friendsChatsPane.getChildren().add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void showRecTradeReqs(String fxml) {
        friendsChatsPane.getChildren().clear();
        /*URL resource = getClass().getResource("/com/bookloop/trade.fxml");
        if (resource == null) {
            System.out.println("Resource not found!");
        } else {
            System.out.println("Resource found: " + resource);
        }*/
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            VBox panel = loader.load();
            tradeReqsPane.getChildren().add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button addBookBtn;

    @FXML
    private Button addToTradebtn;

    @FXML
    private Pane addbookPane;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private AnchorPane bokloopanch;

    @FXML
    private TextField bookAuthorText;

    @FXML
    private TextField bookNameText;

    @FXML
    private AnchorPane forYouPane;

    @FXML
    private Button foryoubtn;

    @FXML
    private Button friendchatbtn;

    @FXML
    private AnchorPane friendsChatsPane;

    @FXML
    private AnchorPane getRecsPane;

    @FXML
    private Button getrecbtn;

    @FXML
    private Text groupChats;

    @FXML
    private AnchorPane groupchanch;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private AnchorPane logOutPane;

    @FXML
    private Button logoutbtn;

    @FXML
    private Pane markedAsREadPane;

    @FXML
    private Text markedAsReadBaslik;

    @FXML
    private Button markedAsReadbtn;

    @FXML
    private MenuBar menubar;

    @FXML
    private AnchorPane myTradeReqsPane;

    @FXML
    private Button mytradebtn;

    @FXML
    private AnchorPane ortaanch;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private Pane profilePane1;

    @FXML
    private Button profilebtn;

    @FXML
    private AnchorPane recanch;

    @FXML
    private Text reclabel;

    @FXML
    private Text rectext;

    @FXML
    private ScrollBar scrol2;

    @FXML
    private ScrollBar scrol3;

    @FXML
    private ScrollBar scroll1;

    @FXML
    private ScrollBar scrollBar2;

    @FXML
    private ScrollBar scrollbar1;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private AnchorPane searchanch;

    @FXML
    private Button searchbtn;

    @FXML
    private TextField searchtextf;

    @FXML
    private AnchorPane settingsPane;

    @FXML
    private Button settingsbtn;

    @FXML
    private SplitPane splitpane;

    @FXML
    private Label text1;

    @FXML
    private AnchorPane tradeReqsPane;

    @FXML
    private Button tradebtn;

    @FXML
    private VBox vbox;

    @FXML
    private Pane willTradeBoooksPane;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Text yourBooks;

   

    @FXML
    void addTrade(ActionEvent event) {
        //frameController.addTrade(event);
    }

    @FXML
    void display(ActionEvent event) {
        //frameController.display(event);
    }

    @FXML
    void logOutBtn(ActionEvent event) {

    }

    @FXML
    void searchBtn(ActionEvent event) {

    }

    @FXML
    void showForYou(ActionEvent event) {
        /*forYouPane.setVisible(true);
        profilePane.setVisible(false);
        settingsPane.setVisible(false);
        tradeReqsPane.setVisible(false);
        myTradeReqsPane.setVisible(false);
        getRecsPane.setVisible(false);
        friendsChatsPane.setVisible(false);
        logOutPane.setVisible(false);*/
        friendsChatsPane.setVisible(true);
        showFriendChat("forYou.fxml");


    }

    @FXML
    void showFriendsChats(ActionEvent event) {
        /*forYouPane.setVisible(false);
        profilePane.setVisible(false);
        settingsPane.setVisible(false);
        tradeReqsPane.setVisible(false);
        myTradeReqsPane.setVisible(false);
        getRecsPane.setVisible(false);
        //friendsChatsPane.setVisible(false);
        logOutPane.setVisible(false);*/

        friendsChatsPane.setVisible(true);
        showFriendChat("FriendsController.fxml");
       // showOnlyPane(friendsChatsPane);
        

    }

    @FXML
    void showMyTradeReqs(ActionEvent event) {

    }

    @FXML
    void showProfile(ActionEvent event) {
        //recommendationsPlace.setText("Displaying profile information...");
        /*forYouPane.setVisible(false);
        profilePane.setVisible(true);
        settingsPane.setVisible(false);
        tradeReqsPane.setVisible(false);
        myTradeReqsPane.setVisible(false);
        getRecsPane.setVisible(false);
        friendsChatsPane.setVisible(false);
        logOutPane.setVisible(false);

        profilePane.setVisible(true);
        //friendsChatsPane.setVisible(false);*/
        friendsChatsPane.setVisible(true);
        initialize("UserController.fxml");
        //showOnlyPane(profilePane);

    }

    @FXML
    void showRecoms(ActionEvent event) {

    }

    @FXML
    void showSettings(ActionEvent event) {

    }

    @FXML
    void showtradeReqs(ActionEvent event) {
        /*forYouPane.setVisible(false);
        profilePane.setVisible(false);
        settingsPane.setVisible(false);
        tradeReqsPane.setVisible(true);
        myTradeReqsPane.setVisible(false);
        getRecsPane.setVisible(false);
        friendsChatsPane.setVisible(false);
        logOutPane.setVisible(false);*/
        friendsChatsPane.setVisible(true);
        showFriendChat("/com/bookloop/trade.fxml");

    }

    private void showOnlyPane(AnchorPane visiblePane) {
        forYouPane.setVisible(false);
        profilePane.setVisible(false);
        settingsPane.setVisible(false);
        tradeReqsPane.setVisible(false);
        myTradeReqsPane.setVisible(false);
        getRecsPane.setVisible(false);
        friendsChatsPane.setVisible(false);
        logOutPane.setVisible(false);

        if (visiblePane != null) {
            visiblePane.setVisible(true);
            System.out.println("görünür 284");
        }
        else {
            System.out.println("boş 286");;
        }
    }

}
