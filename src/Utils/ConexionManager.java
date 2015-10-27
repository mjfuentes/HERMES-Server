package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionManager {
    private static Connection conexion;
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String DB = "jdbc:sqlite:/home/matias/Documents/Facultad/4to/Laboratorio/trabajo_final/trabajo_final";
    private ConexionManager(){
        super();
    }

    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        if (conexion == null){
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(DB,USER,PASSWORD);
        }
        return conexion;
    }

}
