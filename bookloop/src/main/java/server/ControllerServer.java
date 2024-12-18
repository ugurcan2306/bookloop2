package server;

import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ControllerServer implements Initializable   {
    @FXML
    private VBox vBox;

    @FXML
    private Button sendButton;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField textField;

    private Server server;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            server= new Server(new ServerSocket(1234));
         
            
        } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error");
        }
       
        vBox.heightProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               scroll.setVvalue((Double)newValue);
            }
            
        });
        server.receiveMessageFromClient(vBox);

        sendButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              String messageToSend= textField.getText();
              if (!messageToSend.isEmpty()) {
                HBox hBox= new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));

                Text text= new Text(messageToSend);
                TextFlow textFlow= new TextFlow(text);
                textFlow.setStyle("-fx-color: rgb(239,242,255);"+"-fx-background-color: rgb(15,125,242);"+"-fx-background-radius: 20px;"); 
                textFlow.setPadding(new Insets(5, 10, 5, 10));
                text.setFill(Color.color(0.934, 0.945,1));
                hBox.getChildren().add(textFlow);
                vBox.getChildren().add(hBox);
                server.sendMessageToClient(messageToSend);
                textField.clear();

              }
            }
            
            
            
        });
               
    }
    public static void addLabel(String messageFromClient, VBox vbox){
        HBox hbox= new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(5, 5, 5, 10));
        Text text= new Text(messageFromClient);
        TextFlow flow= new TextFlow(text);
        flow.setStyle("fx-background-color: rgb(233,233,235);"+"-fx-background-radius: 20px;"); 
        flow.setPadding(new Insets(5,10,5,10));
        hbox.getChildren().add(flow);

        Platform.runLater(new Runnable() {

            @Override 
            public void run() {
               vbox.getChildren().add(hbox);
            }
            
        });
    }

    
}