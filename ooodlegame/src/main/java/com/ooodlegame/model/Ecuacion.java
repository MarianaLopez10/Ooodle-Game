package com.ooodlegame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ecuacion {

    private List<Integer> numeros;
    private int resultado;
    private int rango;

    // Constructor vacío
    public Ecuacion() {
        this.numeros = new ArrayList<>();
    }

    // Ecuación aleatoria según el rango
    public void generarEcuacion(int rango) {
        this.rango = rango;

        // Lista de números disponibles del 1 al rango
        List<Integer> disponibles = new ArrayList<>();
        for (int i = 1; i <= rango; i++) {
            disponibles.add(i);
        }

        // Mezclar aleatoriamente
        Collections.shuffle(disponibles);

        // Tomar los primeros 4 números
        numeros.clear();
        for (int i = 0; i < 4; i++) {
            numeros.add(disponibles.get(i));
        }

        // Calcular y guardar el resultado
        this.resultado = calcularResultado();
    }

    // Calcula num1 × num2 + num3 - num4
    public int calcularResultado() {
        return (numeros.get(0) * numeros.get(1)) + numeros.get(2) - numeros.get(3);
    }

    // Getters y Setters
    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = numeros;
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