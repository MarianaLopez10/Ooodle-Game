package com.ooodlegame.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ecuacion {

    private List<Integer> numeros;
    private int resultado;
    private int rango;

    // Constructor vacío
    public Ecuacion() {
        this.numeros = new ArrayList<>();
    }

    // Constructor completo
    public Ecuacion(List<Integer> numeros, int rango) {
        this.numeros = numeros;
        this.rango = rango;
        this.resultado = calcularResultado();
    }

    // Calcula num1 × num2 + num3 - num4
    public int calcularResultado() {
        if (numeros == null || numeros.size() != 4) {
            throw new IllegalStateException("La ecuación debe tener 4 números.");
        }

        return (numeros.get(0) * numeros.get(1)) + numeros.get(2) - numeros.get(3);

    }

    // Valida si la ecuación cumple reglas
    public boolean validarEcuacion() {

        // Deben existir 4 números
        if (numeros == null || numeros.size() != 4) {
            return false;
        }

        // Todos dentro del rango
        for (Integer n : numeros) {
            if (n < 1 || n > rango) {
                return false;
            }
        }

        // No repetidos
        Set<Integer> unicos = new HashSet<>(numeros);

        if (unicos.size() != 4) {
            return false;
        }

        // Resultado correcto
        return resultado == calcularResultado();
    }

    // Getters y Setters
    public List<Integer> getNumeros() {
        return new ArrayList<>(numeros);
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = new ArrayList<>(numeros);
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }
}