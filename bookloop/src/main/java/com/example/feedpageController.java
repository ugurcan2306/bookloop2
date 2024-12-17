package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import client.FireStoreHelper;
import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage.BucketAccessControls.List;
import com.google.cloud.Date;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.FirestoreException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class feedpageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane2feedpage;

    @FXML
    private AnchorPane anchorpaneOfFeedPage;
    @FXML
    private VBox commentsContainer;

    @FXML
    private AnchorPane anchorpanefeedpage3;

    @FXML
    private AnchorPane anchorpaneforcomment2;

    @FXML
    private ImageView dislikeimage;

    @FXML
    private Pane feedpagepane;

    @FXML
    private ImageView imageforprofile2;

    @FXML
    private ImageView imageviewdislike;

    @FXML
    private ImageView imageviewfeedpageprofile;

    @FXML
    private ImageView imageviewforbook1;

    @FXML
    private ImageView imageviewforbook2;

    @FXML
    private ImageView imageviewfordislike;

    @FXML
    private Label labeldislikeforcomment2;

    @FXML
    private Label labeldislikesayisi;

    @FXML
    private Label labellikeforcomment2;

    @FXML
    private Label labellikesayisi;

    @FXML
    private ImageView likeimage;

    @FXML
    private Pane panefeedpage2;

    @FXML
    private Pane panefeedpagecomment;

    @FXML
    private Pane paneforcomment;

    @FXML
    private Pane paneforcomment2;

    @FXML
    private Pane paneoffeedpage;

    @FXML
    private Text textForYou;

    @FXML
    private Text textIamworm;

    @FXML
    private TextFlow textflowforcomment;

    @FXML
    private Text textforauthor1;

    @FXML
    private Text textforauthor2;

    @FXML
    private TextFlow textforcomment2;

    @FXML
    private Text textforuser2;

    @FXML
    private Text textyorum1;
   
    @FXML
    private VBox vbox;

    private int likeCount = 0; // Variable to track total like count
    private int dislikeCount = 0;
    private int likeCount2 = 0; // Variable to track total like count
    private int dislikeCount2 = 0;
    private static final int COMMENTS_PAGE_SIZE = 3; // Number of comments to load per page
    private int currentPage = 0;
    private ArrayList<Map<String, Object>> allComments = new ArrayList<>();

    @FXML
    void initialize() throws IOException {
        assert anchorpane2feedpage != null
                : "fx:id=\"anchorpane2feedpage\" was not injected: check your FXML file 'feedpage.fxml'.";
       
        assert commentsContainer != null
                : "fx:id=\"commentsContainer\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert anchorpaneOfFeedPage != null
                : "fx:id=\"anchorpaneOfFeedPage\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert anchorpanefeedpage3 != null
                : "fx:id=\"anchorpanefeedpage3\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert anchorpaneforcomment2 != null
                : "fx:id=\"anchorpaneforcomment2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert dislikeimage != null : "fx:id=\"dislikeimage\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert feedpagepane != null : "fx:id=\"feedpagepane\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert imageforprofile2 != null
                : "fx:id=\"imageforprofile2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert imageviewdislike != null
                : "fx:id=\"imageviewdislike\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert imageviewfeedpageprofile != null
                : "fx:id=\"imageviewfeedpageprofile\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert imageviewforbook1 != null
                : "fx:id=\"imageviewforbook1\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert imageviewforbook2 != null
                : "fx:id=\"imageviewforbook2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert imageviewfordislike != null
                : "fx:id=\"imageviewfordislike\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert labeldislikeforcomment2 != null
                : "fx:id=\"labeldislikeforcomment2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert labeldislikesayisi != null
                : "fx:id=\"labeldislikesayisi\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert labellikeforcomment2 != null
                : "fx:id=\"labellikeforcomment2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert labellikesayisi != null
                : "fx:id=\"labellikesayisi\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert likeimage != null : "fx:id=\"likeimage\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert panefeedpage2 != null
                : "fx:id=\"panefeedpage2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert panefeedpagecomment != null
                : "fx:id=\"panefeedpagecomment\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert paneforcomment != null
                : "fx:id=\"paneforcomment\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert paneforcomment2 != null
                : "fx:id=\"paneforcomment2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert paneoffeedpage != null
                : "fx:id=\"paneoffeedpage\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textForYou != null : "fx:id=\"textForYou\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textIamworm != null : "fx:id=\"textIamworm\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textflowforcomment != null
                : "fx:id=\"textflowforcomment\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textforauthor1 != null
                : "fx:id=\"textforauthor1\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textforauthor2 != null
                : "fx:id=\"textforauthor2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textforcomment2 != null
                : "fx:id=\"textforcomment2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textforuser2 != null : "fx:id=\"textforuser2\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert textyorum1 != null : "fx:id=\"textyorum1\" was not injected: check your FXML file 'feedpage.fxml'.";
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'feedpage.fxml'.";

        // labellikesayisi.setText(String.valueOf(likeCount));
        // labeldislikesayisi.setText(String.valueOf(dislikeCount));

        // labellikeforcomment2.setText(String.valueOf(likeCount2));
        // labeldislikeforcomment2.setText(String.valueOf(dislikeCount2));
        fetchAllComments();
        System.out.println("Comments fetched: " + allComments.size());
        loadCommentsPage();

    }
    // Variable to track dislike count
    // tüm commentleri aldım şimdi zaman sırasına göre ilk üçünü koycak alta show
    // more

    @FXML
    void handleLikeImageClick() {
        likeCount++;
        labellikesayisi.setText(String.valueOf(likeCount));
    }

    @FXML
    void handleLikeImage2Click() {
        likeCount2++;
        labellikeforcomment2.setText(String.valueOf(likeCount2));
    }

    @FXML
    void handleDislikeImageClick() {
        dislikeCount++;
        labeldislikesayisi.setText(String.valueOf(dislikeCount));
    }

    @FXML
    void handleDislikeImage2Click() {
        dislikeCount2++;
        labeldislikeforcomment2.setText(String.valueOf(dislikeCount2));
    }

    private void fetchAllComments() throws IOException {
        Firestore db = FireStoreHelper.getFirestore();

        try {
            // Query for documents in 'books' collection
            ApiFuture<QuerySnapshot> future = db.collection("books")
                    .get();

            QuerySnapshot querySnapshot = future.get();
            ArrayList<Map<String, Object>> allComments = new ArrayList<>();

            // Iterate over each document in the 'books' collection
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                System.out.println("Fetching comments for book: " + document.getId()); // Debugging line

                // Get the 'Comments' array from the document
                ArrayList<Map<String, Object>> commentsData = (ArrayList<Map<String, Object>>) document.get("Comments");

                // If the 'Comments' field is not null, add them to the allComments list
                if (commentsData != null) {
                    allComments.addAll(commentsData); // Add the comments from this book to the allComments list
                }
            }
            if (!allComments.isEmpty()) {
                // Sort by timestamp (descending order)
                allComments.sort((c1, c2) -> {
                    Timestamp timestamp1 = (Timestamp) c1.get("timestamp");
                    Timestamp timestamp2 = (Timestamp) c2.get("timestamp");
                    return timestamp2.compareTo(timestamp1); // Most recent comments first
                });

                // Display the sorted comments
                for (Map<String, Object> comment : allComments) {
                    String commentText = (String) comment.get("comment");
                    Timestamp timestamp = (Timestamp) comment.get("timestamp");
                    Map<String, Object> ownerOfComment = (Map<String, Object>) comment.get("ownerOfComment");
                    System.out.println("Comment: " + commentText + " | Timestamp: " + timestamp.toDate());
                    String username = (String) ownerOfComment.get("username");
                    String email = (String) ownerOfComment.get("email");
                    displayComment(commentText, 3, 4);
                }
            } else {
                System.out.println("No comments found.");
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace(); // handle error
        }
    }

    // Load a single page of comments (3 at a time)
    private void loadCommentsPage() {
        int startIndex = currentPage * COMMENTS_PAGE_SIZE;
        int endIndex = Math.min(startIndex + COMMENTS_PAGE_SIZE, allComments.size());

        // Yorumları ekleyelim
        for (int i = startIndex; i < endIndex; i++) {
            Map<String, Object> commentData = allComments.get(i);
            String commentText = (String) commentData.get("commentMade");
            // int likes = (int) commentData.get("likes");
            // int dislikes = (int) commentData.get("dislikes");
            int likes = 3;
            int dislikes = 4;

            // Yorumları eklemek için displayComment'i çağır
            displayComment(commentText, likes, dislikes);
        }

        currentPage++; // Bir sonraki sayfaya geç

    }

    // Display a single comment in the VBox container
    private void displayComment(String commentText, int initialLikes, int initialDislikes) {
        // Yorumun gösterileceği Panel
        Pane commentPane = new Pane();
        commentPane.setPrefHeight(110.0);
        commentPane.setPrefWidth(410.0);
        commentPane.setStyle(
                "-fx-background-color: WHITE; -fx-padding: 10; -fx-border-color: #CCC; -fx-border-radius: 5;");

        // Yorum metnini ekleyelim
        Text text = new Text(commentText);
        text.setWrappingWidth(390.0);
        text.setStyle("-fx-font-size: 14; -fx-font-family: 'Verdana';");

        // Metni Pane'e ekleyelim
        text.setLayoutY(10);
        commentPane.getChildren().add(text);

        // Like ve dislike butonlarını ekle
        addCommentButtons(commentPane, initialLikes, initialDislikes);

        // Yorumun gösterildiği VBox'a ekleyelim
        commentsContainer.getChildren().add(commentPane);
    }

    private void addCommentButtons(Pane commentBackgroundPane, int initialLikes, int initialDislikes) {
        // Create Like button image
        ImageView likeImage = new ImageView(new Image(getClass().getResourceAsStream("likebutton.png")));
        likeImage.setFitHeight(26.0);
        likeImage.setFitWidth(34.0);
        likeImage.setLayoutX(318.0); // Adjust position based on the layout
        likeImage.setLayoutY(70.0); // Adjust position
    
        // Create Dislike button image
        ImageView dislikeImage = new ImageView(new Image(getClass().getResourceAsStream("dislikeButton.png")));
        dislikeImage.setFitHeight(26.0);
        dislikeImage.setFitWidth(34.0);
        dislikeImage.setLayoutX(370.0); // Adjust position based on the layout
        dislikeImage.setLayoutY(70.0); // Adjust position
    
        // Add event handlers for like/dislike button clicks
        //likeImage.setOnMouseClicked(e -> handleLike());
        //dislikeImage.setOnMouseClicked(e -> handleDislike());
    
        // Create Labels for Like and Dislike counts
        Label likeLabel = new Label(String.valueOf(initialLikes));
        likeLabel.setLayoutX(318.0); // Adjust X position next to the Like button
        likeLabel.setLayoutY(100.0); // Adjust Y position below the Like button
    
        Label dislikeLabel = new Label(String.valueOf(initialDislikes));
        dislikeLabel.setLayoutX(370.0); // Adjust X position next to the Dislike button
        dislikeLabel.setLayoutY(100.0); // Adjust Y position below the Dislike button
    
        // Add the images and labels to the comment background pane
        commentBackgroundPane.getChildren().addAll(likeImage, dislikeImage, likeLabel, dislikeLabel);
    }

    // Handle the "Show More" button click
    @FXML
    private void handleShowMoreButton() {
        if (currentPage * COMMENTS_PAGE_SIZE < allComments.size()) {
            // Load 3 more comments
            loadCommentsPage();
        } else {
            System.out.println("No more comments to load.");
        }
        
        // Load the next page of comments
    }

    public void addComment(String commentText, int initialLikes, int initialDislikes) {
        // Create the AnchorPane
        AnchorPane commentPane = new AnchorPane();
        commentPane.setPrefHeight(200.0);
        commentPane.setPrefWidth(500.0);

        // Create the white background Pane
        Pane commentBackgroundPane = new Pane();
        commentBackgroundPane.setLayoutX(45.0);
        commentBackgroundPane.setLayoutY(50.0);
        commentBackgroundPane.setPrefHeight(110.0);
        commentBackgroundPane.setPrefWidth(410.0);
        commentBackgroundPane.setStyle("-fx-background-color: WHITE;");

        // Add DropShadow effect
        commentBackgroundPane.setEffect(new javafx.scene.effect.DropShadow());

        // Create and set up the comment text
        Text commentTextNode = new Text(commentText);
        commentTextNode.setWrappingWidth(390.0);
        commentTextNode.setStyle("-fx-font-size: 14; -fx-font-family: 'Verdana';");
        commentBackgroundPane.getChildren().add(commentTextNode);
    
        // Add Like and Dislike buttons
        addCommentButtons(commentBackgroundPane, initialLikes, initialDislikes);
    

        // Add the background Pane to the AnchorPane
        commentPane.getChildren().add(commentBackgroundPane);

        // Add the AnchorPane to the VBox
        commentsContainer.getChildren().add(commentPane);
    }

    // tüm bookların yorumlarına zaman ekle
    // tüm bookların yorumlarını tek tek alıcam
    // aşağısı kaldı
    // zamanlarına göre son 3 ünü displayle
    // daha fazla görmek isterse daha fazla butonuna tıklıcak
    // public ArrayList<Comment> fetchCommentsPage() throws ExecutionException,
    // InterruptedException, IOException {
    // Firestore db = FireStoreHelper.getFirestore();

    // // Query the 'books' collection, ordering by the 'timeStamp' field inside
    // each
    // // comment.
    // ApiFuture<QuerySnapshot> future = db.collection("books")
    // .limit(10) // Fetch only a limited number of documents
    // .get();
    // QuerySnapshot querySnapshot = future.get();
    // System.out.println("Fetched comments: " +
    // querySnapshot.getDocuments().size());
    // ArrayList<Comment> commentsPage = new ArrayList<>();

    // if (!querySnapshot.isEmpty()) {
    // for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
    // ArrayList<Map<String, Object>> commentsData = (ArrayList<Map<String,
    // Object>>) document.get("Comments");

    // if (commentsData != null) {
    // for (Map<String, Object> commentData : commentsData) {
    // String commentText = (String) commentData.get("commentMade");
    // Map<String, Object> ownerData = (Map<String, Object>)
    // commentData.get("ownerofTheComment");
    // User owner = (User) ownerData.get("owner");

    // // Extract timeStamp and convert it
    // Timestamp timeStamp = (Timestamp) commentData.get("timeStamp");

    // // Create Comment object and add to the list
    // Comment comment = new Comment(commentText, owner, timeStamp);
    // commentsPage.add(comment);
    // }
    // }
    // }
    // }

    // currentPage++; // Increment page for pagination
    // return commentsPage;
    // }

    // @FXML
    // private void handleShowMoreButton() {
    // try {
    // ArrayList<Comment> newComments = fetchCommentsPage();
    // if (!newComments.isEmpty()) {
    // // Append new comments to the existing UI
    // newComments.forEach(this::displayComment);
    // } else {
    // showmorebtn.setDisable(true); // Disable button when no more comments
    // }
    // } catch (Exception e) {
    // e.printStackTrace(); // Handle errors appropriately
    // }
    // }

    // private void displayComment(Comment comment) {
    // // Create new UI elements dynamically for each comment
    // TextFlow commentTextFlow = new TextFlow(new Text(comment.getComment()));
    // Pane commentPane = new Pane(commentTextFlow);
    // commentPane.setStyle("-fx-background-color: WHITE;");

    // if (currentPage == 0) {
    // // Add comment to the first AnchorPane (anchorpanefeedpage3)
    // AnchorPane anchorPane = (AnchorPane)
    // feedpagepane.lookup("#anchorpanefeedpage3");
    // if (anchorPane != null) {
    // anchorPane.getChildren().add(commentPane);
    // }
    // } else if (currentPage == 1) {
    // // Add comment to the second AnchorPane (anchorpaneforcomment2)
    // AnchorPane anchorPane = (AnchorPane)
    // feedpagepane.lookup("#anchorpaneforcomment2");
    // if (anchorPane != null) {
    // anchorPane.getChildren().add(commentPane);
    // }
    // }

    // // Add to the parent container (e.g., VBox or AnchorPane)
    // // feedpagepane.getChildren().add(commentPane);
    // }
}
