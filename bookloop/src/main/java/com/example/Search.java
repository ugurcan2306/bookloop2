package com.example;

import java.util.ArrayList;
//Arda
public class Search implements Sort{


    
    public static ArrayList<Book> search(String text){
        if(text.equals("")){
            ArrayList<Book> emptyArr = new ArrayList<>();
        }
        ArrayList<Book> bookList = FinderFromDatabase.ShowBookList();
        System.out.println(bookList);
        text = text.trim();
        String[] splitted = text.split("\\s+");
        ArrayList<Integer> score = new ArrayList<>();
        ArrayList<Book> searchResult = new ArrayList<>();
        for(int i = 0; i < bookList.size(); i++){
            score.add(0);
        }
        for(int j = 0; j < splitted.length; j++){
            for(int i = 0; i < bookList.size(); i++){
                if(bookList.get(i).getName().toLowerCase().contains(splitted[j].toLowerCase())){
                    score.set(i ,score.get(i) + 10);
                }
                if(bookList.get(i).getAuthor().toLowerCase().contains(splitted[j].toLowerCase())){
                    score.set(i ,score.get(i) + 10);
                }
                if(bookList.get(i).getDescription().toLowerCase().contains(splitted[j].toLowerCase())){
                    score.set(i, score.get(i) + 1);
                }
            }
        }
        int greatestIndex = 0;
        boolean relevant = true;
        for(int j = 0; j < bookList.size() && relevant == true; j++){
            for(int i = 0; i < score.size(); i++){
                relevant = false;
                if(score.get(greatestIndex) < score.get(i)){
                    greatestIndex = i;
                }
                if(i == score.size() - 1 && score.get(greatestIndex) != 0){
                    relevant = true;
                    searchResult.add(bookList.get(greatestIndex));
                    bookList.remove(greatestIndex);
                    score.remove(greatestIndex);
                    greatestIndex = 0;
                }
            }
        }
        return searchResult;
    }
    public static ArrayList<Book> filter(ArrayList<String> genreList, ArrayList<String> authorList, double minRating, double maxRating){
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
            for(int i = 0; i < genreList.size(); i++){
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

    public static ArrayList<Book> sortByAscendingName(ArrayList<Book> results) {
        ArrayList<Book> bookList = FinderFromDatabase.ShowBookList();
        ArrayList<Book> sortedList = results;
        Book temp;
        boolean changed = true;
        for (int i = 0; i < sortedList.size() && changed; i++) {
            changed = false;
            for (int j = 0; j + 1 < sortedList.size() - i; j++) {
                if (sortedList.get(j).getName().compareToIgnoreCase(sortedList.get(j + 1).getName()) < 0){
                    changed = true;
                    temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    public static ArrayList<Book> sortByAscendingRating(ArrayList<Book> results) {
        ArrayList<Book> bookList = FinderFromDatabase.ShowBookList();
        ArrayList<Book> sortedList = results;
        Book temp;
        boolean changed = true;
        for (int i = 0; i < sortedList.size() && changed; i++) {
            changed = false;
            for (int j = 0; j + 1< sortedList.size() - i; j++) {
                if (sortedList.get(j).getRate() > sortedList.get(j).getRate()){
                    changed = true;
                    temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    public static ArrayList<Book> sortByDescendingName(ArrayList<Book> results) {
        ArrayList<Book> bookList = FinderFromDatabase.ShowBookList();
        ArrayList<Book> sortedList = results;
        Book temp;
        boolean changed = true;
        for (int i = 0; i < sortedList.size() && changed; i++) {
            changed = false;
            for (int j = 0; j + 1< sortedList.size() - i; j++) {
                if (sortedList.get(j).getName().compareToIgnoreCase(sortedList.get(j + 1).getName()) > 0){
                    changed = true;
                    temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    public static ArrayList<Book> sortByDescendingRating(ArrayList<Book> results) {
        ArrayList<Book> bookList = FinderFromDatabase.ShowBookList();
        ArrayList<Book> sortedList = results;
        Book temp;
        boolean changed = true;
        for (int i = 0; i < sortedList.size() && changed; i++) {
            changed = false;
            for (int j = 0; j + 1< sortedList.size() - i; j++) {
                if (sortedList.get(j).getRate() < sortedList.get(j).getRate()){
                    changed = true;
                    temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }
}

