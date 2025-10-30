import java.util.Random;

public class Arquero extends Soldado {
    private int numeroFlechas;
    
    public Arquero(String nombre){
        super(nombre);
        setPuntosVida(0);
    }
    @Override
    public void setPuntosVida(int puntosVida){
        Random rand = new Random();
        super.setPuntosVida(rand.nextInt(3)+1);
    }

    public void disparar(){
        if (numeroFlechas>0){
            this.numeroFlechas= numeroFlechas-1;
        }
    }
    
}
