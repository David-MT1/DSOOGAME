public class Lancero extends Soldado{
    private int longitudLanza;
    public Lancero(String tipo){
        super(tipo);
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
