package com.ooodlegame.model;

/**
 * Representa el temporizador de una partida.
 * 
 * Su función es controlar el tiempo transcurrido
 * mientras el jugador intenta resolver la ecuación.
 * 
 * El tiempo se almacena en segundos y puede iniciarse,
 * detenerse o reiniciarse según el estado de la partida.
 */
public class Temporizador {

    private int segundos;
    private boolean activo;

    /**
     * Constructor completo.
     * Inicializa el tiempo en cero y detenido.
     */
    public Temporizador() {
        segundos = 0;
        activo = false;
    }

    /**
     * Inicia o reanuda el temporizador.
     */
    public void iniciar() {
        activo = true;
    }

    /**
     * Detiene o pausa el temporizador.
     */
    public void detener() {
        activo = false;
    }

    /**
     * Reinicia el temporizador a cero
     * y lo deja detenido.
     */
    public void reiniciar() {
        segundos = 0;
        activo = false;
    }

    /**
     * Obtiene el tiempo transcurrido en segundos.
     * 
     * @return cantidad de segundos acumulados
     */
    public int obtenerTiempo() {
        return segundos;
    }

    /**
     * Incrementa el tiempo en un segundo
     * solamente si el temporizador está activo.
     */
    public void incrementarSegundo() {
        if (activo) {
            segundos++;
        }
    }

    /**
     * Indica si el temporizador se encuentra activo.
     * 
     * @return true si está corriendo,
     *         false si está detenido
     */
    public boolean isActivo() {
        return activo;
    }
}