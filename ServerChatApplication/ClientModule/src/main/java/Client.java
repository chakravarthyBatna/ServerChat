import java.net.*;
import  java.io.*;
import java.util.Scanner;

public class Client {

    private final String ipAddress;
    private final int port;

    public Client(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    void startClient() {

        try {

            Socket socket = new Socket(ipAddress,port);

            System.out.println("Client is running on port :- " + port);

            String clientName = getClientName(socket);

            LaunchClient launchClient = new LaunchClient(socket, clientName);

            Thread thread = new Thread( ()-> launchClient.receiveFromServer());
            thread.start();

            launchClient.sendMsgToServer();

        } catch (Exception e) { e.printStackTrace(); }

    }

    String getClientName(Socket socket) {

        Scanner scan = new Scanner(System.in);
        System.out.print("\n Enter your Name : ");
        String clientName = scan.nextLine();

        sendNameToServer(socket,clientName);

        return clientName;
    }

    void sendNameToServer(Socket socket, String clientName) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(clientName + "\n");
            writer.flush();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {

        String ipAddress = "localhost";
        int port = 5000;

        Client client = new Client(ipAddress,port);
        client.startClient();
    }
}
