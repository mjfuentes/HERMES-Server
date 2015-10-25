package Service;

import Dao.DaoFactory;
import Enums.Contexto;
import Model.Contenido;
import Model.Notificacion;

import java.util.Date;
import java.util.List;

public class NotificacionService {
    public List<Notificacion> filtrar(String contenido, String contexto, Long niño_id, Date desde, Date hasta, Long etiqueta_id, Long categoria_id){
        return DaoFactory.getNotificacionDAO().getNotificacionesFiltradas(Contenido.valueOf(contenido), Contexto.valueOf(contexto),niño_id,categoria_id,etiqueta_id,desde,hasta);
    }
}
