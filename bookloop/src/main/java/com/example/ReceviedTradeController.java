package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ReceviedTradeController {

    @FXML
    private Label TradeRequestLabel;

    @FXML
    private Button acceptButtonForUser1;

    @FXML
    private Label label1booktrade;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;

    @FXML
    void AcceptButtonPressed1(ActionEvent event) {
        try {
        pane1.getChildren().clear();

        // Load the new FXML file for the different interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bookloop/server.fxml"));
        Parent newContent = loader.load();
    
        // Add the new content to pane1
        pane1.getChildren().add(newContent);
        } catch (Exception e) {
            e.printStackTrace();  // Handle any exceptions that occur during loading
        }
    }
    

    @FXML
    void AcceptButtonPressed2(ActionEvent event) {

    }

    @FXML
    void AcceptButtonPressed3(ActionEvent event) {

    }

    @FXML
    void RejectButtonPressed1(ActionEvent event) {

    }

    @FXML
    void RejectButtonPressed2(ActionEvent event) {

    }

    @FXML
    void RejectButtonPressed3(ActionEvent event) {

    }

}