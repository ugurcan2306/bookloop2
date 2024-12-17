<<<<<<< HEAD
package com.example;
import java.util.ArrayList;
import java.util.Objects;

public class Book {
    String Name;
    String Author;
    String Genre;
    String Description;
    ArrayList<User> Bookowners;
    ArrayList<Comment> Comments;
    int NumberOfRatings;
    int Rate;


    public Book(String name,String author,String genre){
        this.Name=name;
        this.Author= author;
        this.Genre= genre;
        this.Description= "";
        this.Bookowners=null;
        this.Comments=null;
        NumberOfRatings=0;
        Rate=0;
    }
    Book(){
       
    }
    public String getAuthor() {
        return Author;
    }
    public String getGenre() {
        return Genre;
    }
    public String getName() {
        return Name;
    }
    public void setAuthor(String author) {
        this.Author = author;
    }
    public void setGenre(String genre) {
        this.Genre = genre;
    }
    public void setName(String name) {
        this.Name = name;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Aynı nesne kontrolü
        if (obj == null || getClass() != obj.getClass()) return false; // Null veya farklı sınıf kontrolü
        Book book = (Book) obj; // Diğer nesneye dönüştürme
        return Objects.equals(Name, book.Name) && // İsim karşılaştırması
               Objects.equals(Author, book.Author) && // Yazar karşılaştırması
               Objects.equals(Genre, book.Genre); // ISBN karşılaştırması
    }

    // hashCode() metodunu override et
    @Override
    public int hashCode() {
        return Objects.hash(Name, Author, Genre); // Eşitlik için kullanılan alanlar
    }

    @Override
    public String toString() {
        return "Book{" +
               "name='" + Name + '\'' +
               ", author='" + Author + '\'' +
               ", genre='" + Genre + '\'' +
               '}';
    }
    public ArrayList<User> getBookowners() {
        return Bookowners;
    }
    public ArrayList<Comment> getComments() {
        return Comments;
    }
    public String getDescription() {
        return Description;
    }
    public int getNumberOfRatings() {
        return NumberOfRatings;
    }
    public void setBookowners(ArrayList<User> bookowners) {
        Bookowners = bookowners;
    }
    public void setComments(ArrayList<Comment> comments) {
        Comments = comments;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public void setNumberOfRatings(int numberOfRatings) {
        NumberOfRatings = numberOfRatings;
    }
    public int getRate() {
        return Rate;
    }
    public void setRate(int rate) {
        Rate = rate;
    }
}

=======
package com.example;
import java.util.ArrayList;
import java.util.Objects;

public class Book {
    String Name;
    String Author;
    String Genre;
    String Description;
    ArrayList<User> Bookowners;
    ArrayList<Comment> Comments;
    int NumberOfRatings;
    int Rate;


    public Book(String name,String author,String genre){
        this.Name=name;
        this.Author= author;
        this.Genre= genre;
        this.Description= "";
        this.Bookowners=null;
        this.Comments=null;
        NumberOfRatings=0;
        Rate=0;
    }
    Book(){
       
    }
    public String getAuthor() {
        return Author;
    }
    public String getGenre() {
        return Genre;
    }
    public String getName() {
        return Name;
    }
    public void setAuthor(String author) {
        this.Author = author;
    }
    public void setGenre(String genre) {
        this.Genre = genre;
    }
    public void setName(String name) {
        this.Name = name;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Aynı nesne kontrolü
        if (obj == null || getClass() != obj.getClass()) return false; // Null veya farklı sınıf kontrolü
        Book book = (Book) obj; // Diğer nesneye dönüştürme
        return Objects.equals(Name, book.Name) && // İsim karşılaştırması
               Objects.equals(Author, book.Author) && // Yazar karşılaştırması
               Objects.equals(Genre, book.Genre); // ISBN karşılaştırması
    }

    // hashCode() metodunu override et
    @Override
    public int hashCode() {
        return Objects.hash(Name, Author, Genre); // Eşitlik için kullanılan alanlar
    }

    @Override
    public String toString() {
        return "Book{" +
               "name='" + Name + '\'' +
               ", author='" + Author + '\'' +
               ", genre='" + Genre + '\'' +
               '}';
    }
    public ArrayList<User> getBookowners() {
        return Bookowners;
    }
    public ArrayList<Comment> getComments() {
        return Comments;
    }
    public String getDescription() {
        return Description;
    }
    public int getNumberOfRatings() {
        return NumberOfRatings;
    }
    public void setBookowners(ArrayList<User> bookowners) {
        Bookowners = bookowners;
    }
    public void setComments(ArrayList<Comment> comments) {
        Comments = comments;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public void setNumberOfRatings(int numberOfRatings) {
        NumberOfRatings = numberOfRatings;
    }
    public int getRate() {
        return Rate;
    }
    public void setRate(int rate) {
        Rate = rate;
    }
}

>>>>>>> origin/main
