package Enums;

public enum Contenido {
    Entusiasmado(1), Alegre(2), Molesto(3);

    int number;
    Contenido(int number){
        this.number = number;
    }

    public int toInt(){
        return this.number;
    }

    public static Contenido fromInt(Integer num){
        for (Contenido contenido : Contenido.values()) {
            if (contenido.toInt() == num){
                return contenido;
            }
        }
        return null;
    }
}
