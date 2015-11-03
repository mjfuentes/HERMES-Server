package Dao;

import Model.Categoria;
import Model.Contexto;
import Model.Etiqueta;
import Model.Nene;
import Utils.ConexionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContextoDAO extends ObservableDAO{

    public Contexto getContexto(Long id){
        try {
            String query = prepareGet(id);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Contexto(resultSet.getString("nombre"), resultSet.getLong("id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Contexto getOrCreate(String name){
        try {
            String query = prepareGet(name);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Contexto(resultSet.getString("nombre"),resultSet.getLong("id"));
            } else {
                String insert = prepareInsert(name);
                statement = conexion.createStatement();
                statement.executeUpdate(insert);
                statement = conexion.createStatement();
                resultSet = statement.executeQuery("SELECT last_insert_rowid()");
                if (resultSet.next()){
                    return new Contexto(name, (long) resultSet.getLong(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contexto> getContextos(){
        List<Contexto> lista = new ArrayList<>();
        try {
            String query = prepareList();
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lista.add(new Contexto(resultSet.getString("nombre"), resultSet.getLong("id")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private String prepareGet(String name){
        return "SELECT * FROM contexto WHERE nombre='"+ name + "'";
    }

    private String prepareInsert(String name){
        return "INSERT INTO contexto (nombre) values ('"+ name + "')";
    }

    private String prepareGet(Long id){
        return "SELECT * FROM contexto WHERE ID=" + id;
    }

    private String prepareList(){
        return "SELECT * FROM contexto";
    }


    @Override
    public List getAllItems() {
        return this.getContextos();
    }
}
