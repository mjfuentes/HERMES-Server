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
}
