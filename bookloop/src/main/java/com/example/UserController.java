package com.example;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UserController {

    private User user = new User();
    @FXML
    private Label label1;
    @FXML
    private Label label2;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBookBtn;

    @FXML
    private Button addToTradebtn;

    @FXML
    private Pane addbookPane;

    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private TextField bookAuthorText;

    @FXML
    private TextField bookNameText;

    @FXML
    private Pane markedAsREadPane;

    @FXML
    private Text markedAsReadBaslik;

    @FXML
    private Button markedAsReadbtn;


    @FXML
    private MenuBar menubar1;

    @FXML
    private Pane profilePane;

    @FXML
    private ScrollBar scrollBar2;

    @FXML
    private ScrollBar scrollbar1;

    @FXML
    private Pane willTradeBoooksPane;

    @FXML
    private Text yourBooks;

    @FXML
    void initialize() {
        assert addBookBtn != null : "fx:id=\"addBookBtn\" was not injected: check your FXML file 'UserController.fxml'.";
        assert addToTradebtn != null : "fx:id=\"addToTradebtn\" was not injected: check your FXML file 'UserController.fxml'.";
        assert addbookPane != null : "fx:id=\"addbookPane\" was not injected: check your FXML file 'UserController.fxml'.";
        assert anchorpane1 != null : "fx:id=\"anchorpane1\" was not injected: check your FXML file 'UserController.fxml'.";
        assert bookAuthorText != null : "fx:id=\"bookAuthorText\" was not injected: check your FXML file 'UserController.fxml'.";
        assert bookNameText != null : "fx:id=\"bookNameText\" was not injected: check your FXML file 'UserController.fxml'.";
        assert markedAsREadPane != null : "fx:id=\"markedAsREadPane\" was not injected: check your FXML file 'UserController.fxml'.";
        assert markedAsReadBaslik != null : "fx:id=\"markedAsReadBaslik\" was not injected: check your FXML file 'UserController.fxml'.";
        assert menubar1 != null : "fx:id=\"menubar1\" was not injected: check your FXML file 'UserController.fxml'.";
        assert profilePane != null : "fx:id=\"profilePane\" was not injected: check your FXML file 'UserController.fxml'.";
        assert scrollBar2 != null : "fx:id=\"scrollBar2\" was not injected: check your FXML file 'UserController.fxml'.";
        assert scrollbar1 != null : "fx:id=\"scrollbar1\" was not injected: check your FXML file 'UserController.fxml'.";
        assert willTradeBoooksPane != null : "fx:id=\"willTradeBoooksPane\" was not injected: check your FXML file 'UserController.fxml'.";
        assert yourBooks != null : "fx:id=\"yourBooks\" was not injected: check your FXML file 'UserController.fxml'.";
        assert label1 != null : "fx:id=\"label1\" was not injected: check your FXML file 'UserController.fxml'.";
        assert label2 != null : "fx:id=\"label2\" was not injected: check your FXML file 'UserController.fxml'.";
        assert markedAsReadbtn != null : "fx:id=\"markedAsReadbtn\" was not injected: check your FXML file 'UserController.fxml'.";
    }

    @FXML
    void display(ActionEvent event) {
        String bookName =  bookNameText.getText();
        ArrayList<String> markedAsRead =user.hasRead(bookName);
        String listContent = String.join("\n", markedAsRead);


        label1.setText(listContent);
        bookNameText.clear();
       
    }
    @FXML
    void addTrade(ActionEvent event) {
        try {
            String bookName = bookNameText.getText();
            ArrayList<String> willTrade = user.add2Trade(bookName);
            String listContent = String.join("\n", willTrade);
            label2.setText(listContent);
            bookNameText.clear();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
