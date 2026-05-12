package com.ooodlegame.integracion;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ooodlegame.model.Ecuacion;
import com.ooodlegame.services.ConexionBD;
import com.ooodlegame.services.EcuacionDAO;

/**
 * Clase de pruebas de integración para EcuacionDAO.
 *
 * Verifica el correcto funcionamiento de las operaciones
 * relacionadas con el almacenamiento y consulta de ecuaciones
 * en la base de datos.
 *
 * Las pruebas validan:
 * <ul>
 *     <li>El guardado de ecuaciones</li>
 *     <li>La consulta de ecuaciones aleatorias</li>
 *     <li>La verificación de existencia de ecuaciones</li>
 * </ul>
 *
 * Se utiliza una conexión real a la base de datos
 * para comprobar la integración entre la aplicación
 * y el sistema gestor de datos.
 */
public class TestEcuacionDAO {

    /**
     * Objeto DAO utilizado para realizar operaciones
     * sobre la tabla de ecuaciones.
     */
    EcuacionDAO dao = new EcuacionDAO();

    /**
     * Limpia la tabla de ecuaciones antes de cada prueba.
     *
     * Elimina todos los registros almacenados para garantizar
     * que cada test se ejecute en un entorno limpio e independiente.
     *
     * @throws Exception si ocurre un error durante la limpieza
     *                   de la base de datos
     */
    @BeforeEach
    void clean() throws Exception {

        Connection con = ConexionBD.getInstance().getConexion();

        con.setAutoCommit(false);

        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM ecuacion");

        con.setAutoCommit(true);
    }

    /**
     * Verifica que una ecuación pueda guardarse correctamente
     * y posteriormente obtenerse de forma aleatoria.
     *
     * @throws Exception si ocurre un error durante la prueba
     */
    @Test
    void guardarEcuacion_y_consultarAleatoria() throws Exception {

        Ecuacion ec = new Ecuacion();

        ec.setNumeros(Arrays.asList(2, 3, 4, 1)); // 2*3+4-1 = 9
        ec.setResultado(9);
        ec.setRango(9);

        dao.guardarEcuacion(ec);

        Ecuacion resultado = dao.obtenerEcuacionAleatoria(9);

        assertNotNull(resultado);
        assertEquals(9, resultado.getResultado());
        assertEquals(9, resultado.getRango());
        assertEquals(4, resultado.getNumeros().size());
    }

    /**
     * Verifica el funcionamiento del método existeEcuacion().
     *
     * Comprueba que:
     * <ul>
     *     <li>Una ecuación almacenada retorne true</li>
     *     <li>Una ecuación inexistente retorne false</li>
     * </ul>
     *
     * @throws Exception si ocurre un error durante la prueba
     */
    @Test
    void verificaExisteEcuacion() throws Exception {

        Ecuacion ecGuardada = new Ecuacion();

        ecGuardada.setNumeros(Arrays.asList(2, 3, 4, 1));
        ecGuardada.setResultado(9);
        ecGuardada.setRango(9);

        dao.guardarEcuacion(ecGuardada);

        // Debe existir
        assertTrue(dao.existeEcuacion(ecGuardada));

        Ecuacion ecNoExistente = new Ecuacion();

        ecNoExistente.setNumeros(Arrays.asList(5, 6, 7, 8));
        ecNoExistente.setResultado(29);
        ecNoExistente.setRango(9);

        // No debe existir
        assertFalse(dao.existeEcuacion(ecNoExistente));
    }
}