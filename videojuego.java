import java.util.*;
public class videojuego {
        Random rand = new Random();
        Scanner sc = new Scanner (System.in);
        int cantidad = rand.nextInt(10) + 1;
        Soldado[] soldadoR = new Soldado[cantidad];
        int cantidad1 = rand.nextInt(10) + 1;
        Soldado[] soldadoA = new Soldado[cantidad1];

    public Soldado[] equipoRojo() {
        boolean[][] ocupado = new boolean[10][10];
        for (int i = 0; i < cantidad; i++) {
            int fila, columna;
            do {
                fila = rand.nextInt(10);
                columna = rand.nextInt(10);
            } while (ocupado[fila][columna]);
            ocupado[fila][columna] = true;

            int tipe = rand.nextInt(4)+1;
            switch (tipe) {
                case 1:{
                    soldadoR[i] = new Lancero("R-"+(i+1)+" L");
                    break;
                }
                case 2:{
                    soldadoR[i] = new Espadachin("R-"+(i+1)+" E");
                    break;
                }
                case 3:{
                    soldadoR[i] = new Arquero("R-"+(i+1)+" A");
                    break;
                }
                case 4:{
                    soldadoR[i] = new Caballero("R-"+(i+1)+" C");
                    break;
                }
            }
            soldadoR[i].setFila(fila);
            soldadoR[i].setColumna(columna);
            
        }
        return soldadoR;
    }

    public Soldado[] equipoAzul() {
        boolean[][] ocupado = new boolean[10][10];
        for (int i = 0; i < cantidad1; i++) {
            int fila, columna;
            do {
                fila = rand.nextInt(10);
                columna = rand.nextInt(10);
            } while (ocupado[fila][columna]);
            ocupado[fila][columna] = true;
            int tipe = rand.nextInt(4)+1;
            switch (tipe) {
                case 1:{
                    soldadoA[i] = new Lancero("A-"+(i+1)+" L");
                    break;
                }
                case 2:{
                    soldadoA[i] = new Espadachin("A-"+(i+1)+" E");
                    break;
                }
                case 3:{
                    soldadoA[i] = new Arquero("A-"+(i+1)+" A");
                    break;
                }
                case 4:{
                    soldadoA[i] = new Caballero("A-"+(i+1)+" C");
                    break;
                }
            }
            soldadoA[i].setFila(fila);
            soldadoA[i].setColumna(columna);
            
        }
        return soldadoA;
    }

    public void Tablero(Soldado[] R, Soldado[] A) {
        int filas = 10;
        int columnas = 10;
        String[][] tablero = new String[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = "øøø\t";
            }
        }

        for (Soldado s : R) {
            if (s.getFila() >= 0 && s.getColumna() >= 0 && s.getFila() < 10 && s.getColumna() < 10) {
                tablero[s.getFila()][s.getColumna()] = s.getNombre() + ("\t");
            }
        }
        for (Soldado s : A) {
            if (s.getFila() >= 0 && s.getColumna() >= 0 && s.getFila() < 10 && s.getColumna() < 10) {
                tablero[s.getFila()][s.getColumna()] = s.getNombre() + ("\t");
            }
        }

        System.out.println("\n \t\t\tTABLERO DE BATALLA");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void datosEquipos(){
        System.out.println("En este juego hay 2 equipos\nEncontramos tipos de soldados en cada ejercito:\nSoldado(s)\nArquero(A)\nEspasachin(E)\nCaballero(C)\n\n");

        System.out.println("EL EQUIPO AZUL ESTA CONFORMADO");
        for (int i = 0; i < soldadoA.length; i++) {
            System.out.println(soldadoA[i].toString());         
        }
        System.out.println("EL EQUIPO ROJO ESTA CONFORMADO POR:");
        for (int i = 0; i < soldadoR.length; i++) {
            System.out.println(soldadoR[i].toString());
        }
        soldadoMayorVida(soldadoA, soldadoR);
        promedioVida(soldadoA, soldadoR);

        System.out.println("Los soldados ordenados son: \n");
            Soldado[] rankingAzul = rankingPoder(soldadoA);
            Soldado[] rankingRojo = rankingPoder(soldadoR);

            System.out.println("\nLos soldados ordenados por puntos de vida son: \n");

            System.out.println("EL EQUIPO AZUL (ordenado):");
            for (int i = 0; i < rankingAzul.length; i++) {
                System.out.println(rankingAzul[i].toString());
            }

            System.out.println("EL EQUIPO ROJO (ordenado):");
            for (int i = 0; i < rankingRojo.length; i++) {
                System.out.println(rankingRojo[i].toString());
            }
        if(soldadoA.length>soldadoR.length){
            System.out.println("EL EQUIPO AZUL GANA POR MAYORIA EN SU EJERCITO!!!!");
            System.out.println("------DEMUESTRA LO CONTRARIO COMENZANDO EL JUEGO------");
        }else if(soldadoA.length<soldadoR.length){
            System.out.println("EL EQUIPO ROJO GANA POR MAYORIA EN SU EJERCITO!!!!");
            System.out.println("------DEMUESTRA LO CONTRARIO COMENZANDO EL JUEGO------");
        }else{
            System.out.println("TIENEN MISMA CANTIDAD DE SOLDADOS!!!!");
            System.out.println("------COMIENZA EL JUEGO PARA DESEMPATAR------");

        }

    }

    public void iniciarJuego() {
        System.out.println("¿Desea comenzar el juego? (s/n)");
        String opcion = sc.next();

        if (opcion.equalsIgnoreCase("s")) {
            System.out.println("Comenzará el equipo Azul");

            while (true) {
                System.out.println("\n--- Turno del equipo Azul ---");
                System.out.print("Elige el soldado que quieres mover (Ejemplo: A-1): ");
                String nombre = sc.next();
                int codigo = Integer.parseInt(nombre.substring(2)) - 1;

                if (codigo < 0 || codigo >= soldadoA.length) {
                    System.out.println("¡Soldado inválido!");
                    continue;
                }

                soldadoA[codigo].moverse(soldadoA[codigo].getFila(), soldadoA[codigo].getColumna());
                pelea(soldadoA, soldadoR, codigo);
                Tablero(soldadoA, soldadoR);

                System.out.println("\n--- Turno del equipo Rojo ---");
                System.out.print("Elige el soldado que quieres mover (Ejemplo: R-1): ");
                String nombreR = sc.next();
                int codigoR = Integer.parseInt(nombreR.substring(2)) - 1;

                if (codigoR < 0 || codigoR >= soldadoR.length) {
                    System.out.println("Soldado inválido!");
                    continue;
                }

                soldadoR[codigoR].moverse(soldadoR[codigoR].getFila(), soldadoR[codigoR].getColumna());
                pelea(soldadoR, soldadoA, codigoR);
                Tablero(soldadoA, soldadoR);
            }

        } else if (opcion.equalsIgnoreCase("n")) {
            System.out.println("El juego ha finalizado. ¡Vuelva pronto!");
        } else {
            System.out.println("OPCIÓN INCORRECTA. Inicie el programa nuevamente.");
        }
    }

    public void pelea(Soldado[] a, Soldado[] r, int nroSoldado) {
        for (int i = 0; i < r.length; i++) {
            Random rand = new Random();
            double aleatorio = rand.nextInt(100)+1;
            double vidaSoldado = a[nroSoldado].getPuntosVida();
            double vidaEnemigo = r[i].getPuntosVida();
            double probabilidadSoldado = vidaSoldado/(vidaEnemigo+vidaSoldado)*100;

            double problabilidadEnemigo = vidaEnemigo/(vidaEnemigo+vidaSoldado)*100;

            if (a[nroSoldado].getFila() == r[i].getFila() && a[nroSoldado].getColumna() == r[i].getColumna()) {
                System.out.println("EL NUMERO ALEATORIO ES "+aleatorio);
                System.out.println("LA PROBABLIDIDAD DE TU SOLDADO ES DE "+ probabilidadSoldado+" \nLA PROBABLIDIDAD DEL ENEMIGO ES "+problabilidadEnemigo);
                if(probabilidadSoldado==problabilidadEnemigo) {
                    System.out.println("¡Ambos mueren en empate!");
                    a[nroSoldado].morir();
                    r[i].morir();
                    break;
                }else if (probabilidadSoldado > aleatorio) {
                    System.out.println("TU PROBABLIDIDAD ES DE "+probabilidadSoldado+" EL NUMERO ALEATORIO "+aleatorio+" SE ENCUENTRA EN TU PROBABLIDIDAD");
                    System.out.println("GANASTE!! "+r[i].getNombre() + " HA MUERTO!");
                    r[i].morir();
                } else if (probabilidadSoldado < aleatorio) {
                    System.out.println("TU PROBABLIDIDAD ES DE "+probabilidadSoldado+" EL NUMERO ALEATORIO "+aleatorio+" NO SE ENCUENTRA EN TU PROBABLIDIDAD");
                    System.out.println("PERDISTE!!"+a[nroSoldado].getNombre() + " HA MUERTO!");
                    a[nroSoldado].morir();
                } 
            }
        }
        
    }
    public void soldadoMayorVida(Soldado[] soldadoA, Soldado[] soldadoR){
        int mayor = soldadoA[0].getPuntosVida();
        for(int i = 1; i<soldadoA.length;i++) {
            if(mayor<soldadoA[i].getPuntosVida()){
                mayor=soldadoA[i].getPuntosVida();
            }
        }
        for (int i = 0; i < soldadoA.length; i++) {
            if(mayor==soldadoA[i].getPuntosVida()){
                System.out.println("el soldado con mayor vida es "+soldadoA[i].getNombre());
                break;
            }
        }
        int mayorR = soldadoR[0].getPuntosVida();
        for(int i = 1; i<soldadoR.length;i++) {
            if(mayorR<soldadoR[i].getPuntosVida()){
                mayorR=soldadoR[i].getPuntosVida();
            }
        }
        for (int i = 0; i < soldadoR.length; i++) {
            if(mayorR==soldadoR[i].getPuntosVida()){
                System.out.println("el soldado con mayor vida es "+soldadoR[i].getNombre());
                break;
            }
        }
    }

    public void promedioVida(Soldado[] soldadoA, Soldado[] soldadoR){
        double sumaA = 0;
        for (int i = 0; i < soldadoA.length; i++) {
            sumaA = soldadoA[i].getPuntosVida()+sumaA;
        }
        double promedioA = sumaA/soldadoA.length;
        System.out.println("EL PROMEDIO DE VIDA DEL EQUIPO AZUL ES: "+promedioA);

        double sumaR = 0;
        for (int i = 0; i < soldadoR.length; i++) {
            sumaR = soldadoR[i].getPuntosVida()+sumaR;
        }
        double promedioR = sumaR/soldadoR.length;
        System.out.println("EL PROMEDIO DE VIDA DEL EQUIPO ROJO ES: "+promedioR);

    }

    public Soldado[] rankingPoder(Soldado[] soldadoA) {
        int n = soldadoA.length;
        boolean cambio;
        for (int i = 0; i < n - 1; i++) {
            cambio = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (soldadoA[j].getPuntosVida() < soldadoA[j + 1].getPuntosVida()) {
                    Soldado temp = soldadoA[j];
                    soldadoA[j] = soldadoA[j + 1];
                    soldadoA[j + 1] = temp;
                    cambio = true;
                }
            }
            if (!cambio) {
                break;
            }
        }
        return soldadoA;
    }
}
