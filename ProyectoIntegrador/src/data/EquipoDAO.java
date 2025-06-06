package data;

import model.EquipoAudiovisual;
import java.sql.*;
import java.util.ArrayList;

public class EquipoDAO {

    public boolean insertarEquipo(EquipoAudiovisual equipo) {
        // Se usa la tabla EQUIPO_AUDIOVISUAL con 4 columnas:
        // ID_EQUIPO, TIPO, MARCA, ESTADO  
        String sql = "INSERT INTO EQUIPO_AUDIOVISUAL (ID_EQUIPO, TIPO, MARCA, ESTADO) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipo.getId());
            stmt.setString(2, equipo.getTipo());
            // Aquí mapeamos "nombre" al campo MARCA de la tabla
            stmt.setString(3, equipo.getNombre());
            stmt.setString(4, equipo.getEstado());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar equipo: " + e.getMessage());
            return false;
        }
    }

    public EquipoAudiovisual buscarPorId(String idEquipo) {
        String sql = "SELECT * FROM EQUIPO_AUDIOVISUAL WHERE ID_EQUIPO = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idEquipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new EquipoAudiovisual(
                    rs.getString("ID_EQUIPO"),
                    // Usamos la columna MARCA para el atributo "nombre"
                    rs.getString("MARCA"),
                    rs.getString("TIPO"),
                    // No hay columna para ubicacion, se asigna valor por defecto
                    "",
                    rs.getString("ESTADO")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar equipo: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<EquipoAudiovisual> obtenerTodos() {
        ArrayList<EquipoAudiovisual> lista = new ArrayList<>();
        String sql = "SELECT * FROM EQUIPO_AUDIOVISUAL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new EquipoAudiovisual(
                    rs.getString("ID_EQUIPO"),
                    rs.getString("MARCA"),
                    rs.getString("TIPO"),
                    "",
                    rs.getString("ESTADO")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar equipos: " + e.getMessage());
        }
        return lista;
    }
}