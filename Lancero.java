import java.util.Random;

public class Lancero extends Soldado{
    private int longitudLanza;
    public Lancero(String nombre){
        super(nombre);
        setPuntosVida(0);
    }
    @Override
    public void setPuntosVida(int puntosVida){
        Random rand = new Random();
        super.setPuntosVida(rand.nextInt(2)+1);
    }

    public void getlongitudLanza(int longitudLanza){
        this.longitudLanza = longitudLanza;
    }
    public int longitudLanza() {
        return longitudLanza;
    }

    public void schiltrom() {
        super.setNivelDefensa(super.getNivelDefensa()+1);

    }
    
}
