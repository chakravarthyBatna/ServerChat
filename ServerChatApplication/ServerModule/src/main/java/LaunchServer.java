import java.io.*;
import java.net.*;

public class LaunchServer {

    private  Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String clientName;

    public LaunchServer() {}
    public LaunchServer(Socket socket)  //Server.java calling this constructor
    {
        try{

            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException i) { i.printStackTrace(); }
    }

    void executeServer() {

        try {

            String clientName;
            clientName = reader.readLine();

            System.out.println(clientName + " : has joined the chat");

            String msgFromClient = "";

            while (!msgFromClient.equals("bye")) {

                msgFromClient = reader.readLine();

                System.out.println(clientName + " : " + msgFromClient);

                sendToClient(msgFromClient);
            }

            System.out.println(clientName + " has left the chat");

        } catch (Exception e) { e.printStackTrace(); }
    }

    void sendToClient(String msgFromClient) {

        try {
            writer.write("server : " + msgFromClient + "\n");
            writer.flush();
        } catch (Exception e) { e.printStackTrace(); }
    }



    public static void main(String[] args)
    {
//        LaunchServer launchServer = new LaunchServer();
//        launchServer.executeServer();
//
//        Thread thread = new Thread(launchServer::executeServer);
//        thread.start();
    }
}
