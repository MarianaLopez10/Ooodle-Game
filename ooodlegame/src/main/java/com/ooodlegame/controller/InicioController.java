package com.ooodlegame.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controlador de la pantalla de inicio del juego Ooodle.
 *
 * Esta clase se encarga de gestionar la selección
 * del rango numérico de la partida y de iniciar
 * la transición hacia la ventana principal del juego.
 * 
 * Además, administra el estilo visual de los botones
 * de selección para indicar cuál rango se encuentra activo.
 */
public class InicioController {

    /**
     * Botón para seleccionar el rango del 1 al 9.
     */
    @FXML
    private Button btn1a9;

    /**
     * Botón para seleccionar el rango del 1 al 12.
     */
    @FXML
    private Button btn1a12;

    /**
     * Botón encargado de iniciar la partida
     */
    @FXML
    private Button btnIniciar;

    /**
     * Valor máximo permitido para los números
     * de la ecuación durante la partida.
     */
    private int rangoMax;

    /**
     * Inicializa la pantalla de inicio.
     *
     * Establece por defecto el rango del 1 al 9
     * y aplica el estilo visual correspondiente
     * al botón seleccionado.
     */
    @FXML
    public void initialize() {
        rangoMax = 9;
        seleccionarBoton(btn1a9);
    }

    /**
     * Define el rango numérico seleccionado por el usuario.
     *
     * Actualiza el valor máximo permitido y cambia
     * visualmente el botón activo.
     *
     * @param event evento generado al presionar
     *              un botón de selección
     */
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

    /**
     * Aplica el estilo visual al botón seleccionado.
     *
     * Elimina el estilo de selección de todos los botones
     * y posteriormente lo asigna al botón activo.
     *
     * @param botonActivo botón actualmente seleccionado
     */
    private void seleccionarBoton(Button botonActivo) {

        btn1a9.getStyleClass().remove("boton-rango-seleccionado");
        btn1a12.getStyleClass().remove("boton-rango-seleccionado");

        botonActivo.getStyleClass().add("boton-rango-seleccionado");
    }

    /**
     * Inicia una nueva partida del juego.
     *
     * Carga la vista juego.fxml, obtiene el controlador
     * de la pantalla de juego e inicia la partida
     * utilizando el rango seleccionado por el usuario.
     */
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