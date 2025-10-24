public class Arquero extends Soldado {
    private int numeroFlechas;
    /* 
    public Arquero(String tipo){
        super(tipo);
    }*/

    public void disparar(){
        if (numeroFlechas>0){
            this.numeroFlechas= numeroFlechas-1;
        }
    }
    
}
