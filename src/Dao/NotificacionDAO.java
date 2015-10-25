package Dao;

import Model.Notificacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NotificacionDAO {
    public void guardarNotificacion(Notificacion notificacion){
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String query = prepareInsert(notificacion);
            Connection conexion = DriverManager.getConnection("jdbc:odbc:laboratorio", "user", "password");
            Statement statement = conexion.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String prepareInsert(Notificacion notificacion){
        return "INSERT INTO NOTIFICACION" +
                " (texto, contexto_id, categoria_id, fecha_envio, fecha_recepcion, nino_id" +
                " VALUES (" + notificacion.getTexto() + "," + notificacion.getContexto() + "," +
                notificacion.getCategoria() + "," + notificacion.getFechaEnvio() + "," +
                notificacion.getFechaRecepcion() + "," + notificacion.getNiño() + ")";
    }
}
