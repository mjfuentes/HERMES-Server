package Service;

import Dao.DaoFactory;
import Model.Etiqueta;

import java.util.List;

public class EtiquetaService {

    public void crearEtiqueta(String nombre){
        DaoFactory.getEtiquetaDao().guardarEtiqueta(nombre);
    }

    public void eliminarEtiqueta(Long id){
        DaoFactory.getEtiquetaDao().eliminarEtiqueta(id);
    }

    public List<Etiqueta> getEtiquetas(){
        return DaoFactory.getEtiquetaDao().getEtiquetas();
    }
}
