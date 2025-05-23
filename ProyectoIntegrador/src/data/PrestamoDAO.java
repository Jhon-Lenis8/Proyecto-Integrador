package data;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import model.Prestamo;

public class PrestamoDAO {

    private Connection conn;

    public PrestamoDAO() {
        conn = DBConnection.getConnection();
    }

    // Insertar préstamo
    public boolean insertarPrestamo(Prestamo p) {
        String sql = "INSERT INTO prestamos (id_prestamo, id_usuario, id_recurso, tipo_recurso, fecha_solicitud, fecha_uso, fecha_devolucion, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            System.err.println("❌ Error al insertar préstamo:");
            e.printStackTrace();
            return false;
        }
    }

    // Buscar préstamo por ID
    public Prestamo buscarPorId(String idPrestamo) {
        String sql = "SELECT * FROM prestamos WHERE id_prestamo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Prestamo(
                    rs.getString("id_prestamo"),
                    rs.getString("id_usuario"),
                    rs.getString("id_recurso"),
                    rs.getString("tipo_recurso"),
                    rs.getTimestamp("fecha_solicitud").toLocalDateTime(),
                    rs.getTimestamp("fecha_uso").toLocalDateTime(),
                    rs.getTimestamp("fecha_devolucion") != null ? rs.getTimestamp("fecha_devolucion").toLocalDateTime() : null,
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar préstamo:");
            e.printStackTrace();
        }
        return null;
    }

    // Listar todos los préstamos
    public ArrayList<Prestamo> obtenerTodos() {
        ArrayList<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prestamo p = new Prestamo(
                    rs.getString("id_prestamo"),
                    rs.getString("id_usuario"),
                    rs.getString("id_recurso"),
                    rs.getString("tipo_recurso"),
                    rs.getTimestamp("fecha_solicitud").toLocalDateTime(),
                    rs.getTimestamp("fecha_uso").toLocalDateTime(),
                    rs.getTimestamp("fecha_devolucion") != null ? rs.getTimestamp("fecha_devolucion").toLocalDateTime() : null,
                    rs.getString("estado")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar préstamos:");
            e.printStackTrace();
        }
        return lista;
    }
}
