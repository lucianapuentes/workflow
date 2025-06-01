package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    public Login() {
        setTitle("Iniciar sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panel.add(txtContrasena);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        panel.add(btnIngresar);
        add(panel);

        setVisible(true);
    }

    private void autenticar() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        // Validación básica (podrías usar una tabla en la BD en el futuro)
        if (usuario.equals("admin") && contrasena.equals("1234")) {
            dispose(); // Cierra la ventana de login
            new VentanaPrincipal(); // Abre ventana principal
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }
}
