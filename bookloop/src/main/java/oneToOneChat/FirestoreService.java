package oneToOneChat;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.api.core.ApiFuture;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirestoreService {

    private Firestore db;

    // Yapıcı metod
    public FirestoreService() {
        db = FirestoreClient.getFirestore();  // Firestore bağlantısını başlatıyoruz
    }

    // Yeni bir chat ID'si oluşturur ve iki kullanıcıyı chat'e ekler
    public void createChatIdForTwoUsers(String username1, String username2, ChatIdCallback callback) {
    // Kullanıcı adlarını alfabetik sıraya göre sıralıyoruz
    List<String> users = Arrays.asList(username1, username2);
    Collections.sort(users);  // Alfabetik sıralama
    
    // İsimleri birleştirerek chatId oluşturuyoruz
    String chatId = users.get(0) + "_" + users.get(1);

        // Chat verisini Firestore'a ekle
        Map<String, Object> chatData = new HashMap<>();
        chatData.put("users", Arrays.asList(username1, username2));  // Kullanıcılar listesi
        chatData.put("createdAt", FieldValue.serverTimestamp());  // Chat oluşturulma zamanı

        // Veriyi Firestore'a yazma
        ApiFuture<WriteResult> future = db.collection("chats")
                                          .document(chatId)
                                          .set(chatData);

        // Write işlemi tamamlandıktan sonra callback'i çağır
        try {
            future.get();  // Bu, Firestore'a yazma işlemi tamamlanana kadar bekler
            callback.onChatCreated(chatId);  // Chat oluşturulmuşsa callback ile chatId'yi döndür
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcıyı chat'e ekler
    public void addUserToChat(String chatId, String username) {
        // Firestore'dan chat belgesini alıyoruz
        ApiFuture<DocumentSnapshot> future = db.collection("chats").document(chatId).get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                List<String> users = (List<String>) document.get("users");
                if (users != null && !users.contains(username)) {
                    users.add(username);  // Kullanıcıyı listeye ekle
                    ApiFuture<WriteResult> updateFuture = db.collection("chats")
                                                             .document(chatId)
                                                             .update("users", users);
                    updateFuture.get();  // Veriyi güncellemek için bekle
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Gerçek zamanlı olarak mesajları dinler
    public void getMessagesForChatRealTime(String chatId, TextArea messageArea) {
        // Firestore'dan mesajları gerçek zamanlı dinlemek için
        db.collection("chats").document(chatId)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener((querySnapshot, e) -> {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
    
                // Mesajları ekrana yazdır
                messageArea.clear(); // Önce mevcut mesajları temizle
                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                    String username = document.getString("sender");
                    String message = document.getString("message");
                    messageArea.appendText(username + ": " + message + "\n");
                }
            });
    }
    

    // Yeni bir mesajı chat'e ekler
    public void addMessageToChat(String chatId, String sender, String message) {
        Map<String, Object> messageData = new HashMap<>();
        messageData.put("sender", sender);  // Mesajı gönderen kişi
        messageData.put("message", message);  // Mesaj içeriği
        messageData.put("timestamp", FieldValue.serverTimestamp());  // Mesajın zaman damgası

        // Mesajı Firestore'a ekle
        ApiFuture<DocumentReference> future = db.collection("chats")
                                                 .document(chatId)
                                                 .collection("messages")
                                                 .add(messageData);

        try {
            future.get();  // Veriyi eklemek için bekle
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Firestore'dan kullanıcı sayısını alır (chat'deki kullanıcı sayısı)
    public void checkUserCount(String chatId, UserCountCallback callback) {
        ApiFuture<DocumentSnapshot> future = db.collection("chats")
                                               .document(chatId)
                                               .get();

        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                List<String> users = (List<String>) document.get("users");
                int userCount = (users != null) ? users.size() : 0;
                callback.onUserCountChecked(userCount);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Chat oluşturulunca callback ile bilgilendirilme
    public interface ChatIdCallback {
        void onChatCreated(String chatId);
    }

    // Kullanıcı sayısı alındığında callback ile bilgilendirilme
    public interface UserCountCallback {
        void onUserCountChecked(int userCount);
    }
}
