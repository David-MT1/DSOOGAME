import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VentanaMenu extends JFrame {

    private final int TAMANO = 10;

    private JButton[][] grid = new JButton[TAMANO][TAMANO];
    private JLabel lblEstado;
    
    private videojuego juego;
    private Soldado[] rojos;
    private Soldado[] azules;
    
    private Soldado soldadoActual = null;
    private boolean esTurnoAzul = true; 

    public VentanaMenu() {
        configurarVentana();
        inicializarJuego();
        construirInterfaz();
        actualizarTablero(); 
        
        System.out.println("====== INICIANDO JUEGO (MODO GUI + CONSOLA) ======");
        juego.datosEquipos(); // Muestra las estadísticas iniciales en consola
        juego.Tablero(rojos, azules); // Dibuja el tablero ASCII en consola
        
        setVisible(true);
    }

    private void configurarVentana() {
        setTitle("Batalla de Soldados - GUI y Consola");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void inicializarJuego() {
        juego = new videojuego();
        rojos = juego.equipoRojo();
        azules = juego.equipoAzul();
    }

    private void construirInterfaz() {
        setJMenuBar(crearBarraMenu());
        add(crearPanelTablero(), BorderLayout.CENTER);
        add(crearPanelControles(), BorderLayout.EAST);
    }

    private JMenuBar crearBarraMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        
        JMenuItem itemNuevo = new JMenuItem("Nuevo Juego");
        itemNuevo.addActionListener(e -> { dispose(); new VentanaMenu(); });

        JMenuItem itemGuardar = new JMenuItem("Guardar Partida");
        itemGuardar.addActionListener(e -> guardarPartida());

        JMenuItem itemCargar = new JMenuItem("Cargar Partida");
        itemCargar.addActionListener(e -> cargarPartida());

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));

        menuArchivo.add(itemNuevo);
        menuArchivo.addSeparator();
        menuArchivo.add(itemGuardar);
        menuArchivo.add(itemCargar);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemSobre = new JMenuItem("Ver Estadísticas");
        itemSobre.addActionListener(e -> mostrarEstadisticas());
        menuAyuda.add(itemSobre);

        barra.add(menuArchivo);
        barra.add(menuAyuda);
        
        return barra;
    }

    private JPanel crearPanelTablero() {
        JPanel panel = new JPanel(new GridLayout(TAMANO, TAMANO));
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                JButton btn = new JButton();
                btn.setBackground(Color.WHITE);
                btn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                btn.setFocusable(false);
                btn.putClientProperty("fila", i);
                btn.putClientProperty("col", j);
                btn.addActionListener(e -> procesarClicTablero(btn));
                grid[i][j] = btn;
                panel.add(btn);
            }
        }
        return panel;
    }

    private JPanel crearPanelControles() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setPreferredSize(new Dimension(250, 0));

        lblEstado = new JLabel();
        actualizarTextoTurno();
        lblEstado.setFont(new Font("Arial", Font.BOLD, 20));
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel panelFlechas = new JPanel(new GridLayout(3, 3, 5, 5));
        panelFlechas.setMaximumSize(new Dimension(150, 150));

        JButton btnArriba = crearBotonDir("↑", "UP");
        JButton btnAbajo  = crearBotonDir("↓", "DOWN");
        JButton btnIzq    = crearBotonDir("<", "LEFT");
        JButton btnDer    = crearBotonDir(">", "RIGHT");

        panelFlechas.add(new JLabel("")); panelFlechas.add(btnArriba); panelFlechas.add(new JLabel("")); 
        panelFlechas.add(btnIzq); panelFlechas.add(new JLabel("Mover", SwingConstants.CENTER)); panelFlechas.add(btnDer);
        panelFlechas.add(new JLabel("")); panelFlechas.add(btnAbajo); panelFlechas.add(new JLabel("")); 

        panel.add(lblEstado);
        panel.add(Box.createVerticalStrut(30));
        panel.add(panelFlechas);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JButton crearBotonDir(String texto, String accion) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setFocusable(false);
        btn.addActionListener(e -> moverSoldado(accion));
        return btn;
    }



    private void procesarClicTablero(JButton btn) {
        int fila = (int) btn.getClientProperty("fila");
        int col = (int) btn.getClientProperty("col");
        Soldado soldadoEnCasilla = buscarSoldado(fila, col);

        if (soldadoEnCasilla != null) {
            boolean esSoldadoAzul = esEquipoAzul(soldadoEnCasilla);
            boolean turnoCorrecto = (esTurnoAzul && esSoldadoAzul) || (!esTurnoAzul && !esSoldadoAzul);

            if (turnoCorrecto) {
                soldadoActual = soldadoEnCasilla;
                System.out.println("Seleccionado: " + soldadoActual.getNombre());
            } else {
                JOptionPane.showMessageDialog(this, "No es el turno de este equipo.");
                return;
            }
        } else {
            soldadoActual = null;
        }
        actualizarTablero();
    }

    private void moverSoldado(String direccion) {
        if (soldadoActual == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un soldado primero.");
            return;
        }

        int f = soldadoActual.getFila();
        int c = soldadoActual.getColumna();

        switch (direccion) {
            case "UP":    f--; break;
            case "DOWN":  f++; break;
            case "LEFT":  c--; break;
            case "RIGHT": c++; break;
        }

        if (f < 0 || f >= TAMANO || c < 0 || c >= TAMANO) {
            JOptionPane.showMessageDialog(this, "Límite del tablero alcanzado.");
            return;
        }
        if (buscarSoldado(f, c) != null) {
            JOptionPane.showMessageDialog(this, "Casilla ocupada.");
            return;
        }

        soldadoActual.setFila(f);
        soldadoActual.setColumna(c);
        
        System.out.println("\n--- Movimiento Realizado ---");
        juego.Tablero(rojos, azules);

        soldadoActual = null;
        esTurnoAzul = !esTurnoAzul;
        
        actualizarTextoTurno();
        actualizarTablero();
    }

    private void mostrarEstadisticas() {
        int vivosAzul = 0;
        int sumaVidaAzul = 0;
        String masFuerteAzul = "Nadie";
        int maxVidaAzul = -1;

        for (Soldado s : azules) {
            if (s != null && s.getPuntosVida() > 0) {
                vivosAzul++;
                sumaVidaAzul += s.getPuntosVida();
                if (s.getPuntosVida() > maxVidaAzul) {
                    maxVidaAzul = s.getPuntosVida();
                    masFuerteAzul = s.getNombre();
                }
            }
        }
        double promedioAzul = (vivosAzul > 0) ? (double) sumaVidaAzul / vivosAzul : 0;

        int vivosRojo = 0;
        int sumaVidaRojo = 0;
        String masFuerteRojo = "Nadie";
        int maxVidaRojo = -1;

        for (Soldado s : rojos) {
            if (s != null && s.getPuntosVida() > 0) {
                vivosRojo++;
                sumaVidaRojo += s.getPuntosVida();
                if (s.getPuntosVida() > maxVidaRojo) {
                    maxVidaRojo = s.getPuntosVida();
                    masFuerteRojo = s.getNombre();
                }
            }
        }
        double promedioRojo = (vivosRojo > 0) ? (double) sumaVidaRojo / vivosRojo : 0;

        System.out.println("\n=======================================");
        System.out.println("      ESTADÍSTICAS DE GUERRA (LOG)     ");
        System.out.println("=======================================");
        
        System.out.println("EQUIPO AZUL");
        System.out.println(" * Soldados Vivos: " + vivosAzul);
        System.out.printf(" * Promedio de Vida: %.2f\n", promedioAzul);
        System.out.println(" * El más fuerte: " + masFuerteAzul + " (Vida: " + maxVidaAzul + ")");
        
        System.out.println("\nEQUIPO ROJO ");
        System.out.println(" * Soldados Vivos: " + vivosRojo);
        System.out.printf(" * Promedio de Vida: %.2f\n", promedioRojo);
        System.out.println(" * El más fuerte: " + masFuerteRojo + " (Vida: " + maxVidaRojo + ")");
        
        System.out.println("=======================================\n");

        // --- 4. MOSTRAR EN VENTANA (POPUP) ---
        String mensaje = "<html>" +
                "<h1>Reporte de Batalla</h1>" +
                "<hr>" +
                
                "<h3 style='color:blue;'>EQUIPO AZUL</h3>" +
                "<b>Vivos:</b> " + vivosAzul + "<br>" +
                "<b>Promedio:</b> " + String.format("%.2f", promedioAzul) + "<br>" +
                "<b>MVP (Más fuerte):</b> " + masFuerteAzul + " (" + (maxVidaAzul < 0 ? 0 : maxVidaAzul) + " HP)" +
                
                "<br><br>" +
                
                "<h3 style='color:red;'>EQUIPO ROJO</h3>" +
                "<b>Vivos:</b> " + vivosRojo + "<br>" +
                "<b>Promedio:</b> " + String.format("%.2f", promedioRojo) + "<br>" +
                "<b>MVP (Más fuerte):</b> " + masFuerteRojo + " (" + (maxVidaRojo < 0 ? 0 : maxVidaRojo) + " HP)" +
                "</html>";

        JOptionPane.showMessageDialog(this, mensaje, "Estadísticas en Tiempo Real", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                grid[i][j].setIcon(null);
                grid[i][j].setText("");
                grid[i][j].setBackground(Color.WHITE);
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        }
        for (Soldado s : rojos)  pintarCelda(s, new Color(255, 120, 120));
        for (Soldado s : azules) pintarCelda(s, new Color(120, 180, 255));
    }

    private void pintarCelda(Soldado s, Color colorFondo) {
        if (s != null && s.getPuntosVida() > 0) {
            JButton btn = grid[s.getFila()][s.getColumna()];
            btn.setBackground(colorFondo);
            if (s == soldadoActual) btn.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
            
            ImageIcon icon = cargarImagen(s);
            if (icon != null) btn.setIcon(icon);
            else btn.setText(s.getNombre().substring(0, 1));
        }
    }

    private ImageIcon cargarImagen(Soldado s) {
        String ruta = obtenerRutaImagen(s.getNombre());
        try {
            ImageIcon original = new ImageIcon(ruta);
            if (original.getIconWidth() == -1) return null;
            Image escalada = original.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            return new ImageIcon(escalada);
        } catch (Exception e) { return null; }
    }

    private String obtenerRutaImagen(String nombre) {
        if (nombre.contains(" A")) return "imagenes/arquero.png";
        if (nombre.contains(" C")) return "imagenes/caballero.png";
        if (nombre.contains(" E")) return "imagenes/espadachin.png";
        if (nombre.contains(" L")) return "imagenes/lancero.png";
        return "imagenes/vacio.png";
    }

    private void actualizarTextoTurno() {
        if (esTurnoAzul) {
            lblEstado.setText("<html><center>TURNO:<br>AZUL</center></html>");
            lblEstado.setForeground(Color.BLUE);
        } else {
            lblEstado.setText("<html><center>TURNO:<br>ROJO</center></html>");
            lblEstado.setForeground(Color.RED);
        }
    }

    // --- 5. UTILIDADES Y PERSISTENCIA ---
    private Soldado buscarSoldado(int f, int c) {
        for (Soldado s : rojos) if (s != null && s.getFila() == f && s.getColumna() == c && s.getPuntosVida() > 0) return s;
        for (Soldado s : azules) if (s != null && s.getFila() == f && s.getColumna() == c && s.getPuntosVida() > 0) return s;
        return null;
    }

    private boolean esEquipoAzul(Soldado s) {
        for (Soldado azul : azules) if (azul == s) return true;
        return false;
    }

    private void guardarPartida() {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()))) {
                out.writeObject(rojos);
                out.writeObject(azules);
                out.writeBoolean(esTurnoAzul);
                JOptionPane.showMessageDialog(this, "Guardado exitoso.");
                System.out.println("Partida guardada en: " + fc.getSelectedFile().getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
            }
        }
    }

    private void cargarPartida() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()))) {
                rojos = (Soldado[]) in.readObject();
                azules = (Soldado[]) in.readObject();
                esTurnoAzul = in.readBoolean();
                
                soldadoActual = null;
                actualizarTextoTurno();
                actualizarTablero();
                JOptionPane.showMessageDialog(this, "Carga exitosa.");
                
                // --- CONSOLA: IMPRIMIR NUEVO ESTADO ---
                System.out.println("\n--- PARTIDA CARGADA ---");
                juego.Tablero(rojos, azules);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar: " + e.getMessage());
            }
        }
    }
}