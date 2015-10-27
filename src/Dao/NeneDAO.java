package Dao;

import Model.Etiqueta;
import Model.Nene;
import Utils.ConexionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NeneDAO extends ObservableDAO{

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

    public List getNenes() {
        List<Nene> lista = new ArrayList<>();
        try {
            String query = prepareList();
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lista.add(new Nene(resultSet.getString("nombre"), resultSet.getLong("id")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private String prepareList(){
        return "SELECT * FROM nene";
    }

    @Override
    public List getAllItems() {
        return this.getNenes();
    }
}
