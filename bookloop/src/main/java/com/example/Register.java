package com.example;

import java.io.IOException;
//import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;

import client.FireStoreHelper;
import com.bookloop.firestore.FirestoreUtils;
//import com.google.api.services.storage.Storage.BucketAccessControls.List;
import com.google.cloud.firestore.Firestore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Register {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button BtnRegister;

        @FXML
        private Label LabelCity;

        @FXML
        private Label LabelCountry;

        @FXML
        private Label LabelEmail;

        @FXML
        private Label LabelPassword;

        @FXML
        private Label LabelPasswordConfirm;

        @FXML
        private Label LabelUsername;

        @FXML
        private Label LableRegisterHere;

        @FXML
        private PasswordField PasswordFieldRegister;

        @FXML
        private PasswordField PasswordFieldregister;

        @FXML
        private TextField TextFieldCity;

        @FXML
        private TextField TextFieldCountry;

        @FXML
        private TextField TextFieldEmail;

        @FXML
        private TextField TextFieldUsernazme;

        @FXML
        private Pane bigPaneRegister;

        @FXML
        void MiddlePaneRegister(MouseEvent event) {

        }

        @FXML
        void initialize() {
                assert BtnRegister != null
                                : "fx:id=\"BtnRegister\" was not injected: check your FXML file 'register.fxml'.";
                assert LabelCity != null
                                : "fx:id=\"LabelCity\" was not injected: check your FXML file 'register.fxml'.";
                assert LabelCountry != null
                                : "fx:id=\"LabelCountry\" was not injected: check your FXML file 'register.fxml'.";
                assert LabelEmail != null
                                : "fx:id=\"LabelEmail\" was not injected: check your FXML file 'register.fxml'.";
                assert LabelPassword != null
                                : "fx:id=\"LabelPassword\" was not injected: check your FXML file 'register.fxml'.";
                assert LabelPasswordConfirm != null
                                : "fx:id=\"LabelPasswordConfirm\" was not injected: check your FXML file 'register.fxml'.";
                assert LabelUsername != null
                                : "fx:id=\"LabelUsername\" was not injected: check your FXML file 'register.fxml'.";
                assert LableRegisterHere != null
                                : "fx:id=\"LableRegisterHere\" was not injected: check your FXML file 'register.fxml'.";
                assert PasswordFieldRegister != null
                                : "fx:id=\"PasswordFieldRegister\" was not injected: check your FXML file 'register.fxml'.";
                assert PasswordFieldregister != null
                                : "fx:id=\"PasswordFieldregister\" was not injected: check your FXML file 'register.fxml'.";
                assert TextFieldCity != null
                                : "fx:id=\"TextFieldCity\" was not injected: check your FXML file 'register.fxml'.";
                assert TextFieldCountry != null
                                : "fx:id=\"TextFieldCountry\" was not injected: check your FXML file 'register.fxml'.";
                assert TextFieldEmail != null
                                : "fx:id=\"TextFieldEmail\" was not injected: check your FXML file 'register.fxml'.";
                assert TextFieldUsernazme != null
                                : "fx:id=\"TextFieldUsernazme\" was not injected: check your FXML file 'register.fxml'.";
                assert bigPaneRegister != null
                                : "fx:id=\"bigPaneRegister\" was not injected: check your FXML file 'register.fxml'.";

        }

        @FXML
        void onRegisterClicked() throws IOException {
                String username = TextFieldUsernazme.getText();
                String email = TextFieldEmail.getText();
                String city = TextFieldCity.getText();
                String country = TextFieldCountry.getText();
                String password = PasswordFieldRegister.getText();
                String confirmPassword = PasswordFieldregister.getText();

                if (username.isEmpty() || email.isEmpty() || city.isEmpty() || country.isEmpty() || password.isEmpty()
                                || confirmPassword.isEmpty()) {
                        Alert alert = new Alert(AlertType.ERROR, "Please fill in all fields.", ButtonType.OK);
                        alert.setTitle("Error");
                        alert.setHeaderText("Missing Fields");
                        alert.showAndWait();
                } else if (!password.equals(confirmPassword)) {
                        Alert alert = new Alert(AlertType.ERROR, "Passwords do not match. Please try again.",
                                        ButtonType.OK);
                        alert.setTitle("Error");
                        alert.setHeaderText("Password Mismatch");
                        alert.showAndWait();
                } else {
                        try {
                                // Initialize Firestore
                                System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "src/main/resources/serviceAccountKey.json");
                                Firestore db = FireStoreHelper.getFirestore();

                                // Save user data to Firestore
                                ArrayList<Book> booksToTrade = new ArrayList<>();
                                ArrayList<Book> markedAsRead = new ArrayList<>();
                                ArrayList<GroupChat> groupChats = new ArrayList<>();
                                ArrayList<OneToOneChat> oneToOneChats = new ArrayList<>();
                                ArrayList<User> friends = new ArrayList<>();
                                ArrayList<TradeRequest> receivedTradeRequest = new ArrayList<>();
                                ArrayList<TradeRequest>  sentTradeRequests= new ArrayList<>();

                                FirestoreUtils.addUserToFirestore(db, username, password, email, country, city,
                                           booksToTrade, markedAsRead,groupChats,oneToOneChats,friends,receivedTradeRequest,sentTradeRequests);

                                Alert successAlert = new Alert(AlertType.INFORMATION, "Registration Successful!",
                                                ButtonType.OK);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText("Registration Completed");
                                successAlert.showAndWait();

                                // Redirect to the login page
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                                Pane loginPane = loader.load();
                                Scene loginScene = new Scene(loginPane);
                                Stage currentStage = (Stage) BtnRegister.getScene().getWindow();
                                currentStage.setScene(loginScene);
                                currentStage.show();

                        } catch (Exception e) {
                                e.printStackTrace();
                                Alert dbErrorAlert = new Alert(AlertType.ERROR,
                                                "Error saving user data: " + e.getMessage(),
                                                ButtonType.OK);
                                dbErrorAlert.setTitle("Error");
                                dbErrorAlert.setHeaderText("Database Error");
                                dbErrorAlert.showAndWait();
                        }
                }
        }

}
