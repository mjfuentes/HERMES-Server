package Model;

public enum Contenido {
    ENTUSIASMADO(1), ALEGRE(2), MOLESTO(3);

    int number;
    Contenido(int number){
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
