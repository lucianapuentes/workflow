package org.example;
import org.example.Alquiler;
import org.example.Conexion;
import org.example.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAO {
    public List<Alquiler> obtenerAlquileresActivos() throws SQLException {
        List<Alquiler> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM clientes.alquileres";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Alquiler a = new Alquiler(
                    rs.getInt("num_operacion"),
                    rs.getInt("id_cliente"),
                    rs.getInt("codigo_escritorio"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getTime("hora_inicio").toLocalTime(),
                    rs.getTime("hora_fin").toLocalTime()
            );
            lista.add(a);
        }

        rs.close();
        st.close();
        con.close();
        return lista;
    }
    public static List<Alquiler> obtenerTodos() {
        List<Alquiler> alquileres = new ArrayList<>();

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM clientes.alquileres ORDER BY num_operacion ASC");) {

            while (rs.next()) {
                Alquiler a = new Alquiler();
                a.setNumOperacion(rs.getInt("num_operacion"));
                a.setIdCliente(rs.getInt("id_cliente"));
                a.setCodigoEscritorio(Integer.parseInt(rs.getString("codigo_escritorio")));
                a.setFecha(rs.getDate("fecha").toLocalDate());
                a.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                a.setHoraFin(rs.getTime("hora_fin").toLocalTime());
                a.setPrecio(a.calcularPrecio()); // solo si usás get/se
                alquileres.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alquileres;
    }

    public void insertar(Alquiler alquiler) throws SQLException {
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO clientes.alquileres (id_cliente, codigo_escritorio, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, alquiler.getIdCliente());
        ps.setInt(2, alquiler.getCodigoEscritorio());
        ps.setDate(3, java.sql.Date.valueOf(alquiler.getFecha()));
        ps.setTime(4, java.sql.Time.valueOf(alquiler.getHoraInicio()));
        ps.setTime(5, java.sql.Time.valueOf(alquiler.getHoraFin()));

        ps.executeUpdate();  // ¡FUNDAMENTAL!
        ps.close();
        con.close();
    }

}
