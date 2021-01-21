import java.net.*;
import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<ClientHandler> clientThreads=new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException{

        ServerSocket serverSocket = new ServerSocket(4444);
        Socket clientSocket;
        ArrayList<User> users = new ArrayList<>();
        Database database = new Database(users);

        while (true){
            Thread clientThread = new ClientHandler(serverSocket.accept(),database);
            clientThreads.add((ClientHandler) clientThread);
            clientThread.start();
        }
    }

}
