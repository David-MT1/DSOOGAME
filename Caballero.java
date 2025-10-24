import java.util.*;;
public class Caballero extends Soldado {
    private String arma="espada";
    private boolean montar = true;
    /*
    public Caballero(String tipo){
        super(tipo);
    }
    */
    public void setArma(String arma){
        this.arma=arma;
    }
    public String getArma(){
        return arma;
    }
    public void setMontar(boolean montar){
        this.montar=montar;
    }
    public boolean getMontar(){
        return montar;
    }


    public void alternarArma(){
        Scanner sc = new Scanner(System.in);
            System.out.println("deseas cambiar de arma? (espada/lanza)\n+ahora tienes"+getArma());
            String cambio = sc.next();
            if (cambio.equals("espada")){
                setArma("lanza");
            }else if(cambio.equals("lanza")){
                setArma("lanza");
            }
    
    }
    public void desmontar(){
        if(montar==true){
            super.defender();
            if(getArma().equals("espada")){
                setArma("espada");
            }else if (getArma().equals("lanza")){
                setArma("espada"); 
            }
        }
    }

    public void embestir(){
        if(montar==true){
            super.atacar();
            super.atacar();
        }else{
            super.atacar();
            super.atacar();
            super.atacar();
        }
    }
    public void montar(){
        if(montar==false){
            embestir();
            if(getArma().equals("espada")){
                setArma("lanza");
            }else if (getArma().equals("lanza")){
                setArma("espada"); 
            }
        }
    }
}
