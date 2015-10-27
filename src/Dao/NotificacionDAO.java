package Dao;

import Enums.Contexto;
import Enums.Contenido;
import Model.Categoria;
import Model.Etiqueta;
import Model.Nene;
import Model.Notificacion;
import Utils.ConexionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificacionDAO extends ObservableDAO<Notificacion>{
    public void guardarNotificacion(Notificacion notificacion){
        try {
            String query = prepareInsert(notificacion);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String prepareInsert(Notificacion notificacion){
        return "INSERT INTO NOTIFICACION" +
                " (contenido, contexto, categoria_id, fecha_envio, fecha_recepcion, nino_id)" +
                " VALUES (" + notificacion.getContenido().toInt() + "," + notificacion.getContexto().toInt() + "," +
                notificacion.getCategoria().getId() + ", '" + notificacion.getFechaEnvio() + "','" +
                notificacion.getFechaRecepcion() + "'," + notificacion.getNene().getId() + ")";
    }

    public Notificacion getNotificacion(Long id){
        try {
            String query = prepareGet(id);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Notificacion(resultSet.getLong("id"),Contenido.valueOf(resultSet.getString("contenido")), Contexto.valueOf(String.valueOf(resultSet.getInt("contexto"))),resultSet.getLong("categoria_id"),resultSet.getDate("fecha_envio"),resultSet.getDate("fecha_recepcion"),resultSet.getLong("nino_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Notificacion> getNotificacionesFiltradas(Contenido contenido,Contexto contexto, Nene nene,Categoria categoria, Etiqueta etiqueta,String desde,String hasta){
        List<Notificacion> lista = new ArrayList<>();
        try {
            String query = prepareFilteredList(contenido, contexto, nene, categoria, etiqueta, desde, hasta);
            Connection conexion = ConexionManager.getConexion();
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lista.add(new Notificacion(resultSet.getLong("id"),Contenido.valueOf(resultSet.getString("contenido")), Contexto.valueOf(String.valueOf(resultSet.getInt("contexto"))),resultSet.getLong("categoria_id"),resultSet.getDate("fecha_envio"),resultSet.getDate("fecha_recepcion"),resultSet.getLong("nino_id")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private String prepareFilteredList(Contenido contenido,Contexto contexto,Nene nene, Categoria categoria, Etiqueta etiqueta,String desde,String hasta){
        Boolean first = true;
        StringBuilder sb = new StringBuilder("SELECT * FROM NOTIFICACION");
        if (contenido != null || contexto != null || nene != null || categoria != null || etiqueta != null || desde != null || hasta != null) {
            sb.append(" WHERE");
            if (contenido != null) {
                sb.append(" contenido = '").append(contenido.toInt()).append("'");
                first = false;
            }
            if (contexto != null) {
                if (!first){
                    sb.append(" AND ");
                }
                sb.append(" contexto='").append(contexto.toInt()).append("'");
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
            if (etiqueta != null) {
                if (!first){
                    sb.append(" AND ");
                }
                sb.append(" etiqueta_id =").append(etiqueta.getId().toString());
                first = false;
            }
            if (!Objects.equals(desde, "")) {
                if (!first){
                    sb.append(" AND ");
                }
                sb.append(" fecha_envio > '").append(desde.toString()).append("'");
                first = false;
            }
            if (!Objects.equals(hasta, "")) {
                if (!first){
                    sb.append(" AND ");
                }
                sb.append(" fecha_envio < '").append(hasta.toString()).append("'");
            }
        }
        return sb.toString();
    }

    private String prepareGet(Long id){
        return "SELECT * FROM NOTIFICACION WHERE ID="+id;
    }

    @Override
    public List<Notificacion> getAllItems() {
        return null;
    }
}
