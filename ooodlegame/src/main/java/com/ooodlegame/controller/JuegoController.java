package com.ooodlegame.controller;

import com.ooodlegame.model.Partida;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class JuegoController {

    @FXML
    private Label lblTiempo;

    @FXML
    private Label lblRango;

    private int rango;

    private Partida partida;
    private Timeline timeline;
    private int segundos;

    /**
     * Se ejecuta automáticamente al cargar juego.fxml
     */
    @FXML
    public void initialize() {
        segundos = 0;

        if (lblTiempo != null) {
            lblTiempo.setText("00:00");
        }
    }

    // Recibe el rango desde InicioController
    public void iniciarPartida(int rango) {

        this.rango = rango;

        if (lblRango != null) {
            lblRango.setText("Rango: 1 - " + rango);
        }

        // Crear nueva partida
        partida = new Partida();

        // Iniciar temporizador visual
        iniciarTemporizador();
    }

    // Cronómetro mm:ss
    private void iniciarTemporizador() {

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    segundos++;
                    actualizarTiempo();
                }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Actualiza Label tiempo
    private void actualizarTiempo() {

        int minutos = segundos / 60;
        int seg = segundos % 60;

        lblTiempo.setText(String.format("%02d:%02d", minutos, seg));
    }

    // Detener tiempo si gana o pierde
    public void detenerTemporizador() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}