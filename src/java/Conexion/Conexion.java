package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection connection = null;
    final String usuario = "root";
    final String contrasena = "Nic@20017";
    final String ip = "localhost";
    final String puerto = "3306";
    final String bd = "registros";
    String ruta = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd + "?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(ruta, usuario, contrasena);
            System.out.println("Se Conecto Correctamente");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error getConexionSql()" + e.getMessage());
        }
        return connection;
    }
}
