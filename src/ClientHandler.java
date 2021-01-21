import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{
    private static Socket clientSocket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    private static ObjectInput objectInput;
    private static ObjectOutput objectOutput;
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


            System.out.println("connected");
            outputStream.writeUTF("check!");
            command = inputStream.readUTF();
            while (!command.equals("end") && running){
                if (command.equals("register")){

                    String username = inputStream.readUTF();
                    if (database.has_username(username)) {
                        outputStream.writeUTF("exist");
                    }else{
                        outputStream.writeUTF("OK");
                    }


                    String password = inputStream.readUTF();
                    if (password.length()<5) {
                        outputStream.writeUTF("less_than_five_letters");
                    }else {
                        outputStream.writeUTF("OK");
                    }


                    String confirmPassword = inputStream.readUTF();
                    if (!password.equals(confirmPassword)) {
                        outputStream.writeUTF("not_match");
                    }else {
                        outputStream.writeUTF("OK");
                    }


                   if (!database.has_username(username) && password.length()>5 && password.equals(confirmPassword)){
                        database.users.add(new User(username , password));
                        current_user = database.find(username);
                        outputStream.writeUTF("Done");
                    }

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
                else if(command.equals("changeUsername")){

                    String lastUsername = inputStream.readUTF();
                    String newUsername = inputStream.readUTF();
                    User user = database.find(lastUsername);
                    user.setUsername(newUsername);

                }else if(command.equals("changePassword")){

                    String lastPassword = inputStream.readUTF();
                    String newPassword = inputStream.readUTF();
                    User user = database.find(lastPassword);
                    user.setUsername(newPassword);
                }
                else if (command.equals("gamesInformation")){

                    String username = inputStream.readUTF();
                    User user = database.find(username);

                }else if (command.equals("getScore")){
                    //todo
                }
                else if (command.equals("getGamesInformatoin")){
                    //todo
                }
                command = inputStream.readUTF();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
   }
}
