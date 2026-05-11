package com.ooodlegame.services;

import com.ooodlegame.model.Ecuacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EcuacionDAO {

    /**
     * Guarda una ecuación válida en la base de datos.
     *
     * @param ec ecuación a guardar
     * @throws Exception si la ecuación es nula, inválida o falla la inserción
     */
    public void guardarEcuacion(Ecuacion ec) throws Exception {

        if (ec == null) {
            throw new Exception("La ecuación no puede ser nula.");
        }

        if (!ec.validarEcuacion()) {
            throw new Exception("La ecuación no es válida.");
        }
        
        if (existeEcuacion(ec)) {
            throw new Exception("La ecuación ya existe.");
        }

        String sql = """
                INSERT INTO ecuacion
                (num1, num2, num3, num4, resultado, rango)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
            Connection conn = ConexionBD.getInstance().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            List<Integer> nums = ec.getNumeros();

            ps.setInt(1, nums.get(0));
            ps.setInt(2, nums.get(1));
            ps.setInt(3, nums.get(2));
            ps.setInt(4, nums.get(3));
            ps.setInt(5, ec.getResultado());
            ps.setInt(6, ec.getRango());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new Exception("No se pudo guardar la ecuación.");
            }

        } catch (SQLException e) {
            throw new Exception("Error al guardar ecuación.", e);
        }
    }

    /**
     * Verifica si una ecuación ya existe en la base de datos.
     *
     * @param ec ecuación a verificar
     * @return true si ya existe, false en caso contrario
     * @throws Exception si ocurre error en la consulta
     */
    public boolean existeEcuacion(Ecuacion ec) throws Exception {

        String sql = """
                SELECT COUNT(*)
                FROM ecuacion
                WHERE num1 = ?
                AND num2 = ?
                AND num3 = ?
                AND num4 = ?
                """;

        try (
            Connection conn = ConexionBD.getInstance().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            List<Integer> nums = ec.getNumeros();

            ps.setInt(1, nums.get(0));
            ps.setInt(2, nums.get(1));
            ps.setInt(3, nums.get(2));
            ps.setInt(4, nums.get(3));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;

        } catch (SQLException e) {
            throw new Exception("Error al verificar ecuación.", e);
        }
    }

    /**
     * Obtiene una ecuación aleatoria según el rango.
     *
     * @param rango rango solicitado
     * @return ecuación encontrada
     * @throws Exception si ocurre error o no hay ecuaciones disponibles
     */
    public Ecuacion obtenerEcuacionAleatoria(int rango) throws Exception {

        String sql = """
                SELECT num1, num2, num3, num4, resultado, rango
                FROM ecuacion
                WHERE rango = ?
                ORDER BY RAND()
                LIMIT 1
                """;

        try (
            Connection conn = ConexionBD.getInstance().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, rango);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    List<Integer> numeros = new ArrayList<>();

                    numeros.add(rs.getInt("num1"));
                    numeros.add(rs.getInt("num2"));
                    numeros.add(rs.getInt("num3"));
                    numeros.add(rs.getInt("num4"));

                    Ecuacion ecuacion = new Ecuacion();
                    ecuacion.setNumeros(numeros);
                    ecuacion.setResultado(rs.getInt("resultado"));
                    ecuacion.setRango(rs.getInt("rango"));

                    return ecuacion;
                }
            }

            throw new Exception("No existen ecuaciones para ese rango.");

        } catch (SQLException e) {
            throw new Exception("Error al obtener ecuación aleatoria.", e);
        }
    }
}