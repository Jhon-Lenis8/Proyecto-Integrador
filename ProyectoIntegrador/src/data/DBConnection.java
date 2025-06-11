package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	// CONEXIÓN PARA UDI
	/*
	private static final String URL = "jdbc:oracle:thin:@192.168.254.215:1521:orcl"; 
	private static final String USER = "proII";
	private static final String PASSWORD = "proII";
	*/

	// CONEXIÓN LOCAL
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	private static final String USER = "proyecto";
	private static final String PASSWORD = "proyecto";


    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Error al cargar el driver de Oracle", e);
            }
        }
        return connection;
    }
}