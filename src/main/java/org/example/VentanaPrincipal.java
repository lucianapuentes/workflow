package org.example;

import javax.swing.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Sistema de Alquileres");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnClientes = new JButton("Gestionar Clientes");
        btnClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaClientes(); // Abre ventana de clientes
            }
        });

        JButton btnAlquileres = new JButton("Ver Alquileres Activos");
        btnAlquileres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaAlquileres(); // Abre ventana de alquileres
            }
        });
        JButton btnGenerarInforme = new JButton("Generar Informe PDF");
        btnGenerarInforme.addActionListener(e -> {
            try {
                InformePDFGenerator.generarInformeCompleto("informe.pdf");
                JOptionPane.showMessageDialog(this, "Informe PDF generado exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al generar el informe: " + ex.getMessage());
                ex.printStackTrace();
            }
        });


        JPanel panel = new JPanel();
        panel.add(btnClientes);
        panel.add(btnAlquileres);
        panel.add(btnGenerarInforme);
        // Agrego el botón de alquileres al panel

        add(panel);

        setVisible(true);
    }
}

