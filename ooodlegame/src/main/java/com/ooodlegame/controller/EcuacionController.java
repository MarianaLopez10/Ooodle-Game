package com.ooodlegame.controller;

import java.util.List;

import com.ooodlegame.model.Ecuacion;
import com.ooodlegame.services.EcuacionDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Controlador de la ventana de registro de ecuaciones.
 *
 * Esta clase se encarga de gestionar la interacción
 * entre la interfaz gráfica y la lógica de almacenamiento
 * de ecuaciones en la base de datos.
 *
 * Permite al usuario:
 * <ul>
 *     <li>Ingresar los números de una ecuación</li>
 *     <li>Definir el rango permitido</li>
 *     <li>Guardar ecuaciones válidas en la base de datos</li>
 * </ul>
 */
public class EcuacionController {

    /**
     * Campo de texto para el primer número.
     */
    @FXML
    private TextField txtNum1;

    /**
     * Campo de texto para el segundo número.
     */
    @FXML
    private TextField txtNum2;

    /**
     * Campo de texto para el tercer número.
     */
    @FXML
    private TextField txtNum3;

    /**
     * Campo de texto para el cuarto número.
     */
    @FXML
    private TextField txtNum4;

    /**
     * Campo de texto para el rango de la ecuación.
     */
    @FXML
    private TextField txtRango;

    /**
     * Guarda una nueva ecuación en la base de datos.
     *
     * Obtiene los valores ingresados por el usuario,
     * crea un objeto Ecuacion y utiliza EcuacionDAO
     * para almacenarlo.
     *
     * En caso de éxito se muestra una alerta informativa
     * y se limpian los campos del formulario.
     *
     * Si ocurre algún error, se muestra el mensaje
     * correspondiente al usuario.
     */
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

    /**
     * Limpia todos los campos del formulario.
     *
     * Elimina el contenido ingresado por el usuario
     * después de guardar una ecuación correctamente.
     */
    private void limpiarCampos() {

        txtNum1.clear();
        txtNum2.clear();
        txtNum3.clear();
        txtNum4.clear();
        txtRango.clear();
    }

    /**
     * Muestra una ventana de alerta informativa.
     *
     * @param titulo título de la alerta
     * @param mensaje mensaje mostrado al usuario
     */
    private void mostrarAlerta(String titulo, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}