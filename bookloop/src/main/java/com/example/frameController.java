package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle.Control;

import groupChat.chatWindowController;
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
    private User currentUser =SessionManager.getCurrentUser();
    private User currentFriend;
    private String chatId;
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
    void openGroupch(String fxml) {
        friendsChatsPane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            VBox panel = loader.load();
            friendsChatsPane.getChildren().add(panel);
            chatWindowController controller = loader.getController();
            //System.out.println(currentUser.getUsername());
            controller.initializeChat(chatId, currentUser.getUsername()); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showRecTradeReqs(String fxml) throws IOException {
        friendsChatsPane.getChildren().clear();
        /*URL resource = getClass().getResource("/com/bookloop/trade.fxml");
        if (resource == null) {
            System.out.println("Resource not found!");
        } else {
            System.out.println("Resource found: " + resource);
        }*/
        //friendsChatsPane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            VBox panel = loader.load();
            friendsChatsPane.getChildren().add(panel);
            ReceivedTradeController received = loader.getController();
            //traderequest objesi lazım
            //TradeRequest tr = new TradeRequest();
            //currentFriend.setUsername(tr.getSendername());
            received.showRecRequests(currentUser.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tradeRequest.fxml"));
        //VBox panel = loader.load();
        //loader.setController(ProfileContainer);
        //ReceivedTradeController received = loader.getController();
        //received.showRecRequests(currentUser.getUsername());
    }
    @FXML
    void showMyTradeReqs(String fxml) throws IOException {
        friendsChatsPane.getChildren().clear();
        /*URL resource = getClass().getResource("/com/bookloop/trade.fxml");
        if (resource == null) {
            System.out.println("Resource not found!");
        } else {
            System.out.println("Resource found: " + resource);
        }*/
        //friendsChatsPane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            VBox panel = loader.load();
            friendsChatsPane.getChildren().add(panel);
            myTradeReqsController sent = loader.getController();
            //traderequest objesi lazım
            //TradeRequest tr = new TradeRequest();
            //currentFriend.setUsername(tr.getSendername());
            sent.showMyRecRequests(currentUser.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tradeRequest.fxml"));
        //VBox panel = loader.load();
        //loader.setController(ProfileContainer);
        //ReceivedTradeController received = loader.getController();
        //received.showRecRequests(currentUser.getUsername());
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
    private AnchorPane anchRec1;
    @FXML
    private Button friendchatbtn;

    @FXML
    private AnchorPane anchorPaneRec2;
    @FXML
    private AnchorPane friendsChatsPane;

    @FXML
    private AnchorPane getRecsPane;
    @FXML
    private ScrollPane scrPaneRec2;

    @FXML
    private ScrollPane scrPaneforRec1;


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
    private Button groupCh1;

    
    @FXML
    private Button groupch2;

    @FXML
    private Button groupch3;

    @FXML
    private Button groupch4;
    @FXML
    private ScrollPane scrpane;
    @FXML
    void goGroupCh1(ActionEvent event) {
        friendsChatsPane.setVisible(true);
        SessionManager.setCurrentUser("ugurcan23");
        chatId = "animalfarm";
        openGroupch("/chatWindow.fxml");
    }

    @FXML
    void gogroupch2(ActionEvent event) {
        friendsChatsPane.setVisible(true);
        SessionManager.setCurrentUser("ugurcan23");
        chatId = "1984";
        openGroupch("/chatWindow.fxml");

    }

    @FXML
    void gogroupch3(ActionEvent event) {
        friendsChatsPane.setVisible(true);
        SessionManager.setCurrentUser("ugurcan23");
        chatId = "bravenewworld";
        openGroupch("/chatWindow.fxml");

    }

    @FXML
    void groupch4(ActionEvent event) {
        friendsChatsPane.setVisible(true);
        SessionManager.setCurrentUser("ugurcan23");
        chatId = "fahrenheit451";
        openGroupch("/chatWindow.fxml");

    }

   

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
        showFriendChat("forYouFeed.fxml");


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
    void showMyTradeReqs(ActionEvent event) throws IOException {
        friendsChatsPane.setVisible(true);
        showMyTradeReqs("myTradeReqs.fxml");
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
    void showtradeReqs(ActionEvent event) throws IOException {
        /*forYouPane.setVisible(false);
        profilePane.setVisible(false);
        settingsPane.setVisible(false);
        tradeReqsPane.setVisible(true);
        myTradeReqsPane.setVisible(false);
        getRecsPane.setVisible(false);
        friendsChatsPane.setVisible(false);
        logOutPane.setVisible(false);*/
       

        friendsChatsPane.setVisible(true);
        showRecTradeReqs("tradeRequest.fxml");

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
