package com.ooodlegame.model;

/**
 * Enumeración que representa el estado de una celda
 * luego de validar un intento en el juego.
 * 
 * VERDE: número correcto en la posición correcta.
 * AMARILLO: número correcto en posición incorrecta.
 * GRIS: número no presente en la ecuación secreta.
 * 
 * Se utiliza para mostrar retroalimentación visual
 * al jugador después de cada intento.
 */
public enum EstadoCelda {
    /** Número correcto en posición correcta */
    VERDE,
    
    /** Número correcto en posición incorrecta */
    AMARILLO,

    /** Número no presente en la solución */
    GRIS
}
