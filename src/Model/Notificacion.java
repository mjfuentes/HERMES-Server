package Model;
import Dao.DaoFactory;
import Enums.*;

import java.util.Date;
import java.util.List;


public class Notificacion {
    private Contenido contenido;
    private Long contexto_id;
    private Long categoria_id;
    private Date fecha_envio;
    private Date fecha_recepcion;
    private Long nene_id;
    private Long id;
    private List<Long> etiquetas_id;

    public Notificacion(){}

    public Notificacion(Long id, Contenido contenido, Long contexto_id, Long categoria_id, Date fecha_envio, Date fecha_recepcion, Long nene_id, List<Long> etiquetas_id){
        this.setId(id);
        this.setContenido(contenido);
        this.setContexto_id(contexto_id);
        this.setCategoria_id(categoria_id);
        this.setFecha_envio(fecha_envio);
        this.setFecha_recepcion(fecha_recepcion);
        this.setNene_id(nene_id);
        this.setEtiquetas_id(etiquetas_id);
    }

    public Notificacion(Contenido contenido, Long contexto_id, Long categoria_id, Date fecha_envio, Long nene_id){
        this.setContenido(contenido);
        this.setContexto_id(contexto_id);
        this.setCategoria_id(categoria_id);
        this.setFecha_envio(fecha_envio);
        this.setNene_id(nene_id);
    }

    public Contenido getContenido() {return contenido;}

    public Contexto getContexto() {
        return DaoFactory.getContextoDao().getContexto(this.contexto_id);
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

    public List<Etiqueta> getEtiquetas() {
        return DaoFactory.getEtiquetaDao().getEtiquetas(etiquetas_id);
    }

    public Long getId() {
        return id;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public void setContexto_id(Long contexto_id) {
        this.contexto_id = contexto_id;
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

    public void setEtiquetas_id(List<Long> etiquetas_id) {
        this.etiquetas_id = etiquetas_id;
    }

    public Boolean tieneEtiqueta(Etiqueta etiqueta){
        if (etiqueta == null){ return true;}
        for (Etiqueta e: getEtiquetas()){
            if (e.getId().equals(etiqueta.getId())){
                return true;
            }
        }
        return false;
    }
}
