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

    public Nene getOrCreate(String name){
        try {
            String query = prepareGet(name);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Nene(resultSet.getString("nombre"),resultSet.getLong("id"));
            } else {
                String insert = prepareInsert(name);
                statement = conexion.createStatement();
                statement.executeUpdate(insert);
                statement = conexion.createStatement();
                resultSet = statement.executeQuery("SELECT last_insert_rowid()");
                if (resultSet.next()){
                    return new Nene(name, (long) resultSet.getLong(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String prepareGet(Long id){
        return "SELECT * FROM nene WHERE ID="+id;
    }

    private String prepareGet(String name){
        return "SELECT * FROM nene WHERE nombre='"+ name + "'";
    }

    private String prepareInsert(String name){
        return "INSERT INTO nene (nombre) values ('"+ name + "')";
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
