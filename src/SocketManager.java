import java.util.ArrayList;
import java.net.*;
import java.io.*;
public class SocketManager extends Thread{
    ServerSocket serverSocket;
    Database database;
    boolean running=true;
    ArrayList<ClientHandler> threads=new ArrayList<>();

    public SocketManager(ServerSocket serverSocket, Database database) {
        this.serverSocket = serverSocket;
        this.database = database;
    }

    public void run() {
        while(running){
            try {
                Socket socket = serverSocket.accept();
                Thread client = new ClientHandler(socket, database);
                threads.add((ClientHandler) client);
                client.start();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
