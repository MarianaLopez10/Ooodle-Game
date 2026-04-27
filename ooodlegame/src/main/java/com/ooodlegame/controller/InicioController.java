package com.ooodlegame.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private Button btn1a9;

    @FXML
    private Button btn1a12;

    @FXML
    private Button btnIniciar;

    private int rangoMax;

    @FXML
    public void initialize() {
        rangoMax = 9;
        seleccionarBoton(btn1a9);
    }

    @FXML
    private void definirRango(ActionEvent event) {

        Button botonActivo = (Button) event.getSource();

        if (botonActivo == btn1a9) {
            rangoMax = 9;
        } else {
            rangoMax = 12;
        }

        seleccionarBoton(botonActivo);
    }

    private void seleccionarBoton(Button botonActivo) {

        btn1a9.getStyleClass().remove("boton-rango-seleccionado");
        btn1a12.getStyleClass().remove("boton-rango-seleccionado");

        botonActivo.getStyleClass().add("boton-rango-seleccionado");
    }

    @FXML
    private void iniciarJuego() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/ooodlegame/view/juego.fxml")
            );

            Parent root = loader.load();

            JuegoController controlador = loader.getController();
            controlador.iniciarPartida(rangoMax);

            Stage escenario = (Stage) btnIniciar.getScene().getWindow();
            escenario.setScene(new Scene(root));
            escenario.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}