package oneToOneChat;

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

            // get() ile senkronize alıyoruz
            DocumentReference documentReference = future.get();
            System.out.println("Message added with ID: " + documentReference.getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Mesajları almak için metod (gerçek zamanlı dinleme)
    public void getMessagesForChatRealTime(String chatId, TextArea messageArea) {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("chats")
                    .document(chatId)
                    .collection("messages")
                    .orderBy("timestamp", Query.Direction.ASCENDING)
                    .get();

            QuerySnapshot querySnapshot = future.get();  // Veriyi senkronize alıyoruz
            Platform.runLater(() -> {
                messageArea.clear(); // Eski mesajları temizle
                for (QueryDocumentSnapshot document : querySnapshot) {
                    messageArea.appendText(document.getString("username") + ": " + document.getString("content") + "\n");
                }
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcı sayısını kontrol et
    public void checkUserCount(String chatId, UserCountCallback callback) {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("chats")
                    .document(chatId)
                    .collection("users")
                    .get();

            QuerySnapshot querySnapshot = future.get();  // Veriyi senkronize alıyoruz
            int userCount = querySnapshot.size();
            callback.onUserCountChecked(userCount);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            callback.onUserCountChecked(0);
        }
    }

    // Kullanıcıyı Firestore'a kaydetme
    public void addUserToChat(String chatId, String username) {
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
    
        // get() metodunu kullanarak senkronize işlem yapıyoruz
        try {
            // set() metodunu kullanarak, belirli bir belgeye veri ekliyoruz
            ApiFuture<WriteResult> future = db.collection("chats")
                    .document(chatId)
                    .collection("users")
                    .document(username)  // Belirli bir kullanıcı adıyla belge oluşturuyoruz
                    .set(user);  // set() metodunu kullanıyoruz
    
            WriteResult result = future.get();  // Asenkron işlemi senkron hale getiriyoruz
            System.out.println("User added at: " + result.getUpdateTime());  // WriteResult üzerinden işlem zamanı alıyoruz
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    

    public interface UserCountCallback {
        void onUserCountChecked(int count);
    }
}
