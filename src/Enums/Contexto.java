package Enums;

public enum Contexto {
    Establo(1),Terapia(2),Pista(3),Hogar(4);
    int number;

    Contexto(int number){
        this.number = number;
    }

    public int toInt(){
        return this.number;
    }

    public static Contexto fromInt(Integer num){
        for (Contexto contexto : Contexto.values()) {
            if (contexto.toInt() == num){
                return contexto;
            }
        }
        return null;
    }
}
