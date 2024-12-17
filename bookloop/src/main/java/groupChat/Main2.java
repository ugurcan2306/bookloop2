package groupChat;

import com.example.SessionManager;
import com.example.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Main2 extends Application {

    private User currentUser;
    private FirestoreService firestoreService;

    public static void main(String[] args) {
        SessionManager.setCurrentUser("ugurcan23");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //FirebaseInit.initializeFirebase(); 
        firestoreService = new FirestoreService();
        currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            showAlert("Error", "User not found. Please log in.");
            return;
        }
        initializeMainWindow(primaryStage);
    }

    private void initializeMainWindow(Stage primaryStage) {
        //currentUser = SessionManager.getCurrentUser();
        try {
            
            
            // Load the FXML file for the chat window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatWindow.fxml"));
            VBox layout = loader.load(); // load the FXML content

            chatWindowController controller = loader.getController();
            //System.out.println(currentUser.getUsername());
            controller.initializeChat("defaultChatId", currentUser.getUsername()); // Initialize with default values

            // Display chat window in the main scene
            Scene scene = new Scene(layout, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome to Chat");
            primaryStage.show();
        } catch (Exception e) {
            showAlert("Error", "Failed to load the chat window.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}