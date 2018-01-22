package kstopa.server;

import kstopa.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {

    private final Socket socket;
    private final ObjectInputStream reader;
    private final ObjectOutputStream writer;


    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        reader = new ObjectInputStream(socket.getInputStream());
        writer = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            Server.writers.add(writer);
            while (!socket.isClosed()) {
                Message message = (Message) reader.readObject();
                if (message != null) {
                    System.out.println(message);
                    for (ObjectOutputStream w : Server.writers) {
                        w.writeObject(message);
                    }
                }
            }
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            Server.writers.remove(writer);
        }

    }
}

