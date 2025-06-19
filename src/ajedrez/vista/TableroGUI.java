package ajedrez.vista;

import ajedrez.modelo.GestorPartida;
import ajedrez.modelo.IA;
import ajedrez.modelo.Pieza;
import ajedrez.modelo.Tablero;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TableroGUI extends JFrame {
    private JButton[][] botones = new JButton[8][8];
    private GestorPartida gestor = new GestorPartida();
    private Tablero tablero = gestor.getTablero();
    private int filaSeleccionada = -1, colSeleccionada = -1;
    private Map<String, ImageIcon> iconos = new HashMap<>();

    public TableroGUI() {
        setTitle("Ajedrez");
        setSize(640, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cargarIconos();

        JPanel panelTablero = new JPanel(new GridLayout(8, 8));
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(80, 80));
                boton.setMargin(new Insets(0, 0, 0, 0));
                boton.setContentAreaFilled(true);
                boton.setOpaque(true);
                boton.setBorderPainted(false);
                int f = fila, c = col;
                boton.addActionListener(e -> manejarClick(f, c));
                botones[fila][col] = boton;
                panelTablero.add(boton);
            }
        }

        JPanel panelMenu = new JPanel();
        JButton btnNuevo = new JButton("Iniciar Partida");
        JButton btnCargar = new JButton("Cargar Partida");
        JButton btnGuardar = new JButton("Guardar Partida");
        JButton btnSalir = new JButton("Finalizar Partida");

        btnNuevo.addActionListener(e -> reiniciar());
        btnCargar.addActionListener(e -> cargar());
        btnGuardar.addActionListener(e -> guardar());
        btnSalir.addActionListener(e -> System.exit(0));

        panelMenu.add(btnNuevo);
        panelMenu.add(btnCargar);
        panelMenu.add(btnGuardar);
        panelMenu.add(btnSalir);

        add(panelMenu, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);

        actualizarTablero();
    }

    private void manejarClick(int fila, int col) {
        Pieza p = tablero.getPieza(fila, col);

        if (filaSeleccionada == -1 && p != null && p.esBlanca()) {
            filaSeleccionada = fila;
            colSeleccionada = col;
        } else if (filaSeleccionada != -1) {
            Pieza seleccionada = tablero.getPieza(filaSeleccionada, colSeleccionada);
            if (seleccionada != null) {
                for (int[] mov : seleccionada.movimientosValidos(tablero)) {
                    if (mov[0] == fila && mov[1] == col) {
                        tablero.moverPieza(filaSeleccionada, colSeleccionada, fila, col);
                        actualizarTablero();
                        IA.mover(tablero);
                        actualizarTablero();
                        break;
                    }
                }
            }
            filaSeleccionada = colSeleccionada = -1;
        }
    }

    private void actualizarTablero() {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                JButton boton = botones[fila][col];
                boton.setBackground((fila + col) % 2 == 0 ? new Color(240, 217, 181) : new Color(181, 136, 99));
                Pieza p = tablero.getPieza(fila, col);
                if (p == null) {
                    boton.setIcon(null);
                } else {
                    String clave = p.getClass().getSimpleName() + (p.esBlanca() ? "_B" : "_N");
                    boton.setIcon(iconos.get(clave));
                }
            }
        }
    }

    private void cargarIconos() {
        try {
            String[] piezas = {"Peon", "Torre", "Caballo", "Alfil", "Reina", "Rey"};
            for (String nombre : piezas) {
                Image imgB = ImageIO.read(ClassLoader.getSystemResource("img/" + nombre + "_B.png"));
                Image imgN = ImageIO.read(ClassLoader.getSystemResource("img/" + nombre + "_N.png"));
                iconos.put(nombre + "_B", new ImageIcon(imgB.getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
                iconos.put(nombre + "_N", new ImageIcon(imgN.getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error cargando imágenes de piezas.");
        }
    }

    private void reiniciar() {
        gestor = new GestorPartida();
        tablero = gestor.getTablero();
        actualizarTablero();
    }

    private void guardar() {
        try {
            gestor.guardar("partida.ser");
            JOptionPane.showMessageDialog(this, "Partida guardada.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar.");
        }
    }

    private void cargar() {
        try {
            gestor.cargar("partida.ser");
            tablero = gestor.getTablero();
            actualizarTablero();
            JOptionPane.showMessageDialog(this, "Partida cargada.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar partida.");
        }
    }
}