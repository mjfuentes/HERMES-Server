package Model;
import Dao.DaoFactory;
import Enums.*;

import java.util.Date;

public class Notificacion {
    private Contenido contenido;
    private Contexto contexto;
    private Long categoria_id;
    private Date fechaEnvio;
    private Date fechaRecepcion;
    private Long nene_id;
    private Long id;
    private Long etiqueta_id;

    public  Notificacion(Long id, Contenido contenido, Contexto contexto, Long categoria_id, Date fechaEnvio, Date fechaRecepcion, Long nene_id){
        this.id = id;
        this.contenido = contenido;
        this.contexto = contexto;
        this.categoria_id = categoria_id;
        this.fechaEnvio = fechaEnvio;
        this.fechaRecepcion = fechaRecepcion;
        this.nene_id = nene_id;
    }

    public  Notificacion(Contenido contenido, Contexto contexto, Long categoria_id, Date fechaEnvio, Date fechaRecepcion, Long nene_id){
        this.contenido = contenido;
        this.contexto = contexto;
        this.categoria_id = categoria_id;
        this.fechaEnvio = fechaEnvio;
        this.fechaRecepcion = fechaRecepcion;
        this.nene_id = nene_id;
    }

    public Contenido getContenido() {return contenido;}

    public Contexto getContexto() {
        return contexto;
    }

    public Categoria getCategoria() {
        return DaoFactory.getCategoriaDao().getCategoria(this.categoria_id);
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public Nene getNene() {
        return DaoFactory.getNeneDao().getNene(this.nene_id);
    }

    public Etiqueta getEtiqueta() {
        return DaoFactory.getEtiquetaDao().getEtiqueta(etiqueta_id);
    }

    public void setEtiqueta(Long id) {
        this.etiqueta_id = id;
    }

    public Long getId() {
        return id;
    }
}
