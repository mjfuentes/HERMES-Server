package Service;

import Dao.DaoFactory;
import Enums.Contexto;
import Enums.Contenido;
import Model.Categoria;
import Model.Etiqueta;
import Model.Nene;
import Model.Notificacion;

import java.util.Date;
import java.util.List;

public class NotificacionService {
    private static NotificacionService instance;

    private NotificacionService(){
    }

    public static NotificacionService getInstance(){
        if (instance == null){
            instance = new NotificacionService();
        }
        return instance;
    }

    public List<Notificacion> filtrar(Contenido contenido, Contexto contexto, Nene nene, Categoria categoria, Etiqueta etiqueta, String desde, String hasta){
        return DaoFactory.getNotificacionDAO().getNotificacionesFiltradas(contenido, contexto,nene,categoria,etiqueta,desde,hasta);
    }
}
