package com.ooodlegame.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión con la base de datos MySQL.
 * 
 * Implementa el patrón Singleton para garantizar una única instancia
 * de conexión durante la ejecución de la aplicación.
 * 
 * La base de datos utilizada es "ooodle".
 */
public class ConexionBD {

    /**
     * Instancia única de la clase ConexionBD.
     */
    private static ConexionBD instancia;

    /**
     * URL de conexión a la base de datos MySQL.
     */
    private String url = "jdbc:mysql://localhost:3306/ooodle";

    private String usuario = "root";
    private String contrasena = "";

    private ConexionBD() { }

    /**
     * Obtiene la instancia única de la clase ConexionBD.
     * 
     * Si la instancia no existe, se crea automáticamente.
     * 
     * @return instancia única de ConexionBD.
     */
    public static ConexionBD getInstance() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    /**
     * Establece y retorna una conexión con la base de datos.
     * 
     * @return objeto Connection si la conexión es exitosa;
     *         null si ocurre un error.
     */
    public Connection getConexion() {
        try {
            return DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
    
}
