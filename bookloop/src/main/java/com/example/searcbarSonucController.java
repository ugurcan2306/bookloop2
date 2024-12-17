
package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class searcbarSonucController {
    ToggleGroup grp = new ToggleGroup();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private RadioButton rbAsName;

    @FXML
    private RadioButton rbAsRating;

    @FXML
    private RadioButton rbDesName;

    @FXML
    private RadioButton rbDesRat;

    @FXML
    private Pane pane;

    @FXML
    private ScrollPane scrollPane;

    private void handleSearchClick(){
        System.out.println("aaa");
    }

    @FXML
    private Button sortButton;

    @FXML
    private void handleSortButtonAction(ActionEvent event){
        if(rbAsName.equals(grp.getSelectedToggle())){
            displayResults(Search.sortByAscendingName(panelController.results),3);
        }
        if(rbDesName.equals(grp.getSelectedToggle())){
            displayResults(Search.sortByDescendingName(panelController.results),3);
        }
        if(rbAsRating.equals(grp.getSelectedToggle())){
            displayResults(Search.sortByAscendingRating(panelController.results),3);
        }
        if(rbDesRat.equals(grp.getSelectedToggle())){
            displayResults(Search.sortByDescendingRating(panelController.results),3);
        }
    }

    @FXML
    private Pane sortPane;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'searcbarSonuc.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'searcbarSonuc.fxml'.";
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'searcbarSonuc.fxml'.";
        assert sortButton != null : "fx:id=\"sortButton\" was not injected: check your FXML file 'searcbarSonuc.fxml'.";
        assert sortPane != null : "fx:id=\"sortPane\" was not injected: check your FXML file 'searcbarSonuc.fxml'.";
        ArrayList<Book> arr = new ArrayList<>();
        rbAsName.setToggleGroup(grp);
        rbDesName.setToggleGroup(grp);
        rbAsRating.setToggleGroup(grp);
        rbDesRat.setToggleGroup(grp);
        displayResults(panelController.results, 3);
    }
    public void displayResults(ArrayList<Book> results, int itemsPerRow) {
        // Create a GridPane for the layout
        System.out.println(results + " aaaa");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal gap between cells
        gridPane.setVgap(10); // Vertical gap between rows
        gridPane.setStyle("-fx-padding: 10;");
    
        // Loop through the search results and populate the grid
        for (int i = 0; i < results.size(); i++) {
            
            String result = results.get(i).getName() + " - " + results.get(i).getAuthor();
    
            // Create a panel for each result
            BorderPane resultPanel = new BorderPane();
            resultPanel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1;");
            resultPanel.setPrefWidth(200); // Set width
            resultPanel.setPrefHeight(200); // Set height
    
            // Add content to the panel
            Label resultLabel = new Label(result);
            resultPanel.setTop(resultLabel);
            
            // try {
            //     Image image = new Image(getClass().getResource("/com/example/600.jpeg").toExternalForm());
            //     ImageView imageView = new ImageView(image);
            //     imageView.setFitWidth(100); // Set width of the image
            //     imageView.setFitHeight(100); // Set height of the image
            //     imageView.setPreserveRatio(true); // Preserve the aspect ratio
    
            //     // Set the image in the center of the BorderPane
            //     resultPanel.setCenter(imageView);
            // } catch (Exception e) {
            //     System.out.println("Error loading image: " + e.getMessage());
            // }

            resultPanel.setOnMouseClicked(event -> {
                System.out.println("Clicked on panel.");
    
                // Example: Call a method to handle this click
                handleSearchClick();
            });

            // Center the text within the panel
            resultLabel.setStyle("-fx-alignment: center; -fx-font-size: 14;");
    
            // Calculate the row and column index
            int row = i / itemsPerRow; // Integer division gives the row
            int col = i % itemsPerRow; // Modulus gives the column
    
            // Add the panel to the grid
            gridPane.add(resultPanel, col, row);
        }
    
        // Wrap the GridPane in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.setFitToWidth(true); // Makes the scroll content fit the width of the ScrollPane
        scrollPane.setPannable(true);   // Allows mouse drag scrolling
        scrollPane.setPrefSize(628, 567); // Set preferred size (adjust as needed)
    
        // Add the ScrollPane to the Pane
        pane.getChildren().clear(); // Clear any existing content
        pane.getChildren().add(scrollPane);
        // sortPane.getChildren().clear();
        // sortPane.getChildren().add(newGridPane);
    }
}
