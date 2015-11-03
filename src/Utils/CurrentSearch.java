package Utils;

import Enums.Contenido;
import Model.Contexto;
import Model.Categoria;
import Model.Etiqueta;
import Model.Nene;
import Model.Notificacion;
import Service.NotificacionService;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CurrentSearch extends Observable implements Observer {
    private Contenido contenido;
    private Contexto contexto;
    private Nene nene;
    private Categoria categoria;
    private Etiqueta etiqueta;
    private String desde;
    private String hasta;
    private Boolean dateSort;

    public CurrentSearch(){
        this.desde = "";
        this.hasta = "";
    }

    public void setParams(Contenido contenido, Contexto contexto, Nene nene, Categoria categoria, Etiqueta etiqueta, String desde, String hasta){
        this.contenido = contenido;
        this.contexto = contexto;
        this.nene = nene;
        this.categoria = categoria;
        this.etiqueta = etiqueta;
        this.desde = desde;
        this.hasta = hasta;
        this.setChanged();
        this.notifyObservers();
    }

    public List<Notificacion> getResults(){
        return NotificacionService.getInstance().filtrar(contenido,contexto,nene,categoria,etiqueta,desde,hasta);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers();
    }
}
