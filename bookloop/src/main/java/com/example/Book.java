package com.example;

public class Book {
    String name;
    String author;
    String genre;
    public Book(String name,String author,String genre){
        this.name=name;
        this.author= author;
        this.genre= genre;
    }
    Book(){
       
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Name: "+ name;
    }
}