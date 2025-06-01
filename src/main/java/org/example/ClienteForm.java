package org.example;

import org.example.Cliente;
import org.example.ClienteDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ClienteForm extends JDialog {
    private JTextField txtNombre, txtApellido, txtEmail, txtTelefono;
    private Cliente cliente;

    public ClienteForm(JFrame parent, Cliente cliente) {
        super(parent, "Formulario de Cliente", true);
        this.cliente = cliente;

        setSize(300, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        add(txtApellido);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        add(txtTelefono);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());
        add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);

        if (cliente != null) {
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido());
            txtEmail.setText(cliente.getEmail());
            txtTelefono.setText(cliente.getTelefono());
        }
    }

    private void guardar() {
        if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y Apellido son obligatorios");
            return;
        }

        try {
            ClienteDAO dao = new ClienteDAO();

            if (cliente == null) {
                cliente = new Cliente();
            }

            cliente.setNombre(txtNombre.getText().trim());
            cliente.setApellido(txtApellido.getText().trim());
            cliente.setEmail(txtEmail.getText().trim());
            cliente.setTelefono(txtTelefono.getText().trim());

            if (cliente.getId() == 0) {
                dao.insertar(cliente);
            } else {
                dao.actualizar(cliente);
            }

            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar cliente: " + ex.getMessage());
        }
    }
}
