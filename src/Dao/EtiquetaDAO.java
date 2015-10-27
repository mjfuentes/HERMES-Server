package Dao;

import Interfaz.Monitor;
import Model.Etiqueta;
import Model.Notificacion;
import Utils.ConexionManager;
import javafx.beans.InvalidationListener;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class EtiquetaDAO extends ObservableDAO<Etiqueta>{

    public List<Etiqueta> getEtiquetas(){
        List<Etiqueta> lista = new ArrayList<Etiqueta>();
        try {
            String query = prepareList();
            Connection conexion = ConexionManager.getConexion();
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
            String query = prepareDelete(id);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            statement.executeUpdate(query);
            this.setChanged();
            this.notifyObservers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Etiqueta getEtiqueta(Long id){
        try {
            String query = prepareGet(id);
            Connection conexion = ConexionManager.getConexion();
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
            String query = prepareInsert(nombre);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            statement.executeUpdate(query);
            this.setChanged();
            this.notifyObservers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void renombrarEtiqueta(String nombre, Long id){
        try {
            String query = prepareRename(nombre, id);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            statement.executeUpdate(query);
            this.setChanged();
            this.notifyObservers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String prepareRename(String nombre, Long id){
        return "UPDATE etiqueta SET nombre = '" + nombre + "' WHERE ID=" + id;
    }

    private String prepareGet(Long id){
        return "SELECT * FROM etiqueta WHERE ID=" + id;
    }

    private String prepareDelete(Long id){
        return "DELETE FROM etiqueta WHERE ID=" + id;
    }


    private String prepareInsert(String nombre){
        return "INSERT INTO etiqueta" +
                " (nombre)" +
                " VALUES ('" + nombre + "')";
    }

    private String prepareList(){
        return "SELECT * FROM etiqueta";
    }

    @Override
    public List<Etiqueta> getAllItems() {
        return this.getEtiquetas();
    }
}
