package com.ooodlegame.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Representa una ecuación matemática del juego Ooodle.
 * La estructura de la ecuación es: num1 × num2 + num3 - num4
 * 
 * Además almacena el resultado y el rango permitido para validar los números
 * ingresados.
 */
public class Ecuacion {

    private List<Integer> numeros;
    private int resultado;
    private int rango;

    /**
     * Constructor vacío.
     */
    public Ecuacion() {
        this.numeros = new ArrayList<>();
    }

    /**
     * Constructor completo.
     * 
     * @param numeros lista de 4 números de la ecuación
     * @param rango   valor máximo permitido
     */
    public Ecuacion(List<Integer> numeros, int rango) {
        this.numeros = numeros;
        this.rango = rango;
        this.resultado = calcularResultado();
    }

    /**
     * Calcula el resultado de la ecuación:
     * num1 × num2 + num3 - num4
     * 
     * @return resultado calculado
     */
    public int calcularResultado() {
        if (numeros == null || numeros.size() != 4) {
            throw new IllegalStateException("La ecuación debe tener 4 números.");
        }

        return (numeros.get(0) * numeros.get(1)) + numeros.get(2) - numeros.get(3);

    }

    /**
     * Verifica si la ecuación cumple las reglas:
     * - Tener 4 números
     * - Estar dentro del rango
     * - No repetir números
     * - Resultado correcto
     * 
     * @return true si es válida, false en caso contrario
     */
    public boolean validarEcuacion() {

        if (numeros == null || numeros.size() != 4) {
            return false;
        }

        for (Integer n : numeros) {
            if (n < 1 || n > rango) {
                return false;
            }
        }

        Set<Integer> unicos = new HashSet<>(numeros);

        if (unicos.size() != 4) {
            return false;
        }

        return resultado == calcularResultado();
    }

    /**
     * Obtiene los números de la ecuación.
     * 
     * @return lista de números
     */
    public List<Integer> getNumeros() {
        return new ArrayList<>(numeros);
    }

    /**
     * Asigna nuevos números a la ecuación.
     * 
     * @param numeros nueva lista de números
     */
    public void setNumeros(List<Integer> numeros) {
        this.numeros = new ArrayList<>(numeros);
        this.resultado = calcularResultado();
    }

    /**
     * Obtiene el resultado.
     * 
     * @return resultado
     */
    public int getResultado() {
        return resultado;
    }

    /**
     * Asigna el resultado.
     * 
     * @param resultado nuevo resultado
     */
    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    /**
     * Obtiene el rango.
     * 
     * @return rango máximo
     */
    public int getRango() {
        return rango;
    }

    /**
     * Asigna el rango.
     * 
     * @param rango nuevo rango
     */
    public void setRango(int rango) {
        this.rango = rango;
    }
}