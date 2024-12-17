package com.example;

import java.util.ArrayList;
//Arda
public interface Sort {
    /*Bu inteface'i kullanacak classlar: Search ve PersonelRecommendations
     * Bu interface'i kullanan classların erişmesi gereken muhtemel datalar, Books ve GroupChats
    //  */
    // public ArrayList<Book> sortByAscendingName();
    // public ArrayList<Book> sortByDescendingName();
    // public ArrayList<Book> sortByAscendingRating();
    // public ArrayList<Book> sortByDescendingRating();

    /* Implementation:
ArrayList<Book> bookList --> Books registered in our database
     * ArrayList<Book> sortedList = bookList;
     * Book temp;
     * boolean changed = true;
     * for(int i = 0; i < sortedList.size() && changed; i++){
     *      changed = false;
     *      for(int j = 0; j < sortedList.size() - i; j++){
     *          if(sortedList.get(j).getName.compareToIgnoreCase(sortedList.get(j + 1)) < 0){
     *              changed = true;
     *              temp = sortedList.get(j);
     *              sortedList.set(j, sortedList.get(j + 1));
     *              sortedList.set(j + 1, temp);
     *          }
     *      }
     * }
     */
}
