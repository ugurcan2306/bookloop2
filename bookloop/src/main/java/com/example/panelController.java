package com.example;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.checkerframework.checker.units.qual.min;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class panelController {
    public static String searchText;
    public static ArrayList<Book> results;
    CheckBox cb1 = new CheckBox("romance");
    CheckBox cb2 = new CheckBox("crime fiction");
    CheckBox cb3 = new CheckBox("dystopic");
    CheckBox cb4 = new CheckBox("theatre");
    CheckBox cb5 = new CheckBox("poem");
    ComboBox<String> comboBox = new ComboBox<>();
    TextField textField1 = new TextField();
    TextField textField2 = new TextField();
    Label lbl1 = new Label("Genres");
    Label lbl2 = new Label("Authors");
    Label lbl3 = new Label("Rating");

    boolean filterPaneOpen = false;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane centerPane;

    @FXML
    private Button chatsButton;

    @FXML
    private Button forYouButton;

    @FXML
    private Button getRecommendationButton;

    @FXML
    private Pane leftPane;

    @FXML
    private Pane mainPane;

    @FXML
    private Button myTradeRequestsButton;

    @FXML
    private Button profileButton;
    
    @FXML
    private Button filterButton;

    @FXML
    private Pane filterPane;

    @FXML
    private Pane rightPane;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Pane topPane;

    @FXML
    private Button tradeRequestsButton;

    @FXML
    private void handlesearchButtonAction(ActionEvent event){
        System.out.println("You clicked me");
        FxmlLoader obj = new FxmlLoader();
        results = Search.search(searchBar.getText());
        Pane view = obj.getPage("searcbarSonuc.fxml");
        centerPane.setCenter(view);
    }
    @FXML
    private void handlefilterButtonAction(ActionEvent event){
        if(!filterPaneOpen){
            filterPaneOpen = true;
            secretFilterButton.setVisible(true);
            filterPane.setVisible(true);
            filterPane.setStyle("-fx-background-color:  #BD5454");

            GridPane gridPane = new GridPane();
            gridPane.setVgap(10);
            gridPane.setHgap(30);

            

            gridPane.add(lbl1, 1, 1);
            gridPane.add(cb1, 1, 2);
            gridPane.add(cb2, 1, 3);
            gridPane.add(cb3, 1, 4);
            gridPane.add(cb4, 1, 5);
            gridPane.add(cb5, 1, 6);

            comboBox.getItems().addAll("Sabahattin Ali", "Jane Austen", "Orhan Pamuk");
            comboBox.setPrefWidth(100);

            gridPane.add(lbl2, 2, 1);
            gridPane.add(comboBox, 2, 2);
            
            textField1.setPromptText("Enter min rating");
            textField1.setPrefWidth(100);
        
            textField2.setPromptText("Enter max rating");
            textField2.setPrefWidth(100);

            gridPane.add(lbl3, 3, 1);
            gridPane.add(textField1, 3, 2);
            gridPane.add(textField2, 3, 3);

            // Button button = new Button("Filter");
            // button.setLayoutX(337);
            // button.setLayoutX(252);
            filterPane.getChildren().add(gridPane);
            //filterPane.getChildren().add(button);
        }
        else{
            comboBox.getItems().remove(0, comboBox.getItems().size());
            filterPaneOpen = false;
            secretFilterButton.setVisible(false);
            filterPane.setVisible(false);
        }
    }
    private void handleRecommendationClick(String author, String book){
        System.out.println("aaa");
    }
    @FXML
    private void handlesecretFilterButtonAction(ActionEvent event){
        ArrayList<String> genreList = new ArrayList<>();
        ArrayList<String> authorList = new ArrayList<>();
        double minRating = 0;
        double maxRating = 5;
        if(cb1.isSelected()){
            genreList.add(cb1.getText());
        }
        if(cb2.isSelected()){
            genreList.add(cb2.getText());
        }
        if(cb3.isSelected()){
            genreList.add(cb3.getText());
        }
        if(cb4.isSelected()){
            genreList.add(cb4.getText());
        }
        if(cb5.isSelected()){
            genreList.add(cb5.getText());
        }
        if(comboBox.getValue() != null){
            authorList.add(comboBox.getValue());
        }
        if(!textField1.getText().equals("")){
            String temp = textField1.getText();
            temp.replace(".", "");
            boolean isNumber = true;
            for(int i = 0; i < temp.length() && isNumber; i++){
                if(!Character.isDigit(temp.charAt(i))){
                    isNumber = false;
                }
            }
            if(isNumber){
                minRating = Double.parseDouble(textField1.getText());
            }
        }
        if(!textField2.getText().equals("")){
            String temp = textField2.getText();
            temp.replace(".", "");
            boolean isNumber = true;
            for(int i = 0; i < temp.length() && isNumber; i++){
                if(!Character.isDigit(temp.charAt(i))){
                    isNumber = false;
                }
            }
            if(isNumber){
                maxRating = Double.parseDouble(textField1.getText());
            }
        }
        results = Search.filter(genreList, authorList, minRating, maxRating);
        System.out.println("You clicked me");
        FxmlLoader obj = new FxmlLoader();
        Pane view = obj.getPage("searcbarSonuc.fxml");
        centerPane.setCenter(view);
        filterPaneOpen = false;
        secretFilterButton.setVisible(false);
        filterPane.setVisible(false);
    }
    @FXML private void handleGetRecommendationButtonAction(ActionEvent event){
        BorderPane bPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(30);

        gridPane.add(new Label("Genres"), 1, 1);
        gridPane.add(cb1, 1, 2);
        gridPane.add(cb2, 1, 3);
        gridPane.add(cb3, 1, 4);
        gridPane.add(cb4, 1, 5);
        gridPane.add(cb5, 1, 6);

        comboBox.getItems().addAll("Sabahattin Ali", "Jane Austen", "Orhan Pamuk");
        comboBox.setPrefWidth(100);

        gridPane.add(new Label("Authors"), 2, 1);
        gridPane.add(comboBox, 2, 2);
        
        textField1.setPromptText("Enter min rating");
        textField1.setPrefWidth(75);
    
        textField2.setPromptText("Enter max rating");
        textField2.setPrefWidth(75);

        gridPane.add(new Label("Rating"), 3, 1);
        gridPane.add(textField1, 3, 2);
        gridPane.add(textField2, 3, 3);

        Button reccomendFilterButtton = new Button("Filter");
        gridPane.add(reccomendFilterButtton, 3, 7);
        reccomendFilterButtton.setOnAction(e -> {
            handleFilterClick();
        });
        bPane.setCenter(gridPane);
        centerPane.setCenter(bPane);
    }
    private void handleFilterClick(){
        ArrayList<String> genreList = new ArrayList<>();
        ArrayList<String> authorList = new ArrayList<>();
        double minRating = 0;
        double maxRating = 5;
        if(cb1.isSelected()){
            genreList.add(cb1.getText());
        }
        if(cb2.isSelected()){
            genreList.add(cb2.getText());
        }
        if(cb3.isSelected()){
            genreList.add(cb3.getText());
        }
        if(cb4.isSelected()){
            genreList.add(cb4.getText());
        }
        if(cb5.isSelected()){
            genreList.add(cb5.getText());
        }
        if(comboBox.getValue() != null){
            authorList.add(comboBox.getValue());
        }
        if(textField1.getText() != null){
            String temp = textField1.getText();
            temp.replace(".", "");
            boolean isNumber = true;
            for(int i = 0; i < temp.length() && isNumber; i++){
                if(!Character.isDigit(temp.charAt(i))){
                    isNumber = false;
                }
            }
            if(isNumber){
                minRating = Double.parseDouble(textField1.getText());
            }
        }
        if(textField2.getText() != null){
            String temp = textField2.getText();
            temp.replace(".", "");
            boolean isNumber = true;
            for(int i = 0; i < temp.length() && isNumber; i++){
                if(!Character.isDigit(temp.charAt(i))){
                    isNumber = false;
                }
            }
            if(isNumber){
                maxRating = Double.parseDouble(textField1.getText());
            }
        }
        results = Search.filter(genreList, authorList, minRating, maxRating);
    }
    @FXML
    private Pane familiarPane;

    @FXML
    private Pane unfamiliarPane;

    @FXML
    private Button secretFilterButton;

    @FXML
    void initialize() {
        assert centerPane != null : "fx:id=\"centerPane\" was not injected: check your FXML file 'panel.fxml'.";
        assert chatsButton != null : "fx:id=\"chatsButton\" was not injected: check your FXML file 'panel.fxml'.";
        assert forYouButton != null : "fx:id=\"forYouButton\" was not injected: check your FXML file 'panel.fxml'.";
        assert getRecommendationButton != null : "fx:id=\"getRecommendationButton\" was not injected: check your FXML file 'panel.fxml'.";
        assert leftPane != null : "fx:id=\"leftPane\" was not injected: check your FXML file 'panel.fxml'.";
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'panel.fxml'.";
        assert myTradeRequestsButton != null : "fx:id=\"myTradeRequestsButton\" was not injected: check your FXML file 'panel.fxml'.";
        assert profileButton != null : "fx:id=\"profileButton\" was not injected: check your FXML file 'panel.fxml'.";
        assert rightPane != null : "fx:id=\"rightPane\" was not injected: check your FXML file 'panel.fxml'.";
        assert searchBar != null : "fx:id=\"searchBar\" was not injected: check your FXML file 'panel.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'panel.fxml'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'panel.fxml'.";
        assert topPane != null : "fx:id=\"topPane\" was not injected: check your FXML file 'panel.fxml'.";
        assert tradeRequestsButton != null : "fx:id=\"tradeRequestsButton\" was not injected: check your FXML file 'panel.fxml'.";
        assert familiarPane != null : "fx:id=\"familiarPane\" was not injected: check your FXML file 'panel.fxml'.";
        assert unfamiliarPane != null : "fx:id=\"unfamiliarPane\" was not injected: check your FXML file 'panel.fxml'.";
        assert filterButton != null : "fx:id=\"filterButton\" was not injected: check your FXML file 'panel.fxml'.";
        assert filterPane != null : "fx:id=\"filterPane\" was not injected: check your FXML file 'panel.fxml'.";
        assert secretFilterButton != null : "fx:id=\"secretFilterButton\" was not injected: check your FXML file 'panel.fxml'.";
        filterPane.setVisible(false);
        secretFilterButton.setVisible(false);


        ArrayList<Book> familiarRecommendations = new ArrayList<>();
        ArrayList<Book> unfamiliarRecommendations = new ArrayList<>();
        familiarRecommendations = PersonelRecommendations.recommendFamiliar();
        unfamiliarRecommendations = PersonelRecommendations.recommendUnfamiliar();
        fillFamiliarRecommendations(familiarRecommendations);
        fillUnfamiliarRecommendations(unfamiliarRecommendations);
    }
    public void fillFamiliarRecommendations(ArrayList<Book> familiarRecommendations){
        VBox VBox = new VBox(10);
        VBox.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");
        for(int i = 0; i < familiarRecommendations.size(); i++){
            String author = familiarRecommendations.get(i).getAuthor();
            String book = familiarRecommendations.get(i).getName();

            BorderPane resultPanel = new BorderPane();
            resultPanel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1");
            resultPanel.setPrefHeight(120);
            resultPanel.setPrefWidth(120);

            Label authorLabel = new Label(author);
            resultPanel.setTop(authorLabel);

            Label bookLabel = new Label(book);
            resultPanel.setBottom(bookLabel);

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
                System.out.println("Clicked on panel for book: " + book + " by author: " + author);
    
                // Example: Call a method to handle this click
                handleRecommendationClick(author, book);
            });

            VBox.getChildren().add(resultPanel);
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(VBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(130, 290);

        familiarPane.getChildren().clear();
        familiarPane.getChildren().add(scrollPane);
    }
    public void fillUnfamiliarRecommendations(ArrayList<Book> familiarRecommendations){
        VBox VBox = new VBox(10);
        VBox.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");
        for(int i = 0; i < familiarRecommendations.size(); i++){
            String author = familiarRecommendations.get(i).getAuthor();
            String book = familiarRecommendations.get(i).getName();

            BorderPane resultPanel = new BorderPane();
            resultPanel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1");
            resultPanel.setPrefHeight(120);
            resultPanel.setPrefWidth(120);

            Label authorLabel = new Label(author);
            resultPanel.setTop(authorLabel);

            Label bookLabel = new Label(book);
            resultPanel.setBottom(bookLabel);

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
                System.out.println("Clicked on panel for book: " + book + " by author: " + author);
    
                // Example: Call a method to handle this click
                handleRecommendationClick(author, book);
            });

            VBox.getChildren().add(resultPanel);
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(VBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(130, 290);

        unfamiliarPane.getChildren().clear();
        unfamiliarPane.getChildren().add(scrollPane);
    }
}

