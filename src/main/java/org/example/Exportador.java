package org.example;
import org.example.Conexion;

import java.sql.Connection;
import java.util.*;
import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
public class Exportador {
    public static void exportarClientesCSV(String archivoDestino) {
        String sql = "SELECT id, nombre, apellido, email, telefono FROM clientes.clientes";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             FileWriter fw = new FileWriter(archivoDestino)) {

            fw.write("ID,Nombre,Apellido,Email,Telefono\n");

            while (rs.next()) {
                fw.write(rs.getInt("id") + "," +
                        rs.getString("nombre") + "," +
                        rs.getString("apellido") + "," +
                        rs.getString("email") + "," +
                        rs.getString("telefono") + "\n");
            }

            JOptionPane.showMessageDialog(null, "Clientes exportados correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar: " + e.getMessage());
        }
    }
    public static void exportarAlquileresCSV(String archivoDestino) {
        String sql = "SELECT num_operacion, id_cliente, codigo_escritorio, fecha, hora_inicio, hora_fin FROM clientes.alquileres";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             FileWriter fw = new FileWriter(archivoDestino)) {

            fw.write("numOperacion,idCliente,codEscritorio,fecha,horaInicio,horaFin,precio\n");

            while (rs.next()) {
                fw.write(rs.getInt("num_operacion") + "," +
                        rs.getInt("id_cliente") + "," +
                        rs.getString("codigo_escritorio") + "," +
                        rs.getDate("fecha") + "," +
                        rs.getTime("hora_inicio") + "," +
                        rs.getTime("hora_fin") + "\n");
            }

            JOptionPane.showMessageDialog(null, "Alquileres exportados correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar alquileres: " + e.getMessage());
        }
    }







}
