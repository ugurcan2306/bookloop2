package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.example.firestore.FireStoreHelper;
import com.example.firestore.FirestoreUtils;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.v1.FirestoreClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LoginControllerSon {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorid;

    @FXML
    private Label booklooplabel;

    @FXML
    private Button exitButton;

    @FXML
    private ImageView imageviewid;

    @FXML
    private AnchorPane insideanchor;

    @FXML
    private Line lineiddeneme;

    @FXML
    private Button loginButtonid;

    @FXML
    private Button noAccountLabelid;

    @FXML
    private Pane paneeid;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordtextfieldLabel;

    @FXML
    private Label secureLoginLabel;

    @FXML
    private Label slogonlabel;

    @FXML
    private SplitPane splitpaneid;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usertextFieldid;

    @FXML
    void initialize() {
        assert anchorid != null : "fx:id=\"anchorid\" was not injected: check your FXML file 'login.fxml'.";
        assert booklooplabel != null : "fx:id=\"booklooplabel\" was not injected: check your FXML file 'login.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'login.fxml'.";
        assert imageviewid != null : "fx:id=\"imageviewid\" was not injected: check your FXML file 'login.fxml'.";
        assert insideanchor != null : "fx:id=\"insideanchor\" was not injected: check your FXML file 'login.fxml'.";
        assert lineiddeneme != null : "fx:id=\"lineiddeneme\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButtonid != null : "fx:id=\"loginButtonid\" was not injected: check your FXML file 'login.fxml'.";
        assert noAccountLabelid != null
                : "fx:id=\"noAccountLabelid\" was not injected: check your FXML file 'login.fxml'.";
        assert paneeid != null : "fx:id=\"paneeid\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordLabel != null : "fx:id=\"passwordLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordtextfieldLabel != null
                : "fx:id=\"passwordtextfieldLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert secureLoginLabel != null
                : "fx:id=\"secureLoginLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert slogonlabel != null : "fx:id=\"slogonlabel\" was not injected: check your FXML file 'login.fxml'.";
        assert splitpaneid != null : "fx:id=\"splitpaneid\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert usertextFieldid != null
                : "fx:id=\"usertextFieldid\" was not injected: check your FXML file 'login.fxml'.";

        loginButtonid.setOnAction(event -> {
            String username = usertextFieldid.getText();
            String password = passwordtextfieldLabel.getText();

            if (username.isEmpty() || password.isEmpty()) {
               
                Alert alert = new Alert(AlertType.ERROR, "Please fill in both username and password.", ButtonType.OK);
                alert.setTitle("Error");
                alert.setHeaderText("Empty Fields");
                alert.showAndWait();
            } else {
               
                try {
                    validateUser(username, password);
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert( "An error occurred while validating your credentials.", AlertType.ERROR);
                }
            }
        });

       
        noAccountLabelid.setOnAction(event -> {
            openRegisterPage();
        });

       
        exitButton.setOnAction(event -> {
            // Close the application when exit button is clicked
            System.exit(0);
        });

    }
    private void validateUser(String username, String password) throws InterruptedException, ExecutionException, IOException {
        Firestore db =  FireStoreHelper.getFirestore();
    
        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("Username", username).get(); 
        QuerySnapshot querySnapshot = future.get();
    
        if (querySnapshot.isEmpty()) {
            showAlert("The username does not exist in the database.", AlertType.WARNING);
        } else {
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                String storedPassword = document.getString("Password");
                String storedUsername=document.getString("username");
                if (storedPassword != null && storedPassword.equals(password) &&  storedUsername != null && storedUsername.equals(username)) {
                    
                    openRegisterPage();
                    return;
                }
            }
           
            showAlert("The username or password is incorrect.", AlertType.WARNING);
        }
    }
    
    private void showAlert( String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void openRegisterPage() {
        try {
          
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml")); // Update the path
            Scene registerScene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) loginButtonid.getScene().getWindow();
            stage.setScene(registerScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
//yapılacaklar//
//feedpage zamana göre sırala
//eklenen yorumun benim userım tarafından yapıldığını ayarla
