package oneToOneChat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchpane;

    @FXML
    private Button sendbtn;

    @FXML
    private TextArea textarea;

    @FXML
    private TextField textfield;

    @FXML
    private VBox vbox;

    @FXML
    void sendbutton(ActionEvent event) {
        String message = textfield.getText();
            if (!message.isEmpty()) {
                firestoreService.addMessageToChat(chatId, username, message);
                Platform.runLater(() -> textarea.appendText(username + ": " + message + "\n"));
                textfield.clear();
            }
    }

    @FXML
    void initialize() {
        assert anchpane != null : "fx:id=\"anchpane\" was not injected: check your FXML file 'one2onechatWindow.fxml'.";
        assert sendbtn != null : "fx:id=\"sendbtn\" was not injected: check your FXML file 'one2onechatWindow.fxml'.";
        assert textarea != null : "fx:id=\"textarea\" was not injected: check your FXML file 'one2onechatWindow.fxml'.";
        assert textfield != null : "fx:id=\"textfield\" was not injected: check your FXML file 'one2onechatWindow.fxml'.";
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'one2onechatWindow.fxml'.";

    }
    private String chatId;
    private String username;
    private Stage chatStage;
    private FirestoreService firestoreService;

    /*public WindowController(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
        firestoreService = new FirestoreService();
        //initializeWindow();
    }*/
    

    public void initializeWindow(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
        firestoreService = new FirestoreService();

        //chatStage = new Stage();
        //chatStage.setTitle("Chat - " + username);

        //VBox chatLayout = new VBox(10);
        //messageArea = new TextArea();
        //messageArea.setEditable(false);

        //inputField = new TextField();
        //sendButton = new Button("Send");

        /*HBox inputLayout = new HBox(10, textfield, sendbtn);
        inputLayout.setPadding(new javafx.geometry.Insets(5));

        vbox.getChildren().addAll(textarea, inputLayout);
        Scene chatScene = new Scene(vbox, 500, 400);
        chatStage.setScene(chatScene);*/

        // Firestore'dan mesajları dinleme
        firestoreService.getMessagesForChatRealTime(chatId, textarea);

        // Mesaj gönderme işlemi
        //sendbtn.setOnAction(event -> sendbutton(event));

        //chatStage.show();
    }


}
