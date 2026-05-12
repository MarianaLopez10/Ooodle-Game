package com.ooodlegame.controller;

import com.ooodlegame.model.*;
import com.ooodlegame.services.EcuacionDAO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

/**
 * Controlador de la pantalla de juego de Ooodle.
 *
 * Esta clase se encarga de gestionar toda la lógica de interacción
 * entre la interfaz gráfica y el modelo de la partida.
 *
 * Sus principales funciones son:
 * <ul>
 * <li>Inicializar la cuadrícula del juego</li>
 * <li>Gestionar los intentos del jugador</li>
 * <li>Validar ecuaciones ingresadas</li>
 * <li>Mostrar retroalimentación visual mediante colores</li>
 * <li>Controlar el temporizador de la partida</li>
 * <li>Administrar las condiciones de victoria y derrota</li>
 * <li>Gestionar la navegación entre ventanas</li>
 * </ul>
 *
 * Cada fila representa una ecuación matemática de la forma:
 *
 * num1 × num2 + num3 - num4 = resultado
 *
 * Si el resultado ingresado no coincide con el resultado secreto,
 * el operador "=" cambia visualmente a "≠".
 */
public class JuegoController {

    @FXML
    private GridPane panelCuadricula;
    @FXML
    private Button botonVolver;

    /**
     * Campos de entrada
     */
    @FXML
    private TextField r0c0, r0c1, r0c2, r0c3;
    @FXML
    private TextField r1c0, r1c1, r1c2, r1c3;
    @FXML
    private TextField r2c0, r2c1, r2c2, r2c3;
    @FXML
    private TextField r3c0, r3c1, r3c2, r3c3;
    @FXML
    private TextField r4c0, r4c1, r4c2, r4c3;
    @FXML
    private TextField r5c0, r5c1, r5c2, r5c3;

    /**
     * Etiquetas de resultado
     */
    @FXML
    private Label r0resultado;
    @FXML
    private Label r1resultado;
    @FXML
    private Label r2resultado;
    @FXML
    private Label r3resultado;
    @FXML
    private Label r4resultado;
    @FXML
    private Label r5resultado;

    /**
     * Etiquetas del operador "=" de cada fila (fx:id="r0igual" … "r5igual")
     */
    @FXML
    private Label r0igual;
    @FXML
    private Label r1igual;
    @FXML
    private Label r2igual;
    @FXML
    private Label r3igual;
    @FXML
    private Label r4igual;
    @FXML
    private Label r5igual;

    private Partida partida;
    private int filaActual = 0;
    private Timeline timeline;

    private TextField[][] campos;
    private Label[] etiquetasResultado;
    private Label[] etiquetasIgual;

    /**
     * Inicializa los componentes de la interfaz gráfica.
     *
     * Configura la matriz de campos de texto, etiquetas de resultado,
     * listeners de teclado y bloquea las filas del tablero antes
     * de iniciar la partida.
     */
    @FXML
    public void initialize() {

        botonVolver.toFront();

        campos = new TextField[][] {
                { r0c0, r0c1, r0c2, r0c3 },
                { r1c0, r1c1, r1c2, r1c3 },
                { r2c0, r2c1, r2c2, r2c3 },
                { r3c0, r3c1, r3c2, r3c3 },
                { r4c0, r4c1, r4c2, r4c3 },
                { r5c0, r5c1, r5c2, r5c3 }
        };

        etiquetasResultado = new Label[] {
                r0resultado, r1resultado, r2resultado,
                r3resultado, r4resultado, r5resultado
        };

        etiquetasIgual = new Label[] {
                r0igual, r1igual, r2igual,
                r3igual, r4igual, r5igual
        };

        configurarListenersTeclado();

        // Quitar campo-activo que el FXML hardcodea en la fila 0
        for (int f = 0; f < 6; f++) {
            for (TextField tf : campos[f]) {
                tf.getStyleClass().remove("campo-activo");
            }
        }

        // Bloquear todo; iniciarPartida() activa la fila 0
        bloquearFilas(0, 5);
    }

    /**
     * Inicia una nueva partida del juego.
     *
     * Obtiene una ecuación aleatoria desde la base de datos
     * según el rango seleccionado y prepara la interfaz
     * para comenzar los intentos.
     *
     * @param rango valor máximo permitido para los números
     *              de la ecuación (por ejemplo 9 o 12)
     */
    public void iniciarPartida(int rango) {
        partida = new Partida();

        try {
            EcuacionDAO dao = new EcuacionDAO();
            Ecuacion ecuacion = dao.obtenerEcuacionAleatoria(rango);

            partida.iniciarPartida(ecuacion);
        } catch (Exception e) {
            mostrarAlerta("Error al iniciar",
                    "No se pudo obtener una ecuación: " + e.getMessage());
            return;
        }

        int resultadoSecreto = partida.getEcuacionSecreta().getResultado();
        r0resultado.setText(String.valueOf(resultadoSecreto));
        r0resultado.getStyleClass().add("celda-resultado-visible");

        activarFila(0);
        iniciarTemporizadorUI();
    }

    /**
     * Inicia el temporizador visual de la partida.
     *
     * Ejecuta un Timeline que incrementa el contador
     * de tiempo cada segundo mientras la partida
     * no haya finalizado.
     */
    private void iniciarTemporizadorUI() {
        partida.getTimer().iniciar();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (!partida.isFinalizada()) {
                partida.getTimer().incrementarSegundo();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Detiene el temporizador visual de la partida.
     *
     * Finaliza la ejecución del Timeline encargado
     * de actualizar el contador de tiempo del juego.
     */
    private void detenerTemporizadorUI() {
        if (timeline != null)
            timeline.stop();
    }

    /**
     * Valida la ecuación ingresada por el jugador.
     *
     * Verifica:
     * <ul>
     * <li>Que todos los campos estén completos</li>
     * <li>Que los números estén dentro del rango permitido</li>
     * <li>Que no existan números repetidos</li>
     * <li>Que el resultado matemático sea correcto</li>
     * </ul>
     *
     * Posteriormente registra el intento, pinta los colores
     * correspondientes y verifica si el jugador ganó o perdió.
     */
    @FXML
    private void validarEcuacion() {
        if (partida == null || partida.isFinalizada())
            return;

        // 1. Campos completos
        List<Integer> numeros = leerFila(filaActual);
        if (numeros == null) {
            mostrarAlerta("Campos incompletos",
                    "Completa todos los números antes de validar.");
            return;
        }

        // 2. Rango
        int rango = partida.getEcuacionSecreta().getRango();
        for (int n : numeros) {
            if (n < 1 || n > rango) {
                mostrarAlerta("Número fuera de rango",
                        "Usa números dentro del rango (1 – " + rango + ").");
                return;
            }
        }

        // 3. Sin repetidos
        Set<Integer> unicos = new HashSet<>(numeros);
        if (unicos.size() != numeros.size()) {
            mostrarAlerta("Dígitos repetidos",
                    "¡Solo puedes usar cada dígito una vez!");
            return;
        }

        // 4. Calcular resultado del intento y actualizar operador "=" / "≠"
        int resultadoSecreto = partida.getEcuacionSecreta().getResultado();
        int resultadoIngresado = (numeros.get(0) * numeros.get(1))
                + numeros.get(2)
                - numeros.get(3);

        if (resultadoIngresado != resultadoSecreto) {
            etiquetasIgual[filaActual].setText("≠");
        }
        // Si coincide, el "=" del FXML ya está correcto — no se toca

        // 5. Comparar con la solución y registrar intento
        Intento intento = new Intento(numeros);
        intento.compararConSolucion(partida.getEcuacionSecreta());
        partida.registrarIntento(intento);

        // 6. Pintar colores
        pintarFila(filaActual, intento.getEstados());

        // 7. Bloquear fila validada
        bloquearFila(filaActual);

        // 8. Victoria
        if (partida.verificarVictoria()) {
            partida.finalizarPartida();
            detenerTemporizadorUI();
            mostrarAlerta("¡Ganaste!",
                    "Resolviste la ecuación en " + partida.getIntentos().size()
                            + " intento(s) y " + partida.getTimer().obtenerTiempo() + " segundos.");
            return;
        }

        // 9. Derrota
        if (partida.verificarDerrota()) {
            partida.finalizarPartida();
            detenerTemporizadorUI();
            mostrarAlerta("¡Perdiste!",
                    "La ecuación secreta era: "
                            + partida.getEcuacionSecreta().getNumeros().toString());
            return;
        }

        // 10. Avanzar a la siguiente fila
        filaActual++;
        activarFila(filaActual);

        etiquetasResultado[filaActual].setText(String.valueOf(resultadoSecreto));
        etiquetasResultado[filaActual].getStyleClass().add("celda-resultado-visible");
    }

    /**
     * Lee los números ingresados en una fila específica.
     *
     * @param fila índice de la fila a leer
     * @return lista de números ingresados o null
     *         si existen campos vacíos o inválidos
     */
    private List<Integer> leerFila(int fila) {
        List<Integer> numeros = new ArrayList<>();
        for (int col = 0; col < 4; col++) {
            String texto = campos[fila][col].getText().trim();
            if (texto.isEmpty())
                return null;
            try {
                numeros.add(Integer.parseInt(texto));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return numeros;
    }

    /**
     * Aplica los colores de retroalimentación a una fila.
     *
     * Dependiendo del estado de cada celda se asigna
     * una clase CSS correspondiente.
     *
     * @param fila    fila que será coloreada
     * @param estados lista de estados obtenidos
     *                tras comparar el intento
     */
    private void pintarFila(int fila, List<EstadoCelda> estados) {
        for (int col = 0; col < 4; col++) {
            TextField campo = campos[fila][col];
            campo.getStyleClass().removeAll(
                    "celda-correcta", "celda-presente", "celda-ausente");
            switch (estados.get(col)) {
                case VERDE -> campo.getStyleClass().add("celda-correcta");
                case AMARILLO -> campo.getStyleClass().add("celda-presente");
                case GRIS -> campo.getStyleClass().add("celda-ausente");
            }
        }
    }

    /**
     * Bloquea un conjunto de filas del tablero.
     *
     * Deshabilita la edición y navegación de todas
     * las celdas comprendidas entre las filas indicadas.
     *
     * @param desde fila inicial a bloquear
     * @param hasta fila final a bloquear
     */
    private void bloquearFilas(int desde, int hasta) {
        for (int f = desde; f <= hasta; f++)
            bloquearFila(f);
    }

    /**
     * Bloquea una fila específica del tablero.
     *
     * Los campos de texto dejan de ser editables
     * y no pueden recibir foco del teclado.
     *
     * @param fila índice de la fila a bloquear
     */
    private void bloquearFila(int fila) {
        for (TextField campo : campos[fila]) {
            campo.setEditable(false);
            campo.setFocusTraversable(false);
        }
    }

    /**
     * Activa una fila específica del tablero.
     *
     * Habilita la edición de los campos de texto,
     * permite el enfoque mediante teclado y aplica
     * el estilo visual de fila activa.
     *
     * @param fila índice de la fila a activar
     */
    private void activarFila(int fila) {
        for (TextField campo : campos[fila]) {
            campo.setEditable(true);
            campo.setFocusTraversable(true);
            campo.getStyleClass().add("campo-activo");
        }
        campos[fila][0].requestFocus();
    }

    /**
     * Configura los listeners y validaciones de teclado.
     *
     * Limita la entrada de cada campo a máximo dos dígitos
     * numéricos y mueve automáticamente el foco al siguiente
     * campo cuando el usuario ingresa un valor.
     */
    private void configurarListenersTeclado() {
        for (int fila = 0; fila < 6; fila++) {
            for (int col = 0; col < 4; col++) {
                final int f = fila;
                final int c = col;

                campos[f][c].setTextFormatter(
                        new javafx.scene.control.TextFormatter<>(change -> {
                            String nuevo = change.getControlNewText();
                            return nuevo.matches("\\d{0,2}") ? change : null;
                        }));

                campos[f][c].textProperty().addListener((obs, anterior, actual) -> {
                    if (!actual.isEmpty() && c < 3) {
                        campos[f][c + 1].requestFocus();
                    }
                });
            }
        }
    }

    /**
     * Abre la ventana para ingresar nuevas ecuaciones.
     *
     * Carga la vista ecuacion.fxml y crea una nueva ventana
     * independiente para el registro de ecuaciones.
     */

    @FXML
    private void abrirVentanaEcuacion() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/ooodlegame/view/ecuacion.fxml"));

            Parent root = loader.load();

            Stage ventana = new Stage();

            ventana.setTitle("Ingresar ecuación");

            ventana.setScene(new Scene(root));

            ventana.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Regresa a la pantalla de inicio.
     *
     * Detiene el temporizador de la partida actual
     * y carga nuevamente la ventana principal.
     */
    @FXML
    private void volver() {
        detenerTemporizadorUI();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/ooodlegame/view/inicio.fxml"));
            Parent root = loader.load();
            Stage escenario = (Stage) botonVolver.getScene().getWindow();
            escenario.setScene(new Scene(root));
            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra una ventana de alerta informativa.
     *
     * @param titulo  título de la alerta
     * @param mensaje mensaje mostrado al usuario
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}