package Dao;

import Model.Categoria;
import Model.Etiqueta;
import Utils.ConexionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO extends ObservableDAO{

    public Categoria getCategoria(Long id){
        try {
            String query = prepareGet(id);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Categoria(resultSet.getString("nombre"), resultSet.getLong("id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Categoria> getCategorias(){
        List<Categoria> lista = new ArrayList<>();
        try {
            String query = prepareList();
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lista.add(new Categoria(resultSet.getString("nombre"), resultSet.getLong("id")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private String prepareGet(Long id){
        return "SELECT * FROM categoria WHERE ID=" + id;
    }

    private String prepareList(){
        return "SELECT * FROM categoria";
    }


    @Override
    public List getAllItems() {
        return this.getCategorias();
    }
}
