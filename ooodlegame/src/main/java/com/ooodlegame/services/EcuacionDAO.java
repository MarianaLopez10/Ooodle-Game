package com.ooodlegame.services;

import com.ooodlegame.model.Ecuacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EcuacionDAO {

    private ConexionBD conexion;

    public EcuacionDAO() {
        this.conexion = ConexionBD.getInstance();
    }

    // Guarda una ecuación válida en la base de datos
    public boolean guardarEcuacion(Ecuacion ec) {

        if (ec == null || !ec.validarEcuacion()) {
            return false;
        }

        String sql = """
                INSERT INTO ecuacion
                (num1, num2, num3, num4, resultado, rango)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            List<Integer> nums = ec.getNumeros();

            ps.setInt(1, nums.get(0));
            ps.setInt(2, nums.get(1));
            ps.setInt(3, nums.get(2));
            ps.setInt(4, nums.get(3));
            ps.setInt(5, ec.getResultado());
            ps.setInt(6, ec.getRango());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar ecuación: " + e.getMessage());
            return false;
        }
    }

    // Obtiene una ecuación aleatoria según el rango
    public Ecuacion obtenerEcuacionAleatoria(int rango) {

        String sql = """
                SELECT num1, num2, num3, num4, resultado, rango
                FROM ecuacion
                WHERE rango = ?
                ORDER BY RAND()
                LIMIT 1
                """;

        try (Connection conn = conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

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

        } catch (SQLException e) {
            System.out.println("Error al obtener ecuación: " + e.getMessage());
        }

        return null;
    }
}