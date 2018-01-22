package kstopa.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GetPathBox {

    private String path;


    public GetPathBox() throws IOException {
        path = new java.io.File(".").getCanonicalPath();
    }


    public String display() {


        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Save messages");
        window.setMinWidth(250);

        //Labe
        Label label = new Label("Where do you want to save your messages?");

        //TextField
        TextField field = new TextField();
        field.setText(path);

        //Button
        Button button = new Button("save");
        button.setDefaultButton(true);
        button.setOnAction(e -> {
            path = field.getText();
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, field, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setOnCloseRequest(e -> path = "" );
        window.showAndWait();

        return path;
    }
}
