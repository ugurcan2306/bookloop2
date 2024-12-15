package com.example;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProfileContainer{

    private User currentFriend;

   public ProfileContainer(User currentFriend ) {
        this.currentFriend = currentFriend;
    }

    // @FXML
    /*void addBooks2Pane(User user){
       ArrayList<Book> canTrade = user.getWillTrade();
       String listContent = String.join("\n", willTrade);
       label2.setText(listContent);
       for(int i = 0; i<willTrade.size(); i++){
        if(chooseBookTextField.getText().equals(willTrade.get(i))){
            
        }
       }
       
            
    }*/
    @FXML
    void sendReq(ActionEvent event){

    }

     void setTradelencek(){
        ArrayList<Book> canTrade = currentFriend.getBookstoTrade();
        for(int i = 0; i<canTrade.size(); i++){
            label2.setText(canTrade.get(i).getName());
        }
        
    }


    

    @FXML
    private ResourceBundle resources;
    @FXML
    private Button sendReqBtn;
    @FXML
    private Label label2;
     @FXML
    private TextField chooseBookTextField;


    @FXML
    private URL location;

    @FXML
    private AnchorPane anchpane;

    @FXML
    private AnchorPane booksPane;

    @FXML
    private Text currBookText;

    @FXML
    private Button filterBtn;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private ScrollBar scrollPane;

    @FXML
    private Button sortBtn;

    @FXML
    private VBox vbox;

    @FXML
    void initialize() {
        assert anchpane != null : "fx:id=\"anchpane\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert booksPane != null : "fx:id=\"booksPane\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert currBookText != null : "fx:id=\"currBookText\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert filterBtn != null : "fx:id=\"filterBtn\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert profilePane != null : "fx:id=\"profilePane\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert sortBtn != null : "fx:id=\"sortBtn\" was not injected: check your FXML file 'profileContainer.fxml'.";
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'profileContainer.fxml'.";

    }

    

}
