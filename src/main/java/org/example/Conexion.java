package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/workflow";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "luciana";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}