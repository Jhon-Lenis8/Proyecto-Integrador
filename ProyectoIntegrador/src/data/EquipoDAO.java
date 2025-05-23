package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EquipoAudiovisual;

public class EquipoDAO {

    private Connection conn;

    public EquipoDAO() {
        conn = DBConnection.getConnection();
    }

    // Insertar nuevo equipo
    public boolean insertarEquipo(EquipoAudiovisual equipo) {
        String sql = "INSERT INTO equipos (id_equipo, nombre, tipo, ubicacion, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipo.getId());
            stmt.setString(2, equipo.getNombre());
            stmt.setString(3, equipo.getTipo());
            stmt.setString(4, equipo.getUbicacion());
            stmt.setString(5, equipo.getEstado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar equipo:");
            e.printStackTrace();
            return false;
        }
    }

    // Buscar un equipo por ID
    public EquipoAudiovisual buscarPorId(String idEquipo) {
        String sql = "SELECT * FROM equipos WHERE id_equipo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idEquipo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new EquipoAudiovisual(
                    rs.getString("id_equipo"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getString("ubicacion"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar equipo:");
            e.printStackTrace();
        }
        return null;
    }

    // Listar todos los equipos
    public ArrayList<EquipoAudiovisual> obtenerTodos() {
        ArrayList<EquipoAudiovisual> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipos";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EquipoAudiovisual equipo = new EquipoAudiovisual(
                    rs.getString("id_equipo"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getString("ubicacion"),
                    rs.getString("estado")
                );
                lista.add(equipo);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar equipos:");
            e.printStackTrace();
        }
        return lista;
    }
}
