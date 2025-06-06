package data;

import model.SalaInformatica;
import java.sql.*;
import java.util.ArrayList;

public class SalaDAO {

    public boolean insertarSala(SalaInformatica sala) {
        String sql = "INSERT INTO SALA (ID_SALA, NOMBRE, CAPACIDAD, UBICACION, SOFTWARE, ESTADO) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sala.getId());
            stmt.setString(2, sala.getNombre());
            stmt.setInt(3, sala.getCapacidadMaxima());
            stmt.setString(4, sala.getSoftwareDisponible());
            stmt.setString(5, sala.getEstadoActual());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar sala: " + e.getMessage());
            return false;
        }
    }
}