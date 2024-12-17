package com.example;

import java.util.ArrayList;
import java.util.Random;
//Arda
public class PersonelRecommendations{
    /* This class must access a user's read books list to provide a familiarity score for each genre.
     * 
     */
    // private int romance = 0;
    // private int crimeFiction = 0;
    // private int dystopic = 0;
    // private int theatre = 0;
    // private int poem = 0;
    public Book recommendManual(ArrayList<String> genreList, ArrayList<String> authorList, double minRating, double maxRating){
        Random ran = new Random();
        ArrayList<Book> alist = new ArrayList<>();
        alist = filter(genreList, authorList, minRating, maxRating);
        return alist.get(ran.nextInt(0, alist.size()));
        //Does not display group chats!!
    }
    public static ArrayList<Book> recommendFamiliar(){
        int romance = 0;
        int crimeFiction = 0;
        int dystopic = 0;
        int theatre = 0;
        int poem = 0;
        ArrayList<Book> recommendList = new ArrayList<>();
        ArrayList<Book> readBookList = SessionManager.getCurrentUser().getMarkedasRead();
        ArrayList<Book> bookList = FinderFromDatabase.ShowBookList();
        for(int i = 0; i < readBookList.size(); i++){
            if(readBookList.get(i).getGenre().toLowerCase().equals("romance")){
                romance += 100;
                dystopic += 60;
                poem += 80;
                crimeFiction += 60;
                theatre += 20;
            }
            if(readBookList.get(i).getGenre().toLowerCase().equals("crime fiction")){
                dystopic += 80;
                crimeFiction += 100;
                romance += 60;
                theatre += 20;
                poem += 20;
            }
            if(readBookList.get(i).getGenre().toLowerCase().equals("dystopic")){
                crimeFiction += 80;
                romance += 60;
                dystopic += 100;
                theatre += 20;
                poem += 20;
            }
            if(readBookList.get(i).getGenre().toLowerCase().equals("theatre")){
                dystopic += 20;
                poem += 40;
                crimeFiction += 20;
                romance += 20;
                theatre += 100;
            }
            if(readBookList.get(i).getGenre().toLowerCase().equals("poem")){
                theatre += 40;
                dystopic += 20;
                crimeFiction += 20;
                romance += 80;
                poem += 100;
            }
        }
        int romanceCount = Math.round((10 * romance) / romance + crimeFiction + dystopic + theatre + poem);
        int crimeFictionCount = Math.round((10 * crimeFiction) / romance + crimeFiction + dystopic + theatre + poem);
        int dystopicCount = Math.round((10 * dystopic) / romance + crimeFiction + dystopic + theatre + poem);
        int theatreCount = Math.round((10 * theatre) / romance + crimeFiction + dystopic + theatre + poem);
        int poemCount = Math.round((10 * poem) / romance + crimeFiction + dystopic + theatre + poem);

        for(int i = 0; i < bookList.size() && romanceCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("romance") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                romanceCount--;
            }
        }
        for(int i = 0; i < bookList.size() && crimeFictionCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("crime fiction") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                crimeFictionCount--;
            }
        }
        for(int i = 0; i < bookList.size() && dystopicCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("dystopic") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                dystopicCount--;
            }
        }
        for(int i = 0; i < bookList.size() && theatreCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("theatre") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                theatreCount--;
            }
        }
        for(int i = 0; i < bookList.size() && poemCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("poem") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                poemCount--;
            }
        }
        romance = 0;
        romanceCount = 0;
        crimeFiction = 0;
        crimeFictionCount = 0;
        dystopic = 0;
        dystopicCount = 0;
        theatre = 0;
        theatreCount = 0;
        poem = 0;
        poemCount = 0;
        return recommendList;
    }
    public static ArrayList<Book> recommendUnfamiliar(){
        int romance = 0;
        int crimeFiction = 0;
        int dystopic = 0;
        int theatre = 0;
        int poem = 0;
        ArrayList<Book> recommendList = new ArrayList<>();
        ArrayList<Book> readBookList = SessionManager.getCurrentUser().getMarkedasRead();
        ArrayList<Book> bookList = FinderFromDatabase.ShowBookList();
        for(int i = 0; i < readBookList.size(); i++){
            if(readBookList.get(i).getGenre().toLowerCase().equals("romance")){
                romance += 0;
                dystopic += 40;
                poem += 20;
                crimeFiction += 40;
                theatre += 80;
            }
            if(readBookList.get(i).getGenre().toLowerCase().equals("crime fiction")){
                dystopic += 20;
                crimeFiction += 0;
                romance += 40;
                theatre += 80;
                poem += 80;
            }
            if(readBookList.get(i).getGenre().toLowerCase().equals("dystopic")){
                crimeFiction += 20;
                romance += 40;
                dystopic += 0;
                theatre += 80;
                poem += 80;
            }
            if(readBookList.get(i).getGenre().toLowerCase().equals("theatre")){
                dystopic += 80;
                poem += 60;
                crimeFiction += 80;
                romance += 80;
                theatre += 0;
            }
            if(readBookList.get(i).getGenre().toLowerCase().equals("poem")){
                theatre += 60;
                dystopic += 80;
                crimeFiction += 80;
                romance += 20;
                poem += 0;
            }
        }
        int romanceCount = Math.round((10 * romance) / romance + crimeFiction + dystopic + theatre + poem);
        int crimeFictionCount = Math.round((10 * crimeFiction) / romance + crimeFiction + dystopic + theatre + poem);
        int dystopicCount = Math.round((10 * dystopic) / romance + crimeFiction + dystopic + theatre + poem);
        int theatreCount = Math.round((10 * theatre) / romance + crimeFiction + dystopic + theatre + poem);
        int poemCount = Math.round((10 * poem) / romance + crimeFiction + dystopic + theatre + poem);

        for(int i = 0; i < bookList.size() && romanceCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("romance") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                romanceCount--;
            }
        }
        for(int i = 0; i < bookList.size() && crimeFictionCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("crime fiction") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                crimeFictionCount--;
            }
        }
        for(int i = 0; i < bookList.size() && dystopicCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("dystopic") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                dystopicCount--;
            }
        }
        for(int i = 0; i < bookList.size() && theatreCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("theatre") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                theatreCount--;
            }
        }
        for(int i = 0; i < bookList.size() && poemCount != 0; i++){
            if(bookList.get(i).getGenre().toLowerCase().equals("poem") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                poemCount--;
            }
        }
        romance = 0;
        romanceCount = 0;
        crimeFiction = 0;
        crimeFictionCount = 0;
        dystopic = 0;
        dystopicCount = 0;
        theatre = 0;
        theatreCount = 0;
        poem = 0;
        poemCount = 0;
        return recommendList;
    }
    public ArrayList<Book> filter(ArrayList<String> genreList, ArrayList<String> authorList, double minRating, double maxRating){
        ArrayList<Book> bookList = FinderFromDatabase.ShowBookList();
        ArrayList<Book> filteredBooks = new ArrayList<>();
        ArrayList<Book> temp = new ArrayList<>();
        if(!authorList.isEmpty()){
            for(int i = 0; i < authorList.size(); i++){
                for(int j = 0; j < bookList.size(); j++){
                    if(bookList.get(j).getAuthor().equals(authorList.get(i))){
                        filteredBooks.add(bookList.get(j));
                    }
                }
            }
            if(!genreList.isEmpty()){
                for(int i = 0; i < authorList.size(); i++){
                    for(int j = 0; j < filteredBooks.size(); j++){
                        if(filteredBooks.get(j).getGenre().equals(genreList.get(i))){
                            temp.add(filteredBooks.get(j));
                        }
                    }
                }
                filteredBooks = temp;
            }
            for(int i = 0; i < filteredBooks.size(); i++){
                if(filteredBooks.get(i).getRate() < minRating || filteredBooks.get(i).getRate() > maxRating){
                    filteredBooks.remove(i);
                }
            }
            return filteredBooks;
        }
        else if(!genreList.isEmpty()){
            for(int i = 0; i < authorList.size(); i++){
                for(int j = 0; j < bookList.size(); j++){
                    if(bookList.get(j).getGenre().equals(genreList.get(i))){
                        filteredBooks.add(bookList.get(j));
                    }
                }
            }
            for(int i = 0; i < filteredBooks.size(); i++){
                if(filteredBooks.get(i).getRate() < minRating || filteredBooks.get(i).getRate() > maxRating){
                    filteredBooks.remove(i);
                }
            }
            return filteredBooks;
        }
        else{
            for(int i = 0; i < bookList.size(); i++){
                if(bookList.get(i).getRate() >= minRating && bookList.get(i).getRate() <= maxRating){
                    filteredBooks.add(bookList.get(i));
                }
            }
            return filteredBooks;
        }
    }
}
