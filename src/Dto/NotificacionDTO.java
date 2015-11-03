package Dto;

import Enums.Contenido;

import java.util.Date;

public class NotificacionDTO {

    private Contenido contenido;
    private String contexto;
    private String categoria;
    private Date fecha_envio;
    private Date fecha_recepcion;
    private String nene;

    public NotificacionDTO(){};

    public NotificacionDTO(Contenido contenido, String contexto, String categoria, Date fecha_envio, String nene){
        this.contenido = contenido;
        this.contexto = contexto;
        this.categoria = categoria;
        this.fecha_envio = fecha_envio;
        this.nene = nene;
    }

    public void setFecha_recepcion(Date fecha_recepcion){
        this.fecha_recepcion = fecha_recepcion;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public String getContexto() {
        return contexto;
    }

    public String getCategoria() {
        return categoria;
    }

    public Date getFecha_envio() {
        return fecha_envio;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public String getNene() {
        return nene;
    }
}
