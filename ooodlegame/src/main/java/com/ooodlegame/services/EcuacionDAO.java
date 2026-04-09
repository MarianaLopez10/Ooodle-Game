package com.ooodlegame.services;

import com.ooodlegame.model.Ecuacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EcuacionDAO {
    
  private ConexionDB conexion;

    public EcuacionDAO() {
        this.conexion = ConexionDB.getInstance();
    }

    public void guardarEcuacion(Ecuacion ec) {
        String sql = "INSERT INTO ecuacion (num1, num2, num3, num4, resultado, rango) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            List<Integer> numeros = ec.getNumeros();
            ps.setInt(1, numeros.get(0));
            ps.setInt(2, numeros.get(1));
            ps.setInt(3, numeros.get(2));
            ps.setInt(4, numeros.get(3));
            ps.setInt(5, ec.getResultado());
            ps.setInt(6, ec.getRango());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public Ecuacion consultarEcuacion(int rango) {
        String sql = "SELECT * FROM ecuacion WHERE rango = ? ORDER BY RAND() LIMIT 1";
        try (Connection conn = conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rango);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Integer> numeros = new ArrayList<>();
                numeros.add(rs.getInt("num1"));
                numeros.add(rs.getInt("num2"));
                numeros.add(rs.getInt("num3"));
                numeros.add(rs.getInt("num4"));
                Ecuacion ec = new Ecuacion();
                ec.setNumeros(numeros);
                ec.setResultado(rs.getInt("resultado"));
                ec.setRango(rango);
                return ec;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
        return null;
    }
}