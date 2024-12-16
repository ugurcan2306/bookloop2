package oneToOneChat;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ChatWindow {

    private String chatId;
    private String username;
    private Stage chatStage;
    private FirestoreService firestoreService;
    private TextArea messageArea;
    private TextField inputField;
    private Button sendButton;

    // Yapıcı metod
    public ChatWindow(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
        firestoreService = new FirestoreService();
        initializeWindow();
    }

    // Sohbet penceresini başlatan metod
    private void initializeWindow() {
        chatStage = new Stage();
        chatStage.setTitle("Chat - " + username);

        VBox chatLayout = new VBox(10);
        messageArea = new TextArea();
        messageArea.setEditable(false);

        inputField = new TextField();
        sendButton = new Button("Send");

        HBox inputLayout = new HBox(10, inputField, sendButton);
        inputLayout.setPadding(new javafx.geometry.Insets(5));

        chatLayout.getChildren().addAll(messageArea, inputLayout);
        Scene chatScene = new Scene(chatLayout, 500, 400);
        chatStage.setScene(chatScene);

        // Firestore'dan mesajları dinleme
        firestoreService.getMessagesForChatRealTime(chatId, messageArea);

        // Mesaj gönderme işlemi
        sendButton.setOnAction(event -> {
            String message = inputField.getText();
            if (!message.isEmpty()) {
                firestoreService.addMessageToChat(chatId, username, message);
                Platform.runLater(() -> messageArea.appendText(username + ": " + message + "\n"));
                inputField.clear();
            }
        });

        chatStage.show();
    }
}
