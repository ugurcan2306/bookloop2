<<<<<<< HEAD
package com.example;

import java.util.ArrayList;
import java.util.Objects;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firestore.v1.StructuredAggregationQuery.Aggregation.Count;

import client.FireStoreHelper;


public class User {
    private String Password;
    private String Email;
    private String Username;
    private ArrayList<Book> BookstoTrade;
    private ArrayList<User> Friends;
    private ArrayList<GroupChat> GroupChat;
    private ArrayList<OneToOneChat> OneToOneChat;
    private ArrayList<Book> MarkedasRead ;
    private String City;
    private String Country;
    private ArrayList<TradeRequest> ReceivedTradeRequests;
    private ArrayList<TradeRequest> SentTradeRequests;
    
    

    //private GelenTradeRequest tradeRequest;

    public User(String username,String email,String password,String City,String country){
   
    this.Username= username;
    this.Email= email;
    this.Password=password;
    this.City=City;
    this.Country= country;
     
    this.BookstoTrade=new ArrayList<>();
    this.MarkedasRead= new ArrayList<>();
    this.Friends= new ArrayList<User>();
    this.GroupChat=new ArrayList<>();
    this.OneToOneChat= new ArrayList<>();
    this.ReceivedTradeRequests= new ArrayList<>();
    this.SentTradeRequests= new ArrayList<>(); 
    }
    User(){
        this.BookstoTrade=new ArrayList<Book>();
        this.MarkedasRead= new ArrayList<Book>();
        this.Friends= new ArrayList<User>();
        this.GroupChat=new ArrayList<>();
        this.OneToOneChat= new ArrayList<>();
        this.ReceivedTradeRequests= new ArrayList<>();
        this.SentTradeRequests= new ArrayList<>(); 
    }
    public void markedAsRead(String name, String author,String genre){
        Book book= createABook(name, author, genre);
        for (int i = 0; i < MarkedasRead.size(); i++) {
            if (MarkedasRead.get(i).getName().equals(book.getName())&& MarkedasRead.get(i).getAuthor().equals(book.getAuthor()) && MarkedasRead.get(i).getGenre().equals(book.getGenre())) {
                return;
            }
        } 
        MarkedasRead.add(book);
    }
    public void addtoTrade(String name, String author,String genre){
    Book book= createABook(name, author, genre);// görselini nasıl eklicek ag ya
    
        for (int i = 0; i < BookstoTrade.size(); i++) {
            if (BookstoTrade.get(i).getName().equals(book.getName())&& BookstoTrade.get(i).getAuthor().equals(book.getAuthor()) && BookstoTrade.get(i).getGenre().equals(book.getGenre())) {
                return;
            }
        }
        BookstoTrade.add(book);
    }

    public void removeFromTrade(Book book){
        
        if (BookstoTrade.contains(book)) {
            showWarning();//ARE YOU SURE YOU WANT TO DELETE THE BOOK FROM YOUR LİST
            BookstoTrade.remove(book);}
        else{
            return;
        }
       
        
    }
    public void removeFromMarked(Book book){
        if (MarkedasRead.contains(book)) {
            MarkedasRead.remove(book);
        }
        else{
            return;
        }
        
    }

    public void showWarning(){
        // frame i lazım if o button selected şu if şu button selected şu
}
    public ArrayList<User> getFriends() {
        return Friends;
    }

    public static Book createABook(String name, String author, String genre){
        return new Book(name,author,genre);

    }

    public void changePassword(String password) {
        this.Password = password;
    }
    public void changeEmail(String email) {
        this.Email = email;
    }
    @Override
    public String toString() {
        return Username;
    } 
    public ArrayList<Book> getBookstoTrade() {
        return BookstoTrade;
    }
    public String getCity() {
        return City;
    }
    public String getEmail() {
        return Email;
    }
    public String getCountry() {
        return Country;
    }
    public ArrayList<GroupChat> getGroupChat() {
        return GroupChat;
    }
    public ArrayList<Book> getMarkedasRead() {
        return MarkedasRead;
    }
    public ArrayList<OneToOneChat> getOneToOneChat() {
        return OneToOneChat;
    }
    public String getPassword() {
        return Password;
    }
    public String getUsername() {
        return Username;
    }
    public void setBookstoTrade(ArrayList<Book> bookstoTrade) {
        BookstoTrade = bookstoTrade;
    }
    public void setCity(String city) {
        this.City = city;
    }
    public void setCountry(String country) {
        Country = country;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setFriends(ArrayList<User> friends) {
        Friends = friends;
    }
    public void setGroupChat(ArrayList<GroupChat> groupChats) {
        GroupChat = groupChats;
    }
    public void setMarkedasRead(ArrayList<Book> markedAsRead) {
        MarkedasRead = markedAsRead;
    }
    public void setOneToOneChat(ArrayList<OneToOneChat> oneToOneChat) {
        OneToOneChat = oneToOneChat;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public void addToFriends(User user){
        Friends.add(user);
    }
   public ArrayList<TradeRequest> getReceivedTradeRequests() {
       return ReceivedTradeRequests;
   }
   public ArrayList<TradeRequest> getSentTradeRequests() {
       return SentTradeRequests;
   }
   public void setReceivedTradeRequests(ArrayList<TradeRequest> receivedTradeRequests) {
       ReceivedTradeRequests = receivedTradeRequests;
   }
   public void setSentTradeRequests(ArrayList<TradeRequest> sentTradeRequests) {
       SentTradeRequests = sentTradeRequests;
   }
   
    @Override
public boolean equals(Object obj) {
    if (this == obj) return true; // Same object check
    if (obj == null || getClass() != obj.getClass()) return false; // Null or different class check
    
    User user = (User) obj; // Cast the object to User
    return Username.equals(user.Username) && // Username comparison
           Email.equals(user.Email) && // Email comparison
           City.equals(user.City) && 
           Password.equals(user.Password)&&
           Country.equals(user.Country); 
}
@Override
public int hashCode() {
   return Objects.hash(Username, Email, City,Password,Country);
}

    
=======
package com.example;

import java.util.ArrayList;
import java.util.Objects;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firestore.v1.StructuredAggregationQuery.Aggregation.Count;

import client.FireStoreHelper;


public class User {
    private String Password;
    private String Email;
    private String Username;
    private ArrayList<Book> BookstoTrade;
    private ArrayList<User> Friends;
    private ArrayList<GroupChat> GroupChat;
    private ArrayList<OneToOneChat> OneToOneChat;
    private ArrayList<Book> MarkedasRead ;
    private String City;
    private String Country;
    private ArrayList<TradeRequest> ReceivedTradeRequests;
    private ArrayList<TradeRequest> SentTradeRequests;
    
    

    //private GelenTradeRequest tradeRequest;

    public User(String username,String email,String password,String City,String country){
   
    this.Username= username;
    this.Email= email;
    this.Password=password;
    this.City=City;
    this.Country= country;
     
    this.BookstoTrade=new ArrayList<>();
    this.MarkedasRead= new ArrayList<>();
    this.Friends= new ArrayList<User>();
    this.GroupChat=new ArrayList<>();
    this.OneToOneChat= new ArrayList<>();
    this.ReceivedTradeRequests= new ArrayList<>();
    this.SentTradeRequests= new ArrayList<>(); 
    }
    User(){
        this.BookstoTrade=new ArrayList<Book>();
        this.MarkedasRead= new ArrayList<Book>();
        this.Friends= new ArrayList<User>();
        this.GroupChat=new ArrayList<>();
        this.OneToOneChat= new ArrayList<>();
        this.ReceivedTradeRequests= new ArrayList<>();
        this.SentTradeRequests= new ArrayList<>(); 
    }
    public void markedAsRead(String name, String author,String genre){
        Book book= createABook(name, author, genre);
        for (int i = 0; i < MarkedasRead.size(); i++) {
            if (MarkedasRead.get(i).getName().equals(book.getName())&& MarkedasRead.get(i).getAuthor().equals(book.getAuthor()) && MarkedasRead.get(i).getGenre().equals(book.getGenre())) {
                return;
            }
        } 
        MarkedasRead.add(book);
    }
    public void addtoTrade(String name, String author,String genre){
    Book book= createABook(name, author, genre);// görselini nasıl eklicek ag ya
    
        for (int i = 0; i < BookstoTrade.size(); i++) {
            if (BookstoTrade.get(i).getName().equals(book.getName())&& BookstoTrade.get(i).getAuthor().equals(book.getAuthor()) && BookstoTrade.get(i).getGenre().equals(book.getGenre())) {
                return;
            }
        }
        BookstoTrade.add(book);
    }

    public void removeFromTrade(Book book){
        
        if (BookstoTrade.contains(book)) {
            showWarning();//ARE YOU SURE YOU WANT TO DELETE THE BOOK FROM YOUR LİST
            BookstoTrade.remove(book);}
        else{
            return;
        }
       
        
    }
    public void removeFromMarked(Book book){
        if (MarkedasRead.contains(book)) {
            MarkedasRead.remove(book);
        }
        else{
            return;
        }
        
    }

    public void showWarning(){
        // frame i lazım if o button selected şu if şu button selected şu
}
    public ArrayList<User> getFriends() {
        return Friends;
    }

    public static Book createABook(String name, String author, String genre){
        return new Book(name,author,genre);

    }

    public void changePassword(String password) {
        this.Password = password;
    }
    public void changeEmail(String email) {
        this.Email = email;
    }
    @Override
    public String toString() {
        return Username;
    } 
    public ArrayList<Book> getBookstoTrade() {
        return BookstoTrade;
    }
    public String getCity() {
        return City;
    }
    public String getEmail() {
        return Email;
    }
    public String getCountry() {
        return Country;
    }
    public ArrayList<GroupChat> getGroupChat() {
        return GroupChat;
    }
    public ArrayList<Book> getMarkedasRead() {
        return MarkedasRead;
    }
    public ArrayList<OneToOneChat> getOneToOneChat() {
        return OneToOneChat;
    }
    public String getPassword() {
        return Password;
    }
    public String getUsername() {
        return Username;
    }
    public void setBookstoTrade(ArrayList<Book> bookstoTrade) {
        BookstoTrade = bookstoTrade;
    }
    public void setCity(String city) {
        this.City = city;
    }
    public void setCountry(String country) {
        Country = country;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setFriends(ArrayList<User> friends) {
        Friends = friends;
    }
    public void setGroupChat(ArrayList<GroupChat> groupChats) {
        GroupChat = groupChats;
    }
    public void setMarkedasRead(ArrayList<Book> markedAsRead) {
        MarkedasRead = markedAsRead;
    }
    public void setOneToOneChat(ArrayList<OneToOneChat> oneToOneChat) {
        OneToOneChat = oneToOneChat;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public void addToFriends(User user){
        Friends.add(user);
    }
   public ArrayList<TradeRequest> getReceivedTradeRequests() {
       return ReceivedTradeRequests;
   }
   public ArrayList<TradeRequest> getSentTradeRequests() {
       return SentTradeRequests;
   }
   public void setReceivedTradeRequests(ArrayList<TradeRequest> receivedTradeRequests) {
       ReceivedTradeRequests = receivedTradeRequests;
   }
   public void setSentTradeRequests(ArrayList<TradeRequest> sentTradeRequests) {
       SentTradeRequests = sentTradeRequests;
   }
   
    @Override
public boolean equals(Object obj) {
    if (this == obj) return true; // Same object check
    if (obj == null || getClass() != obj.getClass()) return false; // Null or different class check
    
    User user = (User) obj; // Cast the object to User
    return Username.equals(user.Username) && // Username comparison
           Email.equals(user.Email) && // Email comparison
           City.equals(user.City) && 
           Password.equals(user.Password)&&
           Country.equals(user.Country); 
}
@Override
public int hashCode() {
   return Objects.hash(Username, Email, City,Password,Country);
}

    
>>>>>>> origin/main
}