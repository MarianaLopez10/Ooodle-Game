package com.ooodlegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Font nunito = Font.loadFont(
            "https://fonts.gstatic.com/s/nunito/v8/ySZTeT3IuzJj0GK6uGpbBg.ttf",
            20
        );

        Label texto = new Label("Fuente Nunito en JavaFX");
        Button button = new Button(" Test Button");
        VBox box = new VBox(15, texto, button);
        box.setAlignment(Pos.CENTER);
        texto.setFont(nunito);
        button.setFont(nunito);
        Scene scene = new Scene(box, 400, 200);

        stage.setScene(scene);
        stage.setTitle("Nunito Font");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}