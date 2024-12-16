package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import com.example.firestore.FireStoreHelper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class bookController {
    private String namee;

    public bookController(String nameOfTheBook) {
        this.namee = nameOfTheBook;

    }

    public bookController() {
        this.namee = "Animal Farm";
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorcomments;

    @FXML
    private AnchorPane bookclassanchorpane;

    @FXML
    private Label bookclasscommentsbaşlik;

    @FXML
    private VBox bookclassvboxu;
    @FXML
    private VBox yorumvbox;

    @FXML
    private VBox vboxforcomments;

    @FXML
    private TextArea bookdescriptionbookclass;
    @FXML
    private TextField makecommentid;

    @FXML
    private Label bookdescriptionname;

    @FXML
    private Label bookname;

    @FXML
    private ImageView bookphotobookclass;

    @FXML
    private Label bookratingsname;

    @FXML
    private ScrollPane bookscrollpane;

    @FXML
    private Label comment1;

    @FXML
    private Label comment1username;

    @FXML
    private Label comment2;

    @FXML
    private Label comment3;

    @FXML
    private Label comment3user;

    @FXML
    private Label profile1;

    @FXML
    private Label profile2;

    @FXML
    private Label profile3;

    @FXML
    private Label profile4;

    @FXML
    private Label profilestotrade;

    @FXML
    private Label ratelabel;

    @FXML
    private ScrollPane scrollcomments;

    @FXML
    private ImageView yildiz1;

    @FXML
    private ImageView yildiz2;

    @FXML
    private ImageView yildiz3;
    @FXML
    private Button addcommentbtn;

    @FXML
    private ImageView yildiz4;

    @FXML
    private ImageView yildiz5;
    private int count;

    @FXML
    void initialize() {
        count=0;
        assert anchorcomments != null : "fx:id=\"anchorcomments\" was not injected: check your FXML file 'book.fxml'.";
        assert bookclassanchorpane != null
                : "fx:id=\"bookclassanchorpane\" was not injected: check your FXML file 'book.fxml'.";
        assert bookclasscommentsbaşlik != null
                : "fx:id=\"bookclasscommentsbaşlik\" was not injected: check your FXML file 'book.fxml'.";
        assert bookclassvboxu != null : "fx:id=\"bookclassvboxu\" was not injected: check your FXML file 'book.fxml'.";
        assert bookdescriptionbookclass != null
                : "fx:id=\"bookdescriptionbookclass\" was not injected: check your FXML file 'book.fxml'.";
        assert bookdescriptionname != null
                : "fx:id=\"bookdescriptionname\" was not injected: check your FXML file 'book.fxml'.";
        assert bookname != null : "fx:id=\"bookname\" was not injected: check your FXML file 'book.fxml'.";
        assert bookphotobookclass != null
                : "fx:id=\"bookphotobookclass\" was not injected: check your FXML file 'book.fxml'.";
        assert bookratingsname != null
                : "fx:id=\"bookratingsname\" was not injected: check your FXML file 'book.fxml'.";
        assert bookscrollpane != null : "fx:id=\"bookscrollpane\" was not injected: check your FXML file 'book.fxml'.";
        assert comment1 != null : "fx:id=\"comment1\" was not injected: check your FXML file 'book.fxml'.";
        assert comment1username != null
                : "fx:id=\"comment1username\" was not injected: check your FXML file 'book.fxml'.";
        assert comment2 != null : "fx:id=\"comment2\" was not injected: check your FXML file 'book.fxml'.";
        assert comment3 != null : "fx:id=\"comment3\" was not injected: check your FXML file 'book.fxml'.";
        assert comment3user != null : "fx:id=\"comment3user\" was not injected: check your FXML file 'book.fxml'.";
        assert profile1 != null : "fx:id=\"profile1\" was not injected: check your FXML file 'book.fxml'.";
        assert profile2 != null : "fx:id=\"profile2\" was not injected: check your FXML file 'book.fxml'.";
        assert profile3 != null : "fx:id=\"profile3\" was not injected: check your FXML file 'book.fxml'.";
        assert profile4 != null : "fx:id=\"profile4\" was not injected: check your FXML file 'book.fxml'.";
        assert profilestotrade != null
                : "fx:id=\"profilestotrade\" was not injected: check your FXML file 'book.fxml'.";
        assert ratelabel != null : "fx:id=\"rate\" was not injected: check your FXML file 'book.fxml'.";
        assert scrollcomments != null : "fx:id=\"scrollcomments\" was not injected: check your FXML file 'book.fxml'.";
        assert yildiz1 != null : "fx:id=\"yildiz1\" was not injected: check your FXML file 'book.fxml'.";
        assert yildiz2 != null : "fx:id=\"yildiz2\" was not injected: check your FXML file 'book.fxml'.";
        assert yildiz3 != null : "fx:id=\"yildiz3\" was not injected: check your FXML file 'book.fxml'.";
        assert yildiz4 != null : "fx:id=\"yildiz4\" was not injected: check your FXML file 'book.fxml'.";
        assert yildiz5 != null : "fx:id=\"yildiz5\" was not injected: check your FXML file 'book.fxml'.";
        assert yorumvbox != null : "fx:id=\"yorumvbox\" was not injected: check your FXML file 'book.fxml'.";
        assert vboxforcomments != null
                : "fx:id=\"vboxforcomments\" was not injected: check your FXML file 'book.fxml'.";

        assert addcommentbtn != null : "fx:id=\"addcommentbtn\" was not injected: check your FXML file 'book.fxml'.";
        assert makecommentid != null : "fx:id=\"makecommentid\" was not injected: check your FXML file 'book.fxml'.";
        // datadaki bilgiler buraya aktarılacak
        // ratings sistemi yapılacak
        // commentler manage edilecek
        // comment objeleri datada tutulacak

        try {
            handleDisplay();
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void handleDisplay() throws IOException, InterruptedException, ExecutionException {
        try {
            Firestore db = FireStoreHelper.getFirestore();

            ApiFuture<QuerySnapshot> future = db.collection("books").whereEqualTo("Name", namee).get();
            QuerySnapshot querySnapshot = future.get();
            Book book = null;
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                book = new Book(namee, "", "");
                book.setName(document.getString("Name"));
                book.setDescription(document.getString("Description"));
                // book.setAuthor(document.getString());
                // book.setComments((ArrayList<String>) document.get("Comments"));
                book.setRate(document.getDouble("Rate"));

                ArrayList<Map<String, Object>> profilesData = (ArrayList<Map<String, Object>>) document
                        .get("Bookowners");

                ArrayList<User> profiles = new ArrayList<>();
                ArrayList<String> profilesnames = new ArrayList<>();

                if (profilesData != null) {
                    for (Map<String, Object> userData : profilesData) {
                        User user = (User) userData;  // Burada userData'nın zaten bir User nesnesi olduğunu varsayıyoruz
                        profiles.add(user); 
                        book.setBookowners(profiles);
                        
                    }
                }

               

                ArrayList<Map<String, Object>> commentsData = (ArrayList<Map<String, Object>>) document.get("Comments");
                if (commentsData != null) {
                    for (Map<String, Object> commentData : commentsData) {
                        Map<String, Object> ownerData = (Map<String, Object>) commentData.get("ownerofTheComment");
                        String username = ownerData != null ? (String) ownerData.get("username") : "Anonymous";
                        String commentText = (String) commentData.get("commentMade");
                        addComments(username, commentText);
                    }
                }
            }

            if (book != null) {
                bookname.setText(namee);
                bookdescriptionbookclass.setText(book.getDescription());
                ratelabel.setText(String.valueOf(book.getRate()));
                for (User profil : book.getBookowners()) {
                    addProfiles(profil);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // bir profili ekliyo alta
    public void addProfiles(User user) {

        HBox commentSection = new HBox(10); // Spacing of 10 between elements

        // Create avatar placeholder (can be an ImageView instead)
        // Label avatar = new Label("👤"); // Replace with an actual ImageView for real
        // avatars
        // avatar.setStyle("-fx-font-size: 36px;");

        // Create username label

        Label usernameLabel = new Label(user.getUsername());
        usernameLabel.setStyle("-fx-text-fill: blue;");
        // usernameLabel.setOnMouseClicked(event -> openUserProfile(username));

        // Create buttons
        // Button seeProfileButton = new Button("See Profile");
        // Button startChatButton = new Button("Start Chat");
        // Button removeFriendButton = new Button("Remove Friend");

        // Add event handling (optional)
        // seeProfileButton.setOnAction(event -> handleSeeProfile(event));
        // startChatButton.setOnAction(event -> {
        // friendsContainer.setVisible(false);
        // });
        // removeFriendButton.setOnAction(event -> {
        // friendsContainer.getChildren().remove(friendSection);
        // });

        // Add elements to the HBox
        commentSection.getChildren().addAll(usernameLabel);

        // Add the friend's section to the main VBox container
        yorumvbox.getChildren().add(commentSection);

        // Create the HBox container for the friend's section

    }
    @FXML
void handleAddCommentClick() throws IOException, InterruptedException, ExecutionException {
    
    String commentText = makecommentid.getText().trim();

    if (!commentText.isEmpty()) {
        try {
            Firestore db = FireStoreHelper.getFirestore();

            
            String currentUsername = "yourUsername"; // Retrieve dynamically in real use

           
            Map<String, Object> newComment = Map.of(
                "commentMade", commentText,
                "ownerofTheComment", Map.of(
                    "username", currentUsername,
                    "email", "user@example.com", // Use real user email
                    "city", "",                  // Adjust fields as necessary
                    "country", ""
                ),
                "timeStamp", java.time.Instant.now().toString()
            );

           
            ApiFuture<QuerySnapshot> future = db.collection("books").whereEqualTo("Name", namee).get();
            QuerySnapshot querySnapshot = future.get();

            if (!querySnapshot.isEmpty()) {
                for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                    // Add the new comment to the "Comments" array
                    document.getReference().update("Comments", com.google.cloud.firestore.FieldValue.arrayUnion(newComment));
                }

                // Update the UI with the new comment
                addComments(currentUsername, commentText);

                // Clear the input field
                makecommentid.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Comment text is empty!");
    }
}

    public void addComments(String username, String commentText) {

        HBox commentSection2 = new HBox(10); // Spacing of 10 between elements

        // Create avatar placeholder (can be an ImageView instead)
        // Label avatar = new Label("👤"); // Replace with an actual ImageView for real
        // avatars
        // avatar.setStyle("-fx-font-size: 36px;");

        // Create username label

        Label usernameLabel = new Label(username + " : ");
        usernameLabel.setStyle(" -fx-font-weight: bold;");

        Label commentLabel = new Label(commentText);
        // commentLabel.setStyle("-fx-text-fill: black;");
        // usernameLabel.setOnMouseClicked(event -> openUserProfile(username));

        // Create buttons
        // Button seeProfileButton = new Button("See Profile");
        // Button startChatButton = new Button("Start Chat");
        // Button removeFriendButton = new Button("Remove Friend");

        // Add event handling (optional)
        // seeProfileButton.setOnAction(event -> handleSeeProfile(event));
        // startChatButton.setOnAction(event -> {
        // friendsContainer.setVisible(false);
        // });
        // removeFriendButton.setOnAction(event -> {
        // friendsContainer.getChildren().remove(friendSection);
        // });

        // Add elements to the HBox
        commentSection2.getChildren().addAll(usernameLabel, commentLabel);

        // Add the friend's section to the main VBox container
        vboxforcomments.getChildren().add(commentSection2);

        // Create the HBox container for the friend's section

    }
    public void rateBook(int count)throws IOException, InterruptedException, ExecutionException{
        //datadaki rate i alıp
        //datadaki rate eden kişi countunu alıp
        //bu count u kişi sayısı+1 e bölüp 
        //rate e ekleyip rate i güncelleyecek
        try {
            Firestore db = FireStoreHelper.getFirestore();
    
       
            ApiFuture<QuerySnapshot> future = db.collection("books").whereEqualTo("Name", namee).get();
            QuerySnapshot querySnapshot = future.get();
    
            if (!querySnapshot.isEmpty()) {
                for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                  
                    double currentRate = document.getDouble("Rate");
                    long currentNumberOfRatings = document.getLong("NumberOfRatings");
    
               
                    double newRate = ((currentRate * currentNumberOfRatings) + count) / (currentNumberOfRatings + 1);
    
                    document.getReference().update("Rate", newRate);
                    document.getReference().update("NumberOfRatings", currentNumberOfRatings + 1);
    
                  
                    ratelabel.setText(String.format("%.2f", newRate));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    @FXML
    void handleLikeImageClickstar1() throws IOException, InterruptedException, ExecutionException {
        count=1;
        rateBook(count);
        
    }
    @FXML
    void handleLikeImageClickstar2() throws IOException, InterruptedException, ExecutionException {
        count=2;
        rateBook(count);
       
    }
    @FXML
    void handleLikeImageClickstar3() throws IOException, InterruptedException, ExecutionException {
        count=3;
        rateBook(count);
    }
    @FXML
    void handleLikeImageClickstar4() throws IOException, InterruptedException, ExecutionException {
        count=4;
        rateBook(count);
    }
    @FXML
    void handleLikeImageClickstar5() throws IOException, InterruptedException, ExecutionException {
        count=5;
        rateBook(count);
    }

    // public static Book fetchBookDetails(String bookName) throws ExecutionException, InterruptedException {
    //     Firestore db = getFirestore();
    //     ApiFuture<QuerySnapshot> future = db.collection("books").whereEqualTo("Name", bookName).get();
    //     QuerySnapshot querySnapshot = future.get();
        
    //     if (!querySnapshot.isEmpty()) {
    //         for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
    //             // Create and return the Book object
    //             Book book = new Book();
    //             book.setName(document.getString("Name"));
    //             book.setDescription(document.getString("Description"));
    //             book.setRate(document.getDouble("Rate"));
    //             book.setProfilesToTrade((ArrayList<String>) document.get("Bookowners"));
    //             book.setComments((ArrayList<Map<String, Object>>) document.get("Comments"));
    //             return book;
    //         }
    //     }
    //     return null;
    // }

    // private Object openUserProfile(String username) {
    // try {
    // // Assuming you have a method to load a new scene for the user profile
    // FXMLLoader loader = new
    // FXMLLoader(getClass().getResource("userProfile.fxml"));
    // Parent root = loader.load();

    // // Get the controller for the user profile scene
    // UserProfileController userProfileController = loader.getController();

    // // Pass the username (or user object) to the controller to load user data
    // userProfileController.loadUserProfile(username);

    // // Switch to the user profile scene
    // Stage stage = (Stage) usernameLabel.getScene().getWindow(); // Get the
    // current stage
    // stage.setScene(new Scene(root)); // Set the new scene for the profile page
    // stage.show();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // }
    // //contollera profilecontroller
    // public void loadUserProfile(String username) {
    // // Here, you would query the database for the user details (like email,
    // profile picture, etc.)
    // try {
    // Firestore db = FireStoreHelper.getFirestore();
    // ApiFuture<QuerySnapshot> future =
    // db.collection("users").whereEqualTo("username", username).get();
    // QuerySnapshot querySnapshot = future.get();

    // for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
    // String name = document.getString("name");
    // String email = document.getString("email");
    // String imageUrl = document.getString("profileImage"); // Assuming there's an
    // image URL

    // // Update the profile page UI with this data
    // userProfileName.setText(name);
    // userProfileEmail.setText(email);
    // // Set the profile image (assuming you have a method to load the image from a
    // URL)
    // if (imageUrl != null) {
    // Image image = new Image(imageUrl);
    // userProfileImage.setImage(image);
    // }
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

}
