package data;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean insertarUsuario(Usuario usuario, String contrasena) {
        String sql;

        if ("Administrador".equals(usuario.getTipoUsuario())) {
            sql = "INSERT INTO ADMINISTRADOR (IDENTIFICACION, NOMBRE_COMPLETO, EMAIL, CONTRASENA) VALUES (?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO USUARIO (IDENTIFICACION, NOMBRE, APELLIDO, CORREO, TIPO_USUARIO, CONTRASENA) VALUES (?, ?, ?, ?, ?, ?)";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getIdentificacion());

            if ("Administrador".equals(usuario.getTipoUsuario())) {
                stmt.setString(2, usuario.getNombre() + " " + usuario.getApellido());
                stmt.setString(3, usuario.getCorreoElectronico());
                stmt.setString(4, contrasena); // Se almacena directamente sin cifrado
            } else {
                stmt.setString(2, usuario.getNombre());
                stmt.setString(3, usuario.getApellido());
                stmt.setString(4, usuario.getCorreoElectronico());
                stmt.setString(5, usuario.getTipoUsuario());
                stmt.setString(6, contrasena); // Se almacena directamente sin cifrado
            }

            int filas = stmt.executeUpdate();
            System.out.println("Filas afectadas: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }
}