package com.ooodlegame.model;

import java.util.ArrayList;
import java.util.List;

import com.ooodlegame.services.EcuacionDAO;

/**
 * Representa una partida del juego Ooodle.
 *
 * Una partida administra la ecuación secreta,
 * los intentos realizados por el jugador,
 * el temporizador y el estado general del juego.
 *
 * También permite verificar victoria o derrota.
 */
public class Partida {

    private Ecuacion ecuacionSecreta;
    private List<Intento> intentos;
    private int maxIntentos;
    private boolean ganada;
    private boolean finalizada;
    private Temporizador timer;

    /**
     * Constructor de la clase Partida.
     *
     * Inicializa la lista de intentos,
     * establece el máximo de intentos en 6
     * y crea el temporizador.
     */
    public Partida() {
        intentos = new ArrayList<>();
        maxIntentos = 6;
        ganada = false;
        finalizada = false;
        timer = new Temporizador();
    }

    /**
     * Inicia una nueva partida.
     *
     * Obtiene una ecuación aleatoria según el rango,
     * reinicia el estado de la partida,
     * limpia los intentos anteriores
     * y reinicia el temporizador.
     *
     * @param rango valor máximo permitido para los números
     * @throws Exception si ocurre error al obtener la ecuación
     */
    public void iniciarPartida(int rango) throws Exception {

        EcuacionDAO dao = new EcuacionDAO();

        ecuacionSecreta = dao.obtenerEcuacionAleatoria(rango);

        intentos.clear();
        ganada = false;
        finalizada = false;

        timer.reiniciar();
    }

    /**
     * Registra un nuevo intento realizado por el jugador.
     *
     * @param intento intento ingresado
     */
    public void registrarIntento(Intento intento) {
        intentos.add(intento);
    }

    /**
     * Verifica si el jugador ganó la partida.
     *
     * Se gana cuando el último intento registrado
     * coincide completamente con la ecuación secreta.
     *
     * @return true si ganó, false en caso contrario
     */
    public boolean verificarVictoria() {

        ganada = !intentos.isEmpty()
                && intentos.get(intentos.size() - 1).esCorrecto();

        return ganada;
    }

    /**
     * Verifica si el jugador perdió la partida.
     *
     * Ocurre cuando se alcanza el número máximo
     * de intentos sin haber ganado.
     *
     * @return true si perdió, false en caso contrario
     */
    public boolean verificarDerrota() {
        return intentos.size() >= maxIntentos
                && !verificarVictoria();
    }

    /**
     * Finaliza la partida actual.
     *
     * Detiene el temporizador
     * y marca la partida como finalizada.
     */
    public void finalizarPartida() {
        timer.detener();
        finalizada = true;
    }

    /**
     * Obtiene la ecuación secreta.
     *
     * @return ecuación objetivo
     */
    public Ecuacion getEcuacionSecreta() {
        return ecuacionSecreta;
    }

    /**
     * Obtiene la lista de intentos realizados.
     *
     * @return copia de la lista de intentos
     */
    public List<Intento> getIntentos() {
        return new ArrayList<>(intentos);
    }

    /**
     * Obtiene el temporizador de la partida.
     *
     * @return temporizador
     */
    public Temporizador getTimer() {
        return timer;
    }

    /**
     * Indica si la partida terminó.
     *
     * @return true si finalizó, false en caso contrario
     */
    public boolean isFinalizada() {
        return finalizada;
    }

    /**
     * Indica si la partida fue ganada.
     *
     * @return true si fue ganada, false en caso contrario
     */
    public boolean isGanada() {
        return ganada;
    }
}