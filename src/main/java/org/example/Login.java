package org.example;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.example.Conexion;

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
        btnIngresar.addActionListener(e -> {
            if (autenticarUsuario(txtUsuario.getText(), new String(txtContrasena.getPassword()))) {
                new VentanaPrincipal();
                dispose(); // cerrar login
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }
        });

        panel.add(btnIngresar);
        add(panel);
        setVisible(true);
    }

    public static boolean autenticarUsuario(String username, String password) {
        String sql = "SELECT password_hash FROM usuarios.usuarios WHERE username = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String passAlmacenada = rs.getString("password_hash");
                return password.equals(passAlmacenada);  // Comparación directa
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en login: " + e.getMessage());
        }
        return false;
    }
}
