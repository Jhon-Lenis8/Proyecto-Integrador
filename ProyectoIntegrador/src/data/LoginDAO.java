package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    /**
     * Autentica al usuario en la base de datos.
     * @param identificacion Identificación del usuario.
     * @param contrasena Contraseña ingresada.
     * @return "Administrador", "Usuario" o null si no se encontró coincidencia.
     */
    public String autenticar(String identificacion, String contrasena) {
        // Eliminar espacios innecesarios
        identificacion = identificacion.trim();
        contrasena = contrasena.trim();
        System.out.println("Autenticando usuario: " + identificacion);

        // Verificar en la tabla ADMINISTRADOR usando la columna IDENTIFICACION
        String sqlAdmin = "SELECT CONTRASENA FROM ADMINISTRADOR WHERE IDENTIFICACION = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlAdmin)) {

            stmt.setString(1, identificacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String contrasenaBD = rs.getString("CONTRASENA");
                System.out.println("Registro ADMINISTRADOR encontrado: contraseña en BD = [" + contrasenaBD + "]");
                if (contrasenaBD != null && contrasenaBD.equals(contrasena)) {
                    System.out.println("Coincidencia encontrada en ADMINISTRADOR");
                    return "Administrador";
                } else {
                    System.out.println("La contraseña no coincide para ADMINISTRADOR");
                }
            } else {
                System.out.println("No se encontró registro en ADMINISTRADOR para " + identificacion);
            }
        } catch (SQLException e) {
            System.err.println("Error autenticando administrador: " + e.getMessage());
        }

        // Verificar en la tabla USUARIO usando la columna IDENTIFICACION
        String sqlUsuario = "SELECT CONTRASENA FROM USUARIO WHERE IDENTIFICACION = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlUsuario)) {

            stmt.setString(1, identificacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String contrasenaBD = rs.getString("CONTRASENA");
                System.out.println("Registro USUARIO encontrado: contraseña en BD = [" + contrasenaBD + "]");
                if (contrasenaBD != null && contrasenaBD.equals(contrasena)) {
                    System.out.println("Coincidencia encontrada en USUARIO");
                    return "Usuario";
                } else {
                    System.out.println("La contraseña no coincide para USUARIO");
                }
            } else {
                System.out.println("No se encontró registro en USUARIO para " + identificacion);
            }
        } catch (SQLException e) {
            System.err.println("Error autenticando usuario: " + e.getMessage());
        }

        System.out.println("No se encontró coincidencia para la identificación " + identificacion);
        return null; // No se encontró coincidencia
    }
}