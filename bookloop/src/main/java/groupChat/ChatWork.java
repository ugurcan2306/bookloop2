package groupChat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatWork {

    private String chatId;
    private String username;

    public ChatWork(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
        initializeWindow();
    }

    private void initializeWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatWindow.fxml"));
            VBox chatLayout = loader.load();

            chatWindowController controller = loader.getController();
            controller.initializeChat(chatId, username);

            Stage chatStage = new Stage();
            chatStage.setTitle("Chat - " + username);
            chatStage.setScene(new Scene(chatLayout));
            chatStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}