package com.ooodlegame.unitarias;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ooodlegame.model.Ecuacion;
import com.ooodlegame.model.Intento;
import com.ooodlegame.model.Partida;

public class PartidaTest {

    @Test
    void registrarIntento_debeAgregarIntento() {

        Partida p = new Partida();

        Intento intento = new Intento(List.of(1,2,3,4));

        p.registrarIntento(intento);

        assertEquals(1, p.getIntentos().size());
    }

    @Test
    void verificarVictoria_debeRetornarTrue() throws Exception {

        Partida p = new Partida();

        Ecuacion ecuacion = new Ecuacion();
        ecuacion.setNumeros(List.of(1,2,3,4));

        Intento intento = new Intento(List.of(1,2,3,4));
        intento.compararConSolucion(ecuacion);

        p.registrarIntento(intento);

        assertTrue(p.verificarVictoria());
    }

    @Test
    void verificarVictoria_debeRetornarFalse() throws Exception {

        Partida p = new Partida();

        Ecuacion ecuacion = new Ecuacion();
        ecuacion.setNumeros(List.of(1,2,3,4));

        Intento intento = new Intento(List.of(4,3,2,1));
        intento.compararConSolucion(ecuacion);

        p.registrarIntento(intento);

        assertFalse(p.verificarVictoria());
    }

    @Test
    void verificarDerrota_debeRetornarTrue() throws Exception {

        Partida p = new Partida();

        Ecuacion ecuacion = new Ecuacion();
        ecuacion.setNumeros(List.of(1,2,3,4));

        for(int i=0; i<6; i++) {

            Intento intento = new Intento(List.of(4,3,2,1));
            intento.compararConSolucion(ecuacion);

            p.registrarIntento(intento);
        }

        assertTrue(p.verificarDerrota());
    }

    @Test
    void finalizarPartida_debeCambiarEstado() {

        Partida p = new Partida();

        p.finalizarPartida();

        assertTrue(p.isFinalizada());
    }
}