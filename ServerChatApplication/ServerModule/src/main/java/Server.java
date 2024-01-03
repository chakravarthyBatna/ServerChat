import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port;

    public Server(int port)
    {
        this.port = port;
    }

    void startServer()
    {
        try{

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server is running on port :- "+port);

            Socket socket = serverSocket.accept();
            System.out.println("A New client has joined the Chat");

            LaunchServer launchServer = new LaunchServer(socket);
            launchServer.executeServer();

//            Thread thread = new Thread(launchServer::executeServer);
//            thread.start();

        } catch (IOException ie) { ie.printStackTrace(); }
    }

    public static void main(String[] args)
    {
        int port = 5000;
        Server server = new Server(port);
        server.startServer();  //starting the server;
    }
}
