package com.example;

import javafx.application.Application;

public class baska {
    public static void main(String[] args) {
    // Simulate login
    SessionManager.setCurrentUser("ugurcan23");
    SessionManager.getCurrentUser().getBookstoTrade();
    System.out.println(FinderFromDatabase.ShowBookList());
}

}
