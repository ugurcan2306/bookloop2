package com.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;

import java.util.ResourceBundle;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;

import client.FireStoreHelper;



public class CommentsDisplayController implements Initializable {
   
    @FXML
    private VBox commentsContainer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane feedpagepane;

    @FXML
    private ScrollPane scrollPaneFeedPage;

    @FXML
    private Text textForYou;

    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert commentsContainer != null : "fx:id=\"commentsContainer\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert feedpagepane != null : "fx:id=\"feedpagepane\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert scrollPaneFeedPage != null : "fx:id=\"scrollPaneFeedPage\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textForYou != null : "fx:id=\"textForYou\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'feedpage.fxml'.";
        // ArrayList<Comment> arr= new ArrayList<>();
        // arr.add(new Comment("IAM_AWORM", "I DON'T RECOMMEND THIS CRAZY BORING BOOK TO ANYONE!",Timestamp.now(), "Montaigne"));
        // arr.add(new Comment("USER_1984", "ONE OF THE FINEST WORKS OF GEORGE ORWELL...",Timestamp.now() ,"George Orwell - Animal Farm"));
        // loadComments(arr);
    }
        
    
    private void loadComments(ArrayList<Comment> comments) {
       
        for (Comment comment : comments) {
            commentsContainer.getChildren().add(createCommentPanel(comment));// sadece ui ekleme işi. şimdi benim commenti datadan çekmem lazım
        }
    }

    // Yeni yorum eklemek için
    // public void addNewComment(Comment newComment) {
    //     Platform.runLater(() -> {
    //         commentsContainer.getChildren().add(createCommentPanel(newComment));
    //     });
    // }

    // Her bir yorum için bir HBox paneli oluştur
    private HBox createCommentPanel(Comment comment) {
        HBox commentPanel = new HBox(15);
        commentPanel.setPadding(new Insets(10));
        commentPanel.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #B0A8B9;");

        VBox userBox = new VBox();
        Label userLabel = new Label(comment.getUsernameOfTheOwner());
        userLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        Label commentText = new Label(comment.getCommentMade());
        commentText.setWrapText(true);
        commentText.setMaxWidth(300);

        Label bookLabel = new Label(comment.getBookname());
        bookLabel.setStyle("-fx-font-size: 10; -fx-text-fill: #A23E3E;");

        VBox contentBox = new VBox(commentText, bookLabel);

        commentPanel.getChildren().addAll(userBox, contentBox);

        return commentPanel;
    }
}
