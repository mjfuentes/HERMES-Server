package Model;

public class Categoria {
    private String nombre;
    private Long id;

    public Categoria(String nombre, Long id){
        this.nombre = nombre;
        this.id = id;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getId() {
        return id;
    }
}
