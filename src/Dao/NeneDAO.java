package Dao;

import Model.Nene;
import Utils.ConexionManager;

import java.sql.*;

public class NeneDAO {

    public Nene getNene(Long id){
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String query = prepareGet(id);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Nene(resultSet.getString("nombre"),resultSet.getLong("id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String prepareGet(Long id){
        return "SELECT * FROM NINO WHERE ID="+id;
    }
}
