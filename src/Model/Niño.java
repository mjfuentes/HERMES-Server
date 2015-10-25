package Model;

public class Niño {
    private String nombre;
    private Long id;

    public Niño(String nombre, Long id){
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getId() {
        return id;
    }
}
