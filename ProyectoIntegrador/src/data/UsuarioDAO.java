package data;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean insertarUsuario(Usuario usuario, String contrasena) {
        String sql = "INSERT INTO USUARIO (IDENTIFICACION, NOMBRE, APELLIDO, CORREO, TIPO_USUARIO, CONTRASENA) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getIdentificacion());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getCorreoElectronico());
            stmt.setString(5, usuario.getTipoUsuario());
            stmt.setString(6, cifrarContrasena(contrasena));

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    private String cifrarContrasena(String contrasena) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(contrasena.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.err.println("Error al cifrar la contraseña: " + e.getMessage());
            return null;
        }
    }
}