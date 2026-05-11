package com.ooodlegame.controller;

import java.util.List;

import com.ooodlegame.model.Ecuacion;
import com.ooodlegame.services.EcuacionDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class EcuacionController {

    @FXML
    private TextField txtNum1;

    @FXML
    private TextField txtNum2;

    @FXML
    private TextField txtNum3;

    @FXML
    private TextField txtNum4;

    @FXML
    private TextField txtRango;

    @FXML
    private void guardarEcuacion() {

        try {

            int n1 = Integer.parseInt(txtNum1.getText());
            int n2 = Integer.parseInt(txtNum2.getText());
            int n3 = Integer.parseInt(txtNum3.getText());
            int n4 = Integer.parseInt(txtNum4.getText());

            int rango = Integer.parseInt(txtRango.getText());

            Ecuacion ecuacion = new Ecuacion(
                    List.of(n1, n2, n3, n4),
                    rango);

            EcuacionDAO dao = new EcuacionDAO();

            dao.guardarEcuacion(ecuacion);

            mostrarAlerta(
                    "Éxito",
                    "La ecuación fue guardada correctamente.");

            limpiarCampos();

        } catch (Exception e) {

            mostrarAlerta(
                    "Error",
                    e.getMessage());
        }
    }

    private void limpiarCampos() {

        txtNum1.clear();
        txtNum2.clear();
        txtNum3.clear();
        txtNum4.clear();
        txtRango.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}