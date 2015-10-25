package Model;
import Enum.*;

import java.util.Date;

public class Notificacion {
    private String texto;
    private Contexto contexto;
    private Categoria categoria;
    private Date fechaEnvio;
    private Date fechaRecepcion;
    private Niño niño;

    public String getTexto() {
        return texto;
    }

    public Contexto getContexto() {
        return contexto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public Niño getNiño() {
        return niño;
    }
}
