package com.ooodlegame.unitarias;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ooodlegame.model.Ecuacion;
import com.ooodlegame.model.Intento;
import com.ooodlegame.model.Partida;

/**
 * Clase de prueba unitaria para la clase Partida.
 *
 * Verifica el correcto funcionamiento de la lógica
 * principal relacionada con el desarrollo de una partida.
 *
 * Las pruebas incluyen:
 * <ul>
 *     <li>Registro de intentos</li>
 *     <li>Verificación de victoria</li>
 *     <li>Verificación de derrota</li>
 *     <li>Finalización de la partida</li>
 * </ul>
 *
 * Estas pruebas validan únicamente el comportamiento
 * de la clase Partida de forma aislada.
 */
public class PartidaTest {

    /**
     * Verifica que un intento sea agregado correctamente
     * a la lista de intentos de la partida.
     */
    @Test
    void registrarIntento_debeAgregarIntento() {

        Partida p = new Partida();

        Intento intento = new Intento(List.of(1,2,3,4));

        p.registrarIntento(intento);

        assertEquals(1, p.getIntentos().size());
    }

    /**
     * Verifica que verificarVictoria() retorne true
     * cuando el intento coincide completamente
     * con la ecuación secreta.
     *
     * @throws Exception si ocurre un error durante
     *                   la comparación de la solución
     */
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

    /**
     * Verifica que verificarVictoria() retorne false
     * cuando el intento no coincide con la solución.
     *
     * @throws Exception si ocurre un error durante
     *                   la comparación de la solución
     */
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

    /**
     * Verifica que verificarDerrota() retorne true
     * cuando se alcanzan los seis intentos permitidos
     * sin acertar la solución.
     *
     * @throws Exception si ocurre un error durante
     *                   la comparación de la solución
     */
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

    /**
     * Verifica que finalizarPartida() cambie
     * correctamente el estado de la partida
     * a finalizada.
     */
    @Test
    void finalizarPartida_debeCambiarEstado() {

        Partida p = new Partida();

        p.finalizarPartida();

        assertTrue(p.isFinalizada());
    }
}