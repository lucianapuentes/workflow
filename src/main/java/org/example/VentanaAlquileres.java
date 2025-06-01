package org.example;
import org.example.AlquilerDAO;
import org.example.Conexion;
import org.example.Alquiler;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VentanaAlquileres extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaAlquileres() {
        setTitle("Alquileres Activos");
        setSize(700, 300);
        setLocationRelativeTo(null);

        // Ahora con más columnas
        modelo = new DefaultTableModel(new Object[]{
                "ID", "Cliente", "Fecha", "Hora Inicio", "Hora Fin", "Código Escritorio", "Precio"
        }, 0);
        tabla = new JTable(modelo);

        JButton btnNuevo = new JButton("Nuevo Alquiler");
        btnNuevo.addActionListener(e -> nuevoAlquiler());

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(btnNuevo, BorderLayout.SOUTH);

        mostrarActivos();
        setVisible(true);
    }

    private void mostrarActivos() {
        try {
            modelo.setRowCount(0); // Limpiar tabla
            AlquilerDAO dao = new AlquilerDAO();
            List<Alquiler> lista = dao.obtenerAlquileresActivos();
            for (Alquiler a : lista) {
                modelo.addRow(new Object[]{
                        a.getNumOperacion(),
                        a.getIdCliente(),
                        a.getFecha(),
                        a.getHoraInicio(),
                        a.getHoraFin(),
                        a.getCodigoEscritorio(),
                        a.getPrecio()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nuevoAlquiler() {
        try {
            int idCliente = Integer.parseInt(JOptionPane.showInputDialog(this, "ID Cliente:"));
            LocalDate fecha = LocalDate.parse(JOptionPane.showInputDialog(this, "Fecha (YYYY-MM-DD):"));
            LocalTime horaInicio = LocalTime.parse(JOptionPane.showInputDialog(this, "Hora inicio (HH:MM):"));
            LocalTime horaFin = LocalTime.parse(JOptionPane.showInputDialog(this, "Hora fin (HH:MM):"));

            int codigoEscritorio = Integer.parseInt(JOptionPane.showInputDialog(this, "Código Escritorio:"));


            Alquiler nuevo = new Alquiler();
            nuevo.setIdCliente(idCliente);
            nuevo.setFecha(fecha);
            nuevo.setHoraInicio(horaInicio);
            nuevo.setHoraFin(horaFin);
            nuevo.setCodigoEscritorio(codigoEscritorio);


            AlquilerDAO dao = new AlquilerDAO();
            dao.insertar(nuevo);

            mostrarActivos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());

        }
    }
}
