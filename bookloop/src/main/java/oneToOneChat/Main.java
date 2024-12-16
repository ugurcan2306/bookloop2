package oneToOneChat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private FirestoreService firestoreService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FirebaseInit.initializeFirebase(); 
        firestoreService = new FirestoreService();
        initializeMainWindow(primaryStage);
    }

    // Ana pencereyi başlatan metod
    private void initializeMainWindow(Stage primaryStage) {
        // Kullanıcı adları giriş alanı
        VBox layout = new VBox(10);
        TextField usernameField1 = new TextField();
        TextField usernameField2 = new TextField();
        Button enterButton = new Button("Start Chat");

        layout.getChildren().addAll(
            new Label("Enter first username:"), usernameField1, 
            new Label("Enter second username:"), usernameField2, 
            enterButton
        );
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Start Chat");
        primaryStage.show();

        // Buton tıklama işleyicisi
        enterButton.setOnAction(event -> {
            String username1 = usernameField1.getText();
            String username2 = usernameField2.getText();
            if (!username1.isEmpty() && !username2.isEmpty()) {
                // Firestore'da yeni chat ID'si oluştur
                firestoreService.createChatIdForTwoUsers(username1, username2, chatId -> {
                    // Yeni sohbet penceresini başlat
                    new ChatWindow(chatId, username1);
                    new ChatWindow(chatId, username2);
                });
            } else {
                showAlert("Error", "Please enter both usernames.");
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
