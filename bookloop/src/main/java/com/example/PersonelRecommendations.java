import java.awt.print.Book;
import java.util.ArrayList;
//Arda
public class PersonelRecommendations implements Filter{
    /* This class must access a user's read books list to provide a familiarity score for each genre.
     * 
     */
    private int genre1 = 0;
    private int genre2 = 0;
    private int genre3 = 0;
    public ArrayList<Book> recommendManual(){
        return filter();
        //Does not display group chats!!
    }
    public ArrayList<Book> recommendFamiliar(){
        ArrayList<Book> recommendList = new ArrayList<>();
        for(int i = 0; i < readBookList.size(); i++){
            if(readBookList.get(i).getGenre().equals("genre1")){
                genre1 += 100;
                genre2 += 80;
                genre3 += 60;
            }
            if(readBookList.get(i).getGenre().equals("genre2")){
                genre1 += 80;
                genre2 += 100;
                genre3 += 60;
            }
            if(readBookList.get(i).getGenre().equals("genre3")){
                genre1 += 60;
                genre2 += 60;
                genre3 += 100;
            }
        }
        int genre1Count = Math.round((10 * genre1) / genre1 + genre2 + genre3);
        int genre2Count = Math.round((10 * genre2) / genre1 + genre2 + genre3);
        int genre3Count = Math.round((10 * genre3) / genre1 + genre2 + genre3);
        for(int i = 0; i < bookList.size() && genre1Count != 0; i++){
            if(bookList.get(i).getGenre().equals("genre1") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                genre1Count--;
            }
        }
        for(int i = 0; i < bookList.size() && genre2Count != 0; i++){
            if(bookList.get(i).getGenre().equals("genre2") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                genre2Count--;
            }
        }
        for(int i = 0; i < bookList.size() && genre3Count != 0; i++){
            if(bookList.get(i).getGenre().equals("genre3") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                genre3Count--;
            }
        }
        genre1 = 0;
        genre2 = 0;
        genre3 = 0;
        genre1Count = 0;
        genre2Count = 0;
        genre3Count = 0;
        return recommendList;
    }
    public ArrayList<Book> recommendUnfamiliar(){
        ArrayList<Book> recommendList = new ArrayList<>();
        for(int i = 0; i < readBookList.size(); i++){
            if(readBookList.get(i).getGenre().equals("genre1")){
                genre1 += 0;
                genre2 += 20;
                genre3 += 40;
            }
            if(readBookList.get(i).getGenre().equals("genre2")){
                genre1 += 20;
                genre2 += 0;
                genre3 += 40;
            }
            if(readBookList.get(i).getGenre().equals("genre3")){
                genre1 += 40;
                genre2 += 40;
                genre3 += 0;
            }
        }
        int genre1Count = Math.round((10 * genre1) / genre1 + genre2 + genre3);
        int genre2Count = Math.round((10 * genre2) / genre1 + genre2 + genre3);
        int genre3Count = Math.round((10 * genre3) / genre1 + genre2 + genre3);
        for(int i = 0; i < bookList.size() && genre1Count != 0; i++){
            if(bookList.get(i).getGenre().equals("genre1") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                genre1Count--;
            }
        }
        for(int i = 0; i < bookList.size() && genre2Count != 0; i++){
            if(bookList.get(i).getGenre().equals("genre2") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                genre2Count--;
            }
        }
        for(int i = 0; i < bookList.size() && genre3Count != 0; i++){
            if(bookList.get(i).getGenre().equals("genre3") && !readBookList.contains(bookList.get(i))){
                recommendList.add(bookList.get(i));
                genre3Count--;
            }
        }
        genre1 = 0;
        genre2 = 0;
        genre3 = 0;
        genre1Count = 0;
        genre2Count = 0;
        genre3Count = 0;
        return recommendList;
    }
    @Override
    public ArrayList<Book> filter(){
        ArrayList<Book> filteredBooks = new ArrayList<>();
        for(int j = 0; j < genres.size(); j++){
            for(int i = 0; i < bookList.size(); i++){   
                if(bookList.get(i).getGenre.equals(genres.get(j))){
                    filteredBooks.add(bookList.get(i));
                }
            }
        }
        return filteredBooks;
    }
}
