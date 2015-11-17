package Dao;

import Dto.NotificacionDTO;
import Model.Contexto;
import Enums.Contenido;
import Model.Categoria;
import Model.Etiqueta;
import Model.Nene;
import Model.Notificacion;
import Utils.ConexionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NotificacionDAO extends ObservableDAO<Notificacion>{
    public void guardarNotificacion(NotificacionDTO notificacion){
        try {
            Nene nene = DaoFactory.getNeneDao().getOrCreate(notificacion.getNene());
            Categoria categoria = DaoFactory.getCategoriaDao().getOrCreate(notificacion.getCategoria());
            Contexto contexto = DaoFactory.getContextoDao().getOrCreate(notificacion.getContexto());
            String query = prepareInsert(notificacion.getContenido(),contexto,categoria,nene,notificacion.getFecha_envio(),notificacion.getFecha_recepcion());
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            statement.executeUpdate(query);
            setChanged();
            notifyObservers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String prepareInsert(Contenido contenido,Contexto contexto,Categoria categoria,Nene nene,Date fechaEnvio,Date fechaRecepcion){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        return "INSERT INTO NOTIFICACION" +
                " (contenido, contexto_id, categoria_id, fecha_envio, fecha_recepcion, nino_id)" +
                " VALUES (" + contenido.toInt() + "," + contexto.getId() + "," +
                categoria.getId() + ", '" + format.format(fechaEnvio) + "','" +
               format.format(fechaRecepcion) + "'," + nene.getId() + ")";
    }

    public Notificacion getNotificacion(Long id){
        try {
            String query = prepareGet(id);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            if (resultSet.next()) {
                List<Long> etiquetas = DaoFactory.getEtiquetaDao().getEtiquetasFor(resultSet.getLong("id"));
                return new Notificacion(resultSet.getLong("id"),Contenido.fromInt(resultSet.getInt("contenido")), resultSet.getLong("contexto_id"),resultSet.getLong("categoria_id"),format.parse(resultSet.getString("fecha_envio")),format.parse(resultSet.getString("fecha_recepcion")),resultSet.getLong("nino_id"),etiquetas);
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Notificacion> getNotificacionesFiltradas(Contenido contenido,Contexto contexto, Nene nene,Categoria categoria, Etiqueta etiqueta,String desde,String hasta){
        List<Notificacion> lista = new ArrayList<>();
        try {
            String query = prepareFilteredList(contenido, contexto, nene, categoria, desde, hasta);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                List<Long> etiquetas = DaoFactory.getEtiquetaDao().getEtiquetasFor(resultSet.getLong("id"));
                Notificacion notificacion = new Notificacion(resultSet.getLong("id"),Contenido.fromInt(resultSet.getInt("contenido")), resultSet.getLong("contexto_id"),resultSet.getLong("categoria_id"),format.parse(resultSet.getString("fecha_envio")),format.parse(resultSet.getString("fecha_recepcion")),resultSet.getLong("nino_id"),etiquetas);
                if (notificacion.tieneEtiqueta(etiqueta)){
                    lista.add(notificacion);
                }
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Notificacion> getNotificaciones(){
        List<Notificacion> lista = new ArrayList<>();
        try {
            String query = prepareGetAll();
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            while (resultSet.next()) {
                List<Long> etiquetas = DaoFactory.getEtiquetaDao().getEtiquetasFor(resultSet.getLong("id"));
                lista.add(new Notificacion(resultSet.getLong("id"),Contenido.fromInt(resultSet.getInt("contenido")), resultSet.getLong("contexto_id"),resultSet.getLong("categoria_id"),format.parse(resultSet.getString("fecha_envio")),format.parse(resultSet.getString("fecha_recepcion")),resultSet.getLong("nino_id"),etiquetas));
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private String prepareFilteredList(Contenido contenido,Contexto contexto,Nene nene, Categoria categoria,String desde,String hasta){
        Boolean first = true;
        StringBuilder sb = new StringBuilder("SELECT * FROM NOTIFICACION");
        if (contenido != null || contexto != null || nene != null || categoria != null || !desde.equals("") || !hasta.equals("")) {
            sb.append(" WHERE");
            if (contenido != null) {
                sb.append(" contenido = ").append(contenido.toInt());
                first = false;
            }
            if (contexto != null) {
                if (!first){
                    sb.append(" AND ");
                }
                sb.append(" contexto_id ='").append(contexto.getId()).append("'");
                first = false;
            }
            if (nene != null) {
                if (!first){
                    sb.append(" AND ");
                }
                sb.append(" nino_id =").append(nene.getId());
                first = false;
            }
            if (categoria != null) {
                if (!first){
                    sb.append(" AND ");
                }
                sb.append(" categoria_id =").append(categoria.getId().toString());
                first = false;
            }

            if (!Objects.equals(desde, "")) {
                if (!first){
                    sb.append(" AND ");
                }
                sb.append(" fecha_envio > '").append(desde).append("'");
                first = false;
            }
            if (!Objects.equals(hasta, "")) {
                if (!first){
                    sb.append(" AND ");
                }
                sb.append(" fecha_envio < '").append(hasta).append("'");
            }
        }
        return sb.toString();
    }

    private String prepareGet(Long id){
        return "SELECT * FROM NOTIFICACION WHERE ID="+id;
    }

    private String prepareGetAll(){ return "SELECT * FROM NOTIFICACION";}

    @Override
    public List<Notificacion> getAllItems() {
        return getNotificaciones();
    }
}
