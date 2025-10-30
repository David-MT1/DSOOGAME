import java.util.*;
public class Espadachin extends Soldado{
    private int longitudEspada;

    public Espadachin(String nombre){
        super(nombre);
        setPuntosVida(0);
    }
    @Override
    public void setPuntosVida(int puntosVida){
        Random rand = new Random();
        super.setPuntosVida(rand.nextInt(2)+3);
    }

    
    public void setLongitudEspada(int longitudEspada){
        this.longitudEspada = longitudEspada;
    }
    public int getLongitudEspada(){
        return longitudEspada;
    }
    public void crearMuro(){

    }
}
