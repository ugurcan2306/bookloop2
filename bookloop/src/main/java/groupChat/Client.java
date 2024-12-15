//DİLA

package groupChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
    protected Socket socket;
    protected BufferedWriter bufferedWriter;
    protected BufferedReader bufferedReader;
    private String username;

    public Client(Socket socket, String username){
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void sendMessage(){
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();


            Scanner scan = new Scanner(System.in);
            while(socket.isConnected()){
                String messageToSend = scan.nextLine();
                bufferedWriter.write(username + ":" + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

            } 
            scan.close();
        } catch (IOException e) {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }

    }

    public void sendMessage(String messageToSend) {
        try {
            if (messageToSend != null && !messageToSend.isEmpty()) {
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    

    public void listenForMessage(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                String msgFromGroupChat;

                while(socket.isConnected()){
                    try {
                        msgFromGroupChat = bufferedReader.readLine();
                        System.out.println(msgFromGroupChat); // Terminale yazdırma
                    } catch (IOException e) {
                        closeEverything(socket,bufferedReader,bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your username for the group chat");
        String username = scan.nextLine();
        Socket socket  =new Socket("localhost",1234);
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendMessage();
        scan.close();
    }


    public Socket getSocket() {
        return socket;
    }
    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
}


//DİLA

/*buradan sonrası uı için düzenlendi */

// package groupChat;

// import java.io.*;
// import java.net.Socket;
// import javafx.scene.control.TextArea;

// public class Client {

//     private String username;
//     private Socket socket;  // Sunucu ile bağlantı için Socket
//     private BufferedReader bufferedReader;
//     private BufferedWriter bufferedWriter;
//     private TextArea messageArea;

//     // Yapıcı: Kullanıcı adı ve TextArea'yı alır
//     public Client(String username, TextArea messageArea) {
//         this.username = username;
//         this.messageArea = messageArea;
//     }

//     // Sunucuya bağlanma işlemi
//     public void connectToServer() {
//         try {
//             // Sunucuya bağlanmak için socket oluşturuyoruz
//             socket = new Socket("localhost", 1234);  // Sunucu adresi ve portu
//             bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

//             // Kullanıcı adını sunucuya gönderiyoruz
//             bufferedWriter.write(username);
//             bufferedWriter.newLine();
//             bufferedWriter.flush();

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Mesaj gönderme işlemi
//     public void sendMessage(String messageToSend) {
//         try {
//             if (messageToSend != null && !messageToSend.isEmpty()) {
//                 bufferedWriter.write(username + ": " + messageToSend);
//                 bufferedWriter.newLine();
//                 bufferedWriter.flush();
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Mesajları dinleme işlemi
//     public void listenForMessages() {
//         new Thread(new Runnable() {
//             @Override
//             public void run() {
//                 try {
//                     while (true) {
//                         String messageFromServer = bufferedReader.readLine();
//                         if (messageFromServer == null) break;
//                         // Sunucudan gelen mesajı GUI'ye ekle
//                         javafx.application.Platform.runLater(() -> messageArea.appendText(messageFromServer + "\n"));
//                     }
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }).start();  // Yeni iş parçacığı başlatılır
//     }
    
    

// }


