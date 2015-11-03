package Dao;

import Model.Contexto;

public class DaoFactory {
    private static NotificacionDAO notificacionDAO;
    private static EtiquetaDAO etiquetaDAO;
    private static CategoriaDAO categoriaDao;
    private static ContextoDAO contextoDao;
    private static NeneDAO neneDAO;

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

    public static CategoriaDAO getCategoriaDao(){
        if (categoriaDao == null){
            categoriaDao = new CategoriaDAO();
        }
        return categoriaDao;
    }

    public static ContextoDAO getContextoDao(){
        if (contextoDao == null){
            contextoDao = new ContextoDAO();
        }
        return contextoDao;
    }

    public static NeneDAO getNeneDao(){
        if (neneDAO == null){
            neneDAO = new NeneDAO();
        }
        return neneDAO;
    }
}
