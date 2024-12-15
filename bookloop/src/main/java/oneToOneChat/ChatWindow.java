package oneToOneChat;

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

    private static final int MAX_USERS_IN_CHAT = 2;  // Sadece iki kullanıcıya izin verilecek

    public ChatWindow(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
        firestoreService = new FirestoreService();
        initializeWindow();
    }

    private void initializeWindow() {
        // Kullanıcı sayısını kontrol et
        firestoreService.checkUserCount(chatId, result -> {
            if (result >= MAX_USERS_IN_CHAT) {
                // Eğer kullanıcı sayısı 2'yi geçtiyse, yeni pencere açma
                Platform.runLater(() -> showAlert("Error", "This chat is already full. Only two users are allowed."));
            } else {
                // Kullanıcı sayısı 2'yi geçmediyse sohbet penceresini başlat
                createChatWindow();
                // Kullanıcıyı Firestore'a kaydet
                firestoreService.addUserToChat(chatId, username);
            }
        });
    }

    private void createChatWindow() {
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
