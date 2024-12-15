//DİLA

package groupChat;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private ServerSocket serverSocket;
public Server(ServerSocket serverSocket){
    this.serverSocket = serverSocket;
}

public void startServer(){
    try{
        while(!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            System.out.println("A new client has connected!");
            ClientHandler clientHandler = new ClientHandler(socket);

            Thread thread = new Thread(clientHandler);
            thread.start();
        }

    }catch(IOException e){

    }

} 

    public void closeServerSocket(){
        try {
            if(serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}


// //DİLA

/*buradan sonrası uı için güncellendi */

// package groupChat;

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.net.ServerSocket;
// import java.net.Socket;
// import java.util.ArrayList;
// import java.util.List;

// public class Server {

//     private static List<ClientHandler> clientHandlers = new ArrayList<>();

//     public static void main(String[] args) {
//         try (ServerSocket serverSocket = new ServerSocket(1234)) {
//             System.out.println("Server is listening on port 1234...");
//             while (true) {
//                 Socket socket = serverSocket.accept();
//                 ClientHandler clientHandler = new ClientHandler(socket);
//                 clientHandlers.add(clientHandler);
//                 new Thread(clientHandler).start();
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public static class ClientHandler implements Runnable {
//         private Socket socket;
//         private BufferedReader bufferedReader;
//         private BufferedWriter bufferedWriter;
//         private String clientUsername;

//         public ClientHandler(Socket socket) {
//             try {
//                 this.socket = socket;
//                 this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                 this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                 this.clientUsername = bufferedReader.readLine(); // Kullanıcı adını oku
//                 broadcastMessage(clientUsername + " has joined the chat!");
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }

//         @Override
//         public void run() {
//             String messageFromClient;
//             while (socket.isConnected()) {
//                 try {
//                     messageFromClient = bufferedReader.readLine();
//                     broadcastMessage(messageFromClient);
//                 } catch (IOException e) {
//                     closeEverything(socket, bufferedReader, bufferedWriter);
//                     break;
//                 }
//             }
//         }

//         public void broadcastMessage(String messageToSend) {
//             for (ClientHandler clientHandler : clientHandlers) {
//                 try {
//                     if (!clientHandler.clientUsername.equals(clientUsername)) {
//                         clientHandler.bufferedWriter.write(messageToSend);
//                         clientHandler.bufferedWriter.newLine();
//                         clientHandler.bufferedWriter.flush();
//                     }
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }

//         public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
//             try {
//                 if (bufferedReader != null) {
//                     bufferedReader.close();
//                 }
//                 if (bufferedWriter != null) {
//                     bufferedWriter.close();
//                 }
//                 if (socket != null) {
//                     socket.close();
//                 }
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }

