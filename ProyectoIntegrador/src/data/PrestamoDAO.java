package data;

import model.Prestamo;
import java.sql.*;
import java.util.ArrayList;

public class PrestamoDAO {

    public boolean insertarPrestamo(Prestamo p) {
        String sql = "INSERT INTO PRESTAMO (ID_PRESTAMO, ID_USUARIO, ID_RECURSO, TIPO_RECURSO, FECHA_SOLICITUD, FECHA_USO, FECHA_DEVOLUCION, ESTADO) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getIdPrestamo());
            stmt.setString(2, p.getIdUsuario());
            stmt.setString(3, p.getIdRecurso());
            stmt.setString(4, p.getTipoPrestamo());
            stmt.setTimestamp(5, Timestamp.valueOf(p.getFechaSolicitud()));
            stmt.setTimestamp(6, Timestamp.valueOf(p.getFechaEntrega()));
            stmt.setTimestamp(7, p.getFechaDevolucion() != null ? Timestamp.valueOf(p.getFechaDevolucion()) : null);
            stmt.setString(8, p.getEstado());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar préstamo: " + e.getMessage());
            return false;
        }
    }

    public Prestamo buscarPorId(String idPrestamo) {
        String sql = "SELECT * FROM PRESTAMO WHERE ID_PRESTAMO = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Prestamo(
                    rs.getString("ID_PRESTAMO"),
                    rs.getString("ID_USUARIO"),
                    rs.getString("ID_RECURSO"),
                    rs.getString("TIPO_RECURSO"),
                    rs.getTimestamp("FECHA_SOLICITUD").toLocalDateTime(),
                    rs.getTimestamp("FECHA_USO").toLocalDateTime(),
                    rs.getTimestamp("FECHA_DEVOLUCION") != null ? rs.getTimestamp("FECHA_DEVOLUCION").toLocalDateTime() : null,
                    rs.getString("ESTADO")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar préstamo: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Prestamo> obtenerTodos() {
        ArrayList<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRESTAMO";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Prestamo(
                    rs.getString("ID_PRESTAMO"),
                    rs.getString("ID_USUARIO"),
                    rs.getString("ID_RECURSO"),
                    rs.getString("TIPO_RECURSO"),
                    rs.getTimestamp("FECHA_SOLICITUD").toLocalDateTime(),
                    rs.getTimestamp("FECHA_USO").toLocalDateTime(),
                    rs.getTimestamp("FECHA_DEVOLUCION") != null ? rs.getTimestamp("FECHA_DEVOLUCION").toLocalDateTime() : null,
                    rs.getString("ESTADO")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar préstamos: " + e.getMessage());
        }
        return lista;
    }
}