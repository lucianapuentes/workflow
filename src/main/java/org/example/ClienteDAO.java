package org.example;

import org.example.Cliente;
import org.example.Conexion;
import java.sql.*;
import java.util.*;

public class ClienteDAO {
    public void insertar(Cliente cliente) throws SQLException {
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO clientes.clientes (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";

        // Solicitamos que devuelva las claves generadas
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellido());
        ps.setString(3, cliente.getEmail());
        ps.setString(4, cliente.getTelefono());
        ps.executeUpdate();

        // Obtenemos el ID generado
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            cliente.setId(rs.getInt(1)); // Seteamos el ID generado en el objeto Cliente
        }

        rs.close();
        ps.close();
        con.close();
    }


    public void actualizar(Cliente cliente) throws SQLException {
        Connection con = Conexion.getConexion();
        String sql = "UPDATE clientes.clientes SET nombre=?, apellido=?, email=?, telefono=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellido());
        ps.setString(3, cliente.getEmail());
        ps.setString(4, cliente.getTelefono());
        ps.setInt(5, cliente.getId());
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public void eliminar(int id) throws SQLException {
        Connection con = Conexion.getConexion();
        PreparedStatement ps = con.prepareStatement("DELETE FROM clientes.clientes WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM clientes.clientes ORDER BY id");
        while (rs.next()) {
            Cliente c = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("telefono")
            );
            lista.add(c);
        }
        rs.close();
        st.close();
        con.close();
        return lista;
    }
}