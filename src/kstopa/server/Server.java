package kstopa.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private int portNumber = 8080;
    private ServerSocket serverSocket = null;
    static ArrayList<ObjectOutputStream> writers = new ArrayList<>();

    public void getConnection() {
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is now running...");
            acceptClients();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void acceptClients() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ClientThread(socket)).start();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.getConnection();
    }

}
