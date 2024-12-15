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

    private void initializeMainWindow(Stage primaryStage) {
        // Ana pencerede Chat ID ve Kullanıcı Adı girişi
        VBox layout = new VBox(10);
        TextField chatIdField = new TextField();
        TextField usernameField = new TextField();
        Button enterButton = new Button("Enter Chat");

        layout.getChildren().addAll(new Label("Enter Chat ID:"), chatIdField, new Label("Enter your username:"), usernameField, enterButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome to Chat");
        primaryStage.show();

        enterButton.setOnAction(event -> {
            String chatId = chatIdField.getText();
            String username = usernameField.getText();
            if (!chatId.isEmpty() && !username.isEmpty()) {
                // Yeni bir sohbet penceresi aç
                new ChatWindow(chatId, username);
            } else {
                showAlert("Error", "Please enter both Chat ID and Username.");
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
