package com.example;

import java.util.ArrayList;
//Arda
public interface Filter {
    /*Bu inteface'i kullanacak classlar: Search ve PersonelRecommendations
     * Bu interface'i kullanan classların erişmesi gereken muhtemel datalar, Books ve GroupChats
     */
    public ArrayList<Book> filter(ArrayList<String> genreList, ArrayList<String> authorList, double minRating, double maxRating);
    /* Implementation: 
ArrayList<Book> bookList --> Books registered in our database
ArrayList<String> genres --> Filtered genres provided by user
     * ArrayList<Book> filteredBooks = new ArrayList<>();
     * for(int j = 0; j < genres.size(); j++){
     *      for(int i = 0; i < bookList.size(); i++){   
     *          if(bookList.get(i).getGenre.equals(genres.get(j))){
     *              filteredBooks.add(bookList.get(i));
     *          }
     *      }
     * }
     * Comment: Bazı durumlarda ek olarak groupChatlerin de incelenmesi gerekebilir mesela Search classında. Mantıklı gelen
     * groupChatlerin book'larla bağlantılı olması ve sadece book'u filtreleyip groupChati de onla birlikte search resultta göstermek.
     * Mesela filtrelerim sonucu Hayvan Çiftliği kitabına ulaştıysam hemen altında onun group chati de yer almalı.
     * ArrayList<Book> filteredBooks = new ArrayList<>();
     *      for(int i = 0; i < bookList.size(); i++){   
     *          if(bookList.get(i).getRating() > start && bookList.get(i).getRating() < end){
     *              filteredBooks.add(bookList.get(i));
     *          }
     *      }
     * }
     */
}
