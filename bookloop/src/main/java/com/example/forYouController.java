package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import org.checkerframework.checker.units.qual.N;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class forYouController {

    private ArrayList<String> comments = new ArrayList<>();

    @FXML
    private ResourceBundle resources;
     @FXML
    private Button displayCommBtn;
    @FXML
    void displayComments(ActionEvent event) {
        comments.add("book name  yorum  yazar");
        comments.add("book nmmmmm yyyy yazrar");
        comments.add("aaaa bbbb cccc dddd");
        comments.add("aaaaaaaaaaaa");

        int len = comments.size();
        Random rnd = new Random();
        int rndNum = rnd.nextInt(len-1); 
        textarea1.setText(comments.get(rndNum));
        textarea2.setText(comments.get(rndNum+1));
    }



    @FXML
    private URL location;

    @FXML
    private AnchorPane anchpane1;

    @FXML
    private AnchorPane anchpane2;

    @FXML
    private AnchorPane comms;

    @FXML
    private Text foryoutext;

    @FXML
    private Pane pane;

    @FXML
    private Pane pane2;

   @FXML
    private TextArea textarea1;

    @FXML
    private TextArea textarea2;

    @FXML
    private VBox vbox;

    @FXML
    void initialize() {
        assert anchpane1 != null : "fx:id=\"anchpane1\" was not injected: check your FXML file 'forYou.fxml'.";
        assert anchpane2 != null : "fx:id=\"anchpane2\" was not injected: check your FXML file 'forYou.fxml'.";
        assert displayCommBtn != null : "fx:id=\"displayCommBtn\" was not injected: check your FXML file 'forYou.fxml'.";
        assert comms != null : "fx:id=\"comms\" was not injected: check your FXML file 'forYou.fxml'.";
        assert foryoutext != null : "fx:id=\"foryoutext\" was not injected: check your FXML file 'forYou.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'forYou.fxml'.";
        assert pane2 != null : "fx:id=\"pane2\" was not injected: check your FXML file 'forYou.fxml'.";
        assert textarea1 != null : "fx:id=\"textarea1\" was not injected: check your FXML file 'forYou.fxml'.";
        assert textarea2 != null : "fx:id=\"textarea2\" was not injected: check your FXML file 'forYou.fxml'.";
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'forYou.fxml'.";

    }


}
