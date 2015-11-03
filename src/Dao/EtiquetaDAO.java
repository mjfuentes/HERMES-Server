package Dao;

import Model.Etiqueta;
import Model.Notificacion;
import Utils.ConexionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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

    public List<Etiqueta> getEtiquetas(List<Long> ids){
        List<Etiqueta> lista = new ArrayList<>();
        try {
            for (Long id:ids) {
                String query = prepareGet(id);
                Connection conexion = ConexionManager.getConexion();
                Statement statement = conexion.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    lista.add(new Etiqueta(resultSet.getString("nombre"), resultSet.getLong("id")));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
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

    public void asignarEtiqueta(List<Notificacion> notificaciones, Etiqueta etiqueta){
        try {
            Connection conexion = ConexionManager.getConexion();
            for(Iterator<Notificacion> i = notificaciones.iterator(); i.hasNext(); ) {
                Notificacion notificacion = i.next();
                if (!notificacion.tieneEtiqueta(etiqueta)){
                    String query = prepareAsignar(notificacion.getId(), etiqueta.getId());
                    Statement statement = conexion.createStatement();
                    statement.executeUpdate(query);
                } else {
                    String query = prepareDesasignar(notificacion.getId(), etiqueta.getId());
                    Statement statement = conexion.createStatement();
                    statement.executeUpdate(query);
                }
            }
            this.setChanged();
            this.notifyObservers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Long> getEtiquetasFor(Long notificacion_id){
        List<Long> lista = new ArrayList<>();
        try {
            String query = prepareGetEtiquetasFor(notificacion_id);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lista.add(resultSet.getLong("etiqueta_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private String prepareRename(String nombre, Long id){
        return "UPDATE etiqueta SET nombre = '" + nombre + "' WHERE ID=" + id;
    }

    private String prepareGet(Long id){
        return "SELECT * FROM etiquetA WHERE ID=" + id;
    }

    private String prepareDelete(Long id){
        return "DELETE FROM etiqueta WHERE ID=" + id;
    }


    private String prepareInsert(String nombre){
        return "INSERT INTO etiqueta" +
                " (nombre)" +
                " VALUES ('" + nombre + "')";
    }

    private String prepareGetEtiquetasFor(Long notificacion_id){
        return "SELECT * FROM notificacion_etiqueta" +
                " WHERE notificacion_id = " + notificacion_id;
    }

    private String prepareAsignar(Long notificacion_id, Long etiqueta_id){
        return "INSERT INTO notificacion_etiqueta VALUES (" +
                notificacion_id + "," + etiqueta_id + ")";
    }

    private String prepareDesasignar(Long notificacion_id, Long etiqueta_id){
        return "DELETE FROM notificacion_etiqueta" +
                " WHERE notificacion_id = " + notificacion_id +
                " AND etiqueta_id = " + etiqueta_id;
    }

    private String prepareList(){
        return "SELECT * FROM etiqueta";
    }

    @Override
    public List<Etiqueta> getAllItems() {
        return this.getEtiquetas();
    }
}
