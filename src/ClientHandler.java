import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{
    private static Socket clientSocket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    private Database database;
    private User current_user=null;
    boolean running=true;

    public ClientHandler(Socket clientSocket , Database database){
        this.clientSocket = clientSocket;
        this.database = database;
    }

   public void run(){
        String command;
        try {
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            command = inputStream.readUTF();
            while (!command.equals("end") && running){
                if (command.equals("register")){
                    String username = inputStream.readUTF();
                    String password = inputStream.readUTF();

                    if (database.has_username(username)){
                        outputStream.writeUTF("exist");
                    }else{
                        database.users.add(new User(username , password));
                        current_user = database.find(username);
                        outputStream.writeUTF("OK");
                    }
                    outputStream.flush();
                }
                else if (command.equals("login")){
                    String username = inputStream.readUTF();
                    if(!database.has_username(username)){
                        outputStream.writeUTF("not_exist");
                    }else {
                        current_user = database.find(username);
                        outputStream.writeUTF("Done");
                    }
                    outputStream.flush();
                }
                command = inputStream.readUTF();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
   }
}