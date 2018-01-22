package kstopa.client;

import kstopa.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket = null;
    private int portNumber = 8080;
    private String name = null;
    private ObjectInputStream reader = null;
    private ObjectOutputStream writer = null;

    public void connectToServer() {
        try {
            socket = new Socket("localhost", portNumber);
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Could not connect to server");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void sendMessage(Message message) throws IOException {
        writer.writeObject(message);
    }

    public Message readMessage() throws IOException, ClassNotFoundException {
        return (Message) reader.readObject();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
