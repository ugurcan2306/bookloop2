package groupChat;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirestoreService {

    private Firestore db;

    public FirestoreService() {
        db = FirestoreClient.getFirestore();  // Firestore bağlantısı kuruyoruz
    }

    // Mesajı Firestore'a eklemek için metod
    public void addMessageToChat(String chatId, String username, String messageContent) {
        Map<String, Object> message = new HashMap<>();
        message.put("username", username);
        message.put("content", messageContent);
        message.put("timestamp", FieldValue.serverTimestamp());  // Sunucu zaman damgası ekliyoruz

        // Firestore koleksiyonuna mesajı ekliyoruz
        try {
            ApiFuture<DocumentReference> future = db.collection("chats")
                    .document(chatId)
                    .collection("messages")
                    .add(message);

            DocumentReference documentReference = future.get();  // get() ile senkronize alıyoruz
            System.out.println("Message added with ID: " + documentReference.getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Mesajları almak için metod (gerçek zamanlı dinleme)
    public void getMessagesForChatRealTime(String chatId, TextArea messageArea) {
        // Firestore'dan mesajları dinliyoruz (real-time listener)
        db.collection("chats")
                .document(chatId)
                .collection("messages")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        System.err.println("Error getting documents: " + e);
                        return;
                    }

                    // Yeni mesajları GUI'ye güvenli bir şekilde ekliyoruz
                    Platform.runLater(() -> {
                        // Mesajlar TextArea'ya ekleniyor
                        messageArea.clear(); // Eski mesajları temizle
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            messageArea.appendText(document.getString("username") + ": " + document.getString("content") + "\n");
                        }
                    });
                });
    }
}
