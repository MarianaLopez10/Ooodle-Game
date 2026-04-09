package com.ooodlegame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

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
    private void startGame() {
        System.out.println("Iniciando juego con rango 1 - " + rangoMax);
        
        // Aquí luego puedes cambiar de escena y pasar el rango
    }
}