package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.v1.FirestoreClient;

import javafx.scene.layout.VBox;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket) {
        try {
        this.socket= socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
       

        } catch (IOException e) {
            e.printStackTrace();
           closer(socket, bufferedReader, bufferedWriter);
                   }
               }
           
            public static void closer(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){
            try {
             if (socket!=null) {
                socket.close();
                }
                 if (bufferedReader!=null) {
                     bufferedReader.close();
                 }
                 if (bufferedWriter!=null) {
                     bufferedWriter.close();
                 }
                } catch (Exception e) {
                e.printStackTrace();
                }
             }
             public void sendMessageToServer(String message){
                try {
                    
                    Firestore db= FireStoreHelper.getFirestore();
                    Map<String, Object> messageData = new HashMap<>();
                    messageData.put("sender", "Client");
                    messageData.put("content", message);
                    messageData.put("timestamp", System.currentTimeMillis());

                    CollectionReference messagesRef = db.collection("client messages");
                    ApiFuture<DocumentReference> result = messagesRef.add(messageData);
                   


                    bufferedWriter.write(message);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    closer(socket, bufferedReader, bufferedWriter);
                }
            }
        public void receiveMessageFromServer(VBox vBox){
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        String messageFromClient= bufferedReader.readLine();
                        Controller.addLabel(messageFromClient,vBox);
                    } catch (IOException e) {
                       
                        e.printStackTrace();
                        closer(socket, bufferedReader, bufferedWriter);
                        break;
                    }
                }
            }
            

        }).start();
    }
}
