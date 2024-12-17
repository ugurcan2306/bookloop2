package groupChat;

import com.example.SessionManager;

import javafx.application.Application;

public class baska2 {
    public static void main(String[] args) {
    // Simulate login
    SessionManager.setCurrentUser("ugurcan23");
    System.out.println(SessionManager.getCurrentUser());
    Application.launch(Main2.class, args);
}

}