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
                    rs.getTime("hora_fin").toLocalTime(),
                    rs.getDouble("precio")
            );
            lista.add(a);
        }

        rs.close();
        st.close();
        con.close();
        return lista;
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
