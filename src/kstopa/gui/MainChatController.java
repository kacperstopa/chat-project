package kstopa.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kstopa.client.Client;
import kstopa.message.Message;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class MainChatController implements Initializable {

    private Client client;

    @FXML
    private TextArea messagesText;

    @FXML
    private TextField insertText;

    @FXML
    private CheckBox showTime;

    public MainChatController() {
        client = new Client();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client.setName(LoginBox.display());
        client.connectToServer();
        readMessages();
    }

    @FXML
    public void sendMessage() {
        if (!insertText.getText().equals("")) {
            try {
                client.sendMessage(new Message(client.getName(), insertText.getText()));
            } catch (IOException e) {
                System.err.println("Couldn't send message");
                e.printStackTrace();
            }
            insertText.clear();
        }
    }


    private void readMessages() {
        Thread thread = new Thread(() -> {
            while (!client.getSocket().isClosed()) {
                try {
                    Message message = client.readMessage();
                    if (message != null) {
                        if (showTime.isSelected()) messagesText.appendText(message.getStringWithTime());
                        else messagesText.appendText(message.getString());
                    }
                } catch (IOException e) {
                    System.err.println("There is a problem with connection");
                    e.printStackTrace();
                    System.exit(1);
                } catch (ClassNotFoundException e) {
                    System.err.println("There is a problem with serialization");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        );
        thread.setDaemon(true);
        thread.start();
    }


    public void quit() {
        Platform.exit();
    }

    @FXML
    private void saveMessages() {
        try {
            GetPathBox box = new GetPathBox();
            String path = box.display();
            if(!path.equals("")) {
                if (!path.substring(path.length() - 1).equals(File.separator)) path += File.separator;
                PrintWriter writer = new PrintWriter(path + "messages.txt");
                System.out.println(path + "messages.txt");
                writer.print(messagesText.getText());
                writer.close();
                messagesText.appendText("Messages are saved in " + path + "\n");
            }
        } catch (IOException e) {
            System.err.println("Couldn't save messages");
            e.printStackTrace();
        }
    }


    public void showAbout() {
        AboutBox.display();
    }
}

