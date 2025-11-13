import java.util.*;
public class Soldado {

    private String nombre;
    private int puntosVida;
    private int fila;
    private int columna;
    private int nivelAtaque;
    private int nivelDefensa;
    private int vidaActual;
    private int velocidad;
    private String actitud;
    private Boolean vive;

    public Soldado(String nombre){
        this.nombre = nombre;

    }


    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }

    public void setPuntosVida(int puntosVida){
        this.puntosVida = puntosVida;
    }
    public int getPuntosVida(){
        return puntosVida;
    }

    public void setFila(int fila){
        this.fila = fila;
    }
    public int getFila(){
        return fila;
    }

    public void setColumna(int columna){
        this.columna = columna;
    }
    public int getColumna(){
        return columna;
    }

    public void setNivelAtaque(int nivelAtaque){
        this.nivelAtaque = nivelAtaque;
    }
    public int getNivelAtaque(){
        return nivelAtaque;
    }

    public void setNivelDefensa(int nivelDefensa){
        this.nivelDefensa = nivelDefensa;
    }
    public int getNivelDefensa(){
        return nivelDefensa;
    }

    public void setVidaActual(int vidaActual){
        this.vidaActual = vidaActual;
    }
    public int getVidaActual(){
        return vidaActual;
    }

    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
    public int getVelocidad(){
        return velocidad;
    }

    public void setActitud(String actitud){
        this.actitud = actitud;
    }
    public String getActitud(){
        return actitud;
    }

    public void setVive(Boolean vive){
        this.vive = vive;
    }
    public Boolean getVive(){
        return vive;
    }


    public String toString(){
        return ("Nombre del soldado es: "+getNombre()+" Puntos de vida: "+getPuntosVida()+" Posicion es:("+getFila()+";"+getColumna()+")");
    }

    //METODOS

    public void atacar() {
    
}

public void defender() {

}

    public void moverse(int fila, int columna){
        Scanner sc = new Scanner(System.in);
        System.out.println("elige tu movimiento: \nArriba(A)\nAbajo(R)\nDerecha(D)\nIzquierda(I)");
        String opcion = sc.next();
        switch(opcion){
            case("A"):{
                if(fila==0){
                    System.out.println("No puede moverse para arriba");
                } else {
                    fila=fila-1;
                }break;
            }
            case("R"):{
                if(fila==9){ 
                    System.out.println("No puede moverse para abajo");
                } else {
                    fila=fila+1;
                } break;
            } case("D"):{
                if(columna==9){ System.out.println("No puede moverse para la derecha");
                } else {
                    columna=columna+1;
                } break;
            } case("I"):{
                if(columna==0){ System.out.println("No puede moverse para la izquierda");
                } else {
                    columna=columna-1;
                } break;
            }
        }
    this.setFila(fila);
    this.setColumna(columna);
    }

    public void serAtacado(){
        if(vive==true){
            vidaActual=vidaActual-1;
        }
    }

    public void huir(){
        Scanner sc = new Scanner(System.in);
        System.out.println("A donde quieres huir!!!: \nArriba(A)\nAbajo(R)\nDerecha(D)\nIzquierda(I)");
        String opcion = sc.next();
        switch(opcion){
            case("A"):{
                if(fila==0){
                    System.out.println("No puede moverse para arriba");
                } else {
                    fila=fila-1;
                }
                break;
            }
            case("R"):{
                if(fila==9){
                    System.out.println("No puede moverse para abajo");
                } else {
                    fila=fila+1;
                }
                break;
            }
            case("D"):{
                if(columna==9){
                    System.out.println("No puede moverse para la derecha");
                } else {
                    columna=columna+1;
                }
                break;
            }
            case("I"):{
                if(columna==0){
                    System.out.println("No puede moverse para la izquierda");
                } else {
                    columna=columna-1;
                }
                break;
            }
        }
        this.setFila(fila);
        this.setColumna(columna);
    }

    public boolean estaVivo() {
        return this.puntosVida > 0;
    }

    public void morir() {
        this.setPuntosVida(0);
        this.setFila(-1);
        this.setColumna(-1);
        this.setNombre("øøø\t");
}
}

