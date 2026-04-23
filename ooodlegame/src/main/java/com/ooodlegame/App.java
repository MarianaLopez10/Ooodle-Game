package com.ooodlegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Font.loadFont(
            getClass().getResourceAsStream(
                "/com/ooodlegame/fonts/Nunito-Regular.ttf"
            ),
            14
        );

        Font.loadFont(
            getClass().getResourceAsStream(
                "/com/ooodlegame/fonts/Nunito-Bold.ttf"
            ),
            14
        );

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/ooodlegame/view/inicio.fxml")
        );

        Scene scene = new Scene(loader.load(), 1440, 835);

        stage.setTitle("Ooodle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}