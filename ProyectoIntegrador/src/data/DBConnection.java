package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "jhontatiana";
    private static final String PASSWORD = "jhontatiana"; 

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexion establecida con Oracle");
            } catch (ClassNotFoundException e) {
                System.err.println("No se encontro el driver JDBC de Oracle.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error al conectar con la base de datos.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
