package kstopa.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutBox {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("About");
        window.setMinWidth(250);
        window.setMinHeight(200);
        Label label = new Label("This chat application was written \nas OOP class assignment by Kacper Stopa");

        VBox layout = new VBox(10);
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
