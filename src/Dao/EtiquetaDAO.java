package Dao;

import Model.Etiqueta;
import Model.Notificacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaDAO {

    public List<Etiqueta> getEtiquetas(){
        List<Etiqueta> lista = new ArrayList<Etiqueta>();
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String query = prepareList();
            Connection conexion = DriverManager.getConnection("jdbc:odbc:laboratorio", "user", "password");
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lista.add(new Etiqueta(resultSet.getString("nombre"), resultSet.getLong("id")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void eliminarEtiqueta(Long id){
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String query = prepareDelete(id);
            Connection conexion = DriverManager.getConnection("jdbc:odbc:laboratorio", "user", "password");
            Statement statement = conexion.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Etiqueta getEtiqueta(Long id){
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String query = prepareGet(id);
            Connection conexion = DriverManager.getConnection("jdbc:odbc:laboratorio", "user", "password");
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Etiqueta(resultSet.getString("nombre"), resultSet.getLong("id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void guardarEtiqueta(String nombre){
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String query = prepareInsert(nombre);
            Connection conexion = DriverManager.getConnection("jdbc:odbc:laboratorio", "user", "password");
            Statement statement = conexion.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String prepareGet(Long id){
        return "SELECT * FROM ETIQUETA WHERE ID=" + id;
    }

    private String prepareDelete(Long id){
        return "DELETE FROM ETIQUETA WHERE ID=" + id;
    }


    private String prepareInsert(String nombre){
        return "INSERT INTO ETIQUETA" +
                " (nombre)" +
                " VALUES (" + nombre + ")";
    }

    private String prepareList(){
        return "SELECT * FROM ETIQUETA";
    }

}
