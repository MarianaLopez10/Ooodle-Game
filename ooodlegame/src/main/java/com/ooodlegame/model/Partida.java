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
     * Constructor completo.
     * Inicializa los valores por defecto.
     */
    public Partida() {
        intentos = new ArrayList<>();
        maxIntentos = 6;
        finalizada = false;
        ganada = false;
        timer = new Temporizador();
    }

    /**
     * Inicia una nueva partida.
     * 
     * Obtiene una ecuación aleatoria según el rango
     * seleccionado, limpia los intentos previos y
     * reinicia el temporizador.
     * 
     * @param rango valor máximo permitido para los números
     */
    public void iniciarPartida(int rango) {

        EcuacionDAO dao = new EcuacionDAO();

        ecuacionSecreta = dao.obtenerEcuacionAleatoria(rango);

        intentos.clear();
        finalizada = false;
        ganada = false;

        timer.reiniciar();
    }

    /**
     * Registra un nuevo intento realizado por el jugador.
     * 
     * @param intento intento a guardar
     */
    public void registrarIntento(Intento intento) {
        intentos.add(intento);
    }

    /**
     * Verifica si el jugador ganó la partida.
     * 
     * Se considera victoria cuando el último intento
     * ingresado es completamente correcto.
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
     * Ocurre cuando se alcanzó el máximo de intentos
     * y no se logró la victoria.
     * 
     * @return true si perdió, false en caso contrario
     */
    public boolean verificarDerrota() {

        return intentos.size() == maxIntentos
                && !ganada;
    }

    /**
     * Finaliza la partida actual.
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
     * @return lista de intentos
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