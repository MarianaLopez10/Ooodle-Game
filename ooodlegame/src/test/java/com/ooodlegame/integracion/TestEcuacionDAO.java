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

public class TestEcuacionDAO {

    EcuacionDAO dao = new EcuacionDAO();

    @BeforeEach
    void clean() throws Exception {

        Connection con = ConexionBD.getInstance().getConexion();

        con.setAutoCommit(false);

        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM ecuacion");

        con.setAutoCommit(true);
    }

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
}