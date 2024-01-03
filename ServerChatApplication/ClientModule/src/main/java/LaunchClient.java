import  java.io.*;
import java.net.*;
import java.util.Scanner;

public class LaunchClient {

    private  Socket socket;
    private  String clientName;
    private BufferedWriter writer;
    private BufferedReader reader;

    public LaunchClient() {}
    public LaunchClient(Socket socket, String clientName) {
        this.socket = socket;
        this.clientName = clientName;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) { e.printStackTrace(); }

    }

    void receiveFromServer () {
            while(true) {
                try {
                    String msgFromServer = reader.readLine();
                    System.out.println(msgFromServer);
                } catch (Exception e) { e.printStackTrace(); }
            }
    }

    void sendMsgToServer() {
        Scanner scan = new Scanner(System.in);
        String msg = "";

        while(!msg.equals("bye")) {
            try {
                msg = scan.nextLine();
                writer.write( msg + "\n");
                writer.flush();
            } catch (Exception e) { e.printStackTrace(); }
        }
        try {
            socket.close();
        } catch (Exception e) { e.printStackTrace(); }

    }
    public static void main(String[] args) {
//        LaunchClient launchClient = new LaunchClient();
//
//        Thread thread = new Thread( ()-> launchClient.receiveFromServer());
//        thread.start();
//
//        launchClient.sendMsgToServer();
    }
}
