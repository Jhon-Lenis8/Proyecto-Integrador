package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() {
        conn = DBConnection.getConnection();
    }

    // Insertar usuario
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id_usuario, nombre, correo, contrasena, rol) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getIdentificacion());
            stmt.setString(2, usuario.getNombre() + " " + usuario.getApellido());
            stmt.setString(3, usuario.getCorreoElectronico());
            stmt.setString(4, "sin_clave"); 
            stmt.setString(5, usuario.getTipoUsuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario:");
            e.printStackTrace();
            return false;
        }
    }

    // Buscar usuario por ID
    public Usuario buscarPorId(String idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String[] nombrePartes = rs.getString("nombre").split(" ", 2);
                String nombre = nombrePartes.length > 0 ? nombrePartes[0] : "";
                String apellido = nombrePartes.length > 1 ? nombrePartes[1] : "";

                return new Usuario(
                    rs.getString("id_usuario"),
                    nombre,
                    apellido,
                    rs.getString("rol"),
                    rs.getString("correo"),
                    "sin_telefono"
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario:");
            e.printStackTrace();
        }
        return null;
    }

}
