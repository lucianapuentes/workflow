package org.example;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VentanaClientes extends JFrame {
    private JTable tabla;
    private ClienteTableModel modelo;

    public VentanaClientes() {
        setTitle("Gestión de Clientes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new ClienteTableModel();
        tabla = new JTable(modelo);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(e -> agregarCliente());
        btnEditar.addActionListener(e -> editarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarClientes();
        setVisible(true);
    }

    private void cargarClientes() {
        try {
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> lista = dao.listarTodos();
            modelo.setClientes(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregarCliente() {
        ClienteForm form = new ClienteForm(this, null);
        form.setVisible(true);
        cargarClientes();
    }

    private void editarCliente() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            Cliente cliente = modelo.getCliente(fila);
            ClienteForm form = new ClienteForm(this, cliente);
            form.setVisible(true);
            cargarClientes();
        }
    }

    private void eliminarCliente() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            Cliente cliente = modelo.getCliente(fila);
            int r = JOptionPane.showConfirmDialog(this, "¿Eliminar cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                try {
                    ClienteDAO dao = new ClienteDAO();
                    dao.eliminar(cliente.getId());
                    cargarClientes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
