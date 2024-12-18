package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class OnunApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD
        scene = new Scene(loadFXML("/com/example/FriendsController"), 640, 480);
=======
        scene = new Scene(loadFXML("/com/example/forYouFeed"), 640, 480);
>>>>>>> origin/main
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OnunApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}