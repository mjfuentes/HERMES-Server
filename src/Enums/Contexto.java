package Enums;

public enum Contexto {
    ESTABLO(1),TERAPIA(2),PISTA(3),HOGAR(4);
    int id;

    Contexto(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
