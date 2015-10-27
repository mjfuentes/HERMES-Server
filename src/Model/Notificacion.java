package Model;
import Dao.DaoFactory;
import Enums.*;

import java.util.Date;

public class Notificacion {
    private Contenido contenido;
    private Contexto contexto;
    private Long categoria_id;
    private Date fecha_envio;
    private Date fecha_recepcion;
    private Long nene_id;
    private Long id;
    private Long etiqueta_id;

    public Notificacion(){}

    public Notificacion(Long id, Contenido contenido, Contexto contexto, Long categoria_id, Date fecha_envio, Date fecha_recepcion, Long nene_id){
        this.setId(id);
        this.setContenido(contenido);
        this.setContexto(contexto);
        this.setCategoria_id(categoria_id);
        this.setFecha_envio(fecha_envio);
        this.setFecha_recepcion(fecha_recepcion);
        this.setNene_id(nene_id);
    }

    public Notificacion(Contenido contenido, Contexto contexto, Long categoria_id, Date fecha_envio, Date fecha_recepcion, Long nene_id){
        this.setContenido(contenido);
        this.setContexto(contexto);
        this.setCategoria_id(categoria_id);
        this.setFecha_envio(fecha_envio);
        this.setFecha_recepcion(fecha_recepcion);
        this.setNene_id(nene_id);
    }

    public Contenido getContenido() {return contenido;}

    public Contexto getContexto() {
        return contexto;
    }

    public Categoria getCategoria() {
        return DaoFactory.getCategoriaDao().getCategoria(this.categoria_id);
    }

    public Date getFechaEnvio() {
        return fecha_envio;
    }

    public Date getFechaRecepcion() {
        return fecha_recepcion;
    }

    public Nene getNene() {
        return DaoFactory.getNeneDao().getNene(this.nene_id);
    }

    public Etiqueta getEtiqueta() {
        return DaoFactory.getEtiquetaDao().getEtiqueta(etiqueta_id);
    }

    public Long getId() {
        return id;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public void setContexto(Contexto contexto) {
        this.contexto = contexto;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public void setFecha_envio(Date fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public void setNene_id(Long nene_id) {
        this.nene_id = nene_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEtiqueta_id(Long etiqueta_id) {
        this.etiqueta_id = etiqueta_id;
    }
}
