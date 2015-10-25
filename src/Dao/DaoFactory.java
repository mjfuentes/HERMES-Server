package Dao;

public class DaoFactory {
    private static NotificacionDAO notificacionDAO;
    private static EtiquetaDAO etiquetaDAO;

    public static NotificacionDAO getNotificacionDAO(){
        if (notificacionDAO == null){
            notificacionDAO = new NotificacionDAO();
        }
        return notificacionDAO;
    }

    public static EtiquetaDAO getEtiquetaDao(){
        if (etiquetaDAO == null){
            etiquetaDAO = new EtiquetaDAO();
        }
        return etiquetaDAO;
    }
}
