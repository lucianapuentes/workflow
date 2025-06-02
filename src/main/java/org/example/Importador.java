package org.example;
import org.example.Conexion;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;
import java.sql.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class Importador extends javax.swing.JFrame {
    public static void importarClientesCSV(String archivoOrigen) {
        String insertSQL = "INSERT INTO clientes.clientes (id, nombre, apellido, email, telefono) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             BufferedReader br = new BufferedReader(new FileReader(archivoOrigen))) {

            String linea;
            boolean primera = true;
            while ((linea = br.readLine()) != null) {
                if (primera) { // omitir cabecera
                    primera = false;
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length != 5) continue;

                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setInt(1, Integer.parseInt(datos[0].trim()));  // id
                    pstmt.setString(2, datos[1].trim());                 // nombre
                    pstmt.setString(3, datos[2].trim());                 // apellido
                    pstmt.setString(4, datos[3].trim());                 // email
                    pstmt.setString(5, datos[4].trim());                 // telefono
                    pstmt.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(null, "Clientes importados correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al importar clientes: " + e.getMessage());
        }
        }

    public static void importarAlquileresCSV(String archivoOrigen) {
        String insertSQL = """
        INSERT INTO clientes.alquileres 
        (num_operacion, id_cliente, codigo_escritorio, fecha, hora_inicio, hora_fin) 
        VALUES (?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = Conexion.getConexion();
             BufferedReader br = new BufferedReader(new FileReader(archivoOrigen))) {

            String linea;
            boolean primera = true;
            int insertados = 0;

            while ((linea = br.readLine()) != null) {
                if (primera) {
                    primera = false;
                    continue; // Saltar la cabecera
                }

                String[] datos = linea.split(",", -1);
                if (datos.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }

                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setInt(1, Integer.parseInt(datos[0].trim()));           // num_operacion
                    pstmt.setInt(2, Integer.parseInt(datos[1].trim()));           // id_cliente
                    pstmt.setInt(3, Integer.parseInt(datos[2].trim()));                          // codigo_escritorio
                    pstmt.setDate(4, Date.valueOf(datos[3].trim()));              // fecha
                    pstmt.setTime(5, Time.valueOf(datos[4].trim()));              // hora_inicio
                    pstmt.setTime(6, Time.valueOf(datos[5].trim()));              // hora_fin
                    pstmt.executeUpdate();
                    insertados++;
                } catch (SQLException sqle) {
                    System.err.println("Error al insertar línea: " + linea);
                    sqle.printStackTrace();
                }
            }

            JOptionPane.showMessageDialog(null, "Importación completa: " + insertados + " alquileres cargados.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error general al importar alquileres: " + e.getMessage());
        }
    }





}




