package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SalaInformatica;

public class SalaDAO {

    private Connection conn;

    public SalaDAO() {
        conn = DBConnection.getConnection();
    }

    // Insertar nueva sala
    public boolean insertarSala(SalaInformatica sala) {
        String sql = "INSERT INTO salas (id_sala, nombre, capacidad, software, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sala.getId());
            stmt.setString(2, sala.getNombre());
            stmt.setInt(3, sala.getCapacidadMaxima());
            stmt.setString(4, sala.getSoftwareDisponible());
            stmt.setString(5, sala.getEstadoActual());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar sala:");
            e.printStackTrace();
            return false;
        }
    }

    // Buscar una sala por ID
    public SalaInformatica buscarPorId(String idSala) {
        String sql = "SELECT * FROM salas WHERE id_sala = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idSala);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SalaInformatica(
                    rs.getString("id_sala"),
                    rs.getString("nombre"),
                    rs.getInt("capacidad"),
                    rs.getString("software"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar sala:");
            e.printStackTrace();
        }
        return null;
    }

    // Listar todas las salas
    public ArrayList<SalaInformatica> obtenerTodas() {
        ArrayList<SalaInformatica> lista = new ArrayList<>();
        String sql = "SELECT * FROM salas";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SalaInformatica sala = new SalaInformatica(
                    rs.getString("id_sala"),
                    rs.getString("nombre"),
                    rs.getInt("capacidad"),
                    rs.getString("software"),
                    rs.getString("estado")
                );
                lista.add(sala);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar salas:");
            e.printStackTrace();
        }
        return lista;
    }
}
