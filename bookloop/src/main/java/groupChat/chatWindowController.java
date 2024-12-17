package groupChat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class chatWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox hbox;

    @FXML
    private TextArea messagesArea;

    @FXML
    private Button sendbtn;

    @FXML
    private TextField textField;

    @FXML
    private VBox vbox;

    @FXML
    void initialize() {
        assert hbox != null : "fx:id=\"hbox\" was not injected: check your FXML file 'chatWindow.fxml'.";
        assert messagesArea != null : "fx:id=\"messagesArea\" was not injected: check your FXML file 'chatWindow.fxml'.";
        assert sendbtn != null : "fx:id=\"sendbtn\" was not injected: check your FXML file 'chatWindow.fxml'.";
        assert textField != null : "fx:id=\"textField\" was not injected: check your FXML file 'chatWindow.fxml'.";
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'chatWindow.fxml'.";

    }

    private String chatId;
    private String username;
    private FirestoreService firestoreService;

    public void initializeChat(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
        this.firestoreService = new FirestoreService();

        // Real-time message updates
        firestoreService.getMessagesForChatRealTime(chatId, messagesArea);

        // Send message logic
        sendbtn.setOnAction(event -> {
            String message = textField.getText();
            if (!message.isEmpty()) {
                firestoreService.addMessageToChat(chatId, username, message);

                Platform.runLater(() -> {
                    messagesArea.appendText(username + ": " + message + "\n");
                });

                textField.clear();
            }
        });
    }
}