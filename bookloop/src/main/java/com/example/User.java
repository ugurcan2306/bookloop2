package com.example;
//import java.awt.print.Book;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class User {
    private String password;
    private String userName;
    //private ArrayList<Book>  books2trade;
    private  ArrayList<User> friends;
    private SimpleIntegerProperty tradeNumber;
    private String personTraded;
    //private ObservableList<GroupChat> groupChats;
    //private ObservableList<OneToOneChat> oneToOneChat;
    private ArrayList<String> markedAsRead = new ArrayList<>();
    private ArrayList<String> willTrade = new ArrayList<>();;
    //private Location location;
    //private GelenTradeRequest tradeRequest;

    public User(){
        this.userName = userName;
        
    }

    public void tradeCount(int tradeNumber){
        
            tradeNumber++;
        
    }
    /*public void tradedWithWhoCount(){
        personTraded = tradeRequest.displayCount();
    }*/
    public ArrayList<String> hasRead(String name){
         //markedAsRead = new ArrayList<>();
         //name = "aaaa";
        
            //BUTTONDA MARK AS READ TIKMLANDIYSA YANİ
            markedAsRead.add(name);
            return markedAsRead;
            //System.out.println(name);
            

        
    }
    public ArrayList<String> add2Trade(String name){
        //ObservableList<Book> willTrade = FXCollections.observableArrayList();
            //BUTTONDA ADD TO TRADE TIKMLANDIYSA YANİ
            willTrade.add(name);
            return willTrade;
        
        
    }
        
    
    public void removeFromTrade(String book, ArrayList<String> willTrade){
        //ObservableList<Book> wontTrade = FXCollections.observableArrayList();
        //ObservableList<Book> willTrade = add2Trade(book);
        
            //BUTTONDA DELETE E TIKLANDIĞINDA
            showWarning();//ARE YOU SURE YOU WANT TO DELETE THE BOOK FROM YOUR LİST
            willTrade.remove(book);
        
    }
    public void removeFromMarked(String book, ArrayList<String> markedAsread){
        //ObservableList<Book> wontTrade = FXCollections.observableArrayList();
        //ObservableList<Book> willTrade = add2Trade(book);
        
            //BUTTONDA DELETE E TIKLANDIĞINDA
            //ARE YOU SURE YOU WANT TO DELETE THE BOOK FROM YOUR LİST
            markedAsread.remove(book);
            
    }

    public void showWarning(){
        System.out.println("Are you sure you want to delete the book from your list");
    }
    public ArrayList<User> friends(String name){
        User user = new User();
        friends.add(user);
        return friends;
    }
}
