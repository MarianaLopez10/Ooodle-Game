package com.ooodlegame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un intento realizado por el jugador
 * durante una partida.
 * 
 * Un intento contiene los números ingresados por el usuario
 * y el estado de cada celda luego de compararlo con la
 * ecuación secreta.
 */
public class Intento {

    private List<Integer> numeros;
    private List<EstadoCelda> estados;

    /**
     * Constructor completo.
     * 
     * @param numeros números ingresados por el jugador
     */
    public Intento(List<Integer> numeros) {
        this.numeros = numeros;
        this.estados = new ArrayList<>();
    }

    /**
     * Compara los números ingresados con la ecuación solución
     * y asigna un estado a cada posición:
     * 
     * VERDE: número correcto en posición correcta.
     * AMARILLO: número existente en otra posición.
     * GRIS: número no existente en la solución.
     * 
     * @param solucion ecuación secreta de la partida
     */
    public void compararConSolucion(Ecuacion solucion) {

        estados.clear();

        List<Integer> numerosSolucion = solucion.getNumeros();

        for (int i = 0; i < 4; i++) {

            int numeroJugador = numeros.get(i);
            int numeroCorrecto = numerosSolucion.get(i);

            if (numeroJugador == numeroCorrecto) {
                estados.add(EstadoCelda.VERDE);

            } else if (numerosSolucion.contains(numeroJugador)) {
                estados.add(EstadoCelda.AMARILLO);

            } else {
                estados.add(EstadoCelda.GRIS);
            }
        }
    }

    /**
     * Verifica si el intento es completamente correcto.
     * 
     * Un intento correcto ocurre cuando todas las posiciones
     * tienen estado VERDE.
     * 
     * @return true si el jugador acertó toda la ecuación,
     *         false en caso contrario
     */
    public boolean esCorrecto() {

        for (EstadoCelda estado : estados) {
            if (estado != EstadoCelda.VERDE) {
                return false;
            }
        }

        return true;
    }

    /**
     * Obtiene los números ingresados en el intento.
     * 
     * @return lista de números ingresados
     */
    public List<Integer> getNumeros() {
        return numeros;
    }

    /**
     * Obtiene el estado de cada celda del intento.
     * 
     * @return lista de estados
     */
    public List<EstadoCelda> getEstados() {
        return estados;
    }
}