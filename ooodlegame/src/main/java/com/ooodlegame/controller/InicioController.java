package com.ooodlegame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private Button btnIniciar;

    // Valor por defecto
    private int rangoMax = 9;

    @FXML
    private void definirRango(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String texto = btn.getText();

        if (texto.equals("1 - 9")) {
            rangoMax = 9;
        } else if (texto.equals("1 - 12")) {
            rangoMax = 12;
        }
    }

    @FXML
    private void iniciarJuego() throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/ooodlegame/view/juego.fxml"));

        Parent root = loader.load();

        JuegoController controller = loader.getController();
        controller.iniciarPartida(rangoMax);

        Stage stage = (Stage) btnIniciar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}