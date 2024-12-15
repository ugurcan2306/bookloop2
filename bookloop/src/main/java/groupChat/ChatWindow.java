package groupChat;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ChatWindow {

    private TextArea messageArea;
    private TextField inputField;
    private Button sendButton;
    private String chatId;
    private String username;
    private FirestoreService firestoreService;

    public ChatWindow(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
        firestoreService = new FirestoreService();
        initializeWindow();
    }

    private void initializeWindow() {
        Stage chatStage = new Stage();
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
        chatStage.show();

        // Firestore'dan mesajları gerçek zamanlı dinliyoruz
        firestoreService.getMessagesForChatRealTime(chatId, messageArea);  // messageArea'ya mesajları ekleriz

        // Mesaj gönderme işlemi
        sendButton.setOnAction(event -> {
            String message = inputField.getText();
            if (!message.isEmpty()) {
                // Firebase'e mesaj gönderiliyor
                firestoreService.addMessageToChat(chatId, username, message);  

                // Mesajı hemen UI'ye ekliyoruz
                Platform.runLater(() -> {
                    messageArea.appendText(username + ": " + message + "\n");
                });

                // Mesaj gönderildikten sonra input alanını temizle
                inputField.clear();
            }
        });
    }
}
