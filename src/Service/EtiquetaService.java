package Service;

import Dao.DaoFactory;
import Model.Etiqueta;
import Model.Notificacion;

import java.util.List;

public class EtiquetaService {
    private static EtiquetaService instance;
    private EtiquetaService(){
        super();
    }

    public static EtiquetaService getInstance() {
        if (instance == null){
            instance = new EtiquetaService();
        }
        return instance;
    }

    public void asignarEtiqueta(List<Notificacion> notificaciones, Etiqueta etiqueta){
        DaoFactory.getEtiquetaDao().asignarEtiqueta(notificaciones, etiqueta);
    }

    public void crearEtiqueta(String nombre){
        DaoFactory.getEtiquetaDao().guardarEtiqueta(nombre);
    }

    public void eliminarEtiqueta(Long id){
        DaoFactory.getEtiquetaDao().eliminarEtiqueta(id);
    }

    public void renombrarEtiqueta(Long id, String nuevoNombre){ DaoFactory.getEtiquetaDao().renombrarEtiqueta(nuevoNombre,id);}

    public List<Etiqueta> getEtiquetas(){
        return DaoFactory.getEtiquetaDao().getEtiquetas();
    }
}
