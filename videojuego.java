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
            soldadoR[i] = new Soldado();
            soldadoR[i].setFila(fila);
            soldadoR[i].setColumna(columna);
            soldadoR[i].setPuntosVida(rand.nextInt(5) + 1);
            int tipe = rand.nextInt(4)+1;
            switch (tipe) {
                case 1:{
                    soldadoR[i].setNombre("R-"+(i+1)+" S");
                    break;
                }
                case 2:{
                    soldadoR[i].setNombre("R-"+(i+1)+" E");
                    break;
                }
                case 3:{
                    soldadoR[i].setNombre("R-"+(i+1)+" A");
                    break;
                }
                case 4:{
                    soldadoR[i].setNombre("R-"+(i+1)+" C");
                    break;
                }
            }
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
            soldadoA[i] = new Soldado();
            soldadoA[i].setFila(fila);
            soldadoA[i].setColumna(columna);
            soldadoA[i].setPuntosVida(rand.nextInt(5) + 1);
            int tipe = rand.nextInt(4)+1;
            switch (tipe) {
                case 1:{
                    soldadoA[i].setNombre("A-"+(i+1)+" S");
                    break;
                }
                case 2:{
                    soldadoA[i].setNombre("A-"+(i+1)+" E");
                    break;
                }
                case 3:{
                    soldadoA[i].setNombre("A-"+(i+1)+" A");
                    break;
                }
                case 4:{
                    soldadoA[i].setNombre("A-"+(i+1)+" C");
                    break;
                }
            }
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
}
