package Service;

import Dao.DaoFactory;
import Model.Etiqueta;

public class EtiquetaService {

    public void crearEtiqueta(String nombre){
        Etiqueta nueva = new Etiqueta(nombre);
        DaoFactory.getEtiquetaDao().guardarEtiqueta(nueva);
    }

    public void eliminarEtiqueta(String nombre){
        DaoFactory.getEtiquetaDao().eliminarEtiqueta(nombre);
    }

}
