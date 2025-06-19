package ajedrez.modelo;

import java.io.*;

public class GestorPartida {
    private Tablero tablero;
    private Jugador jugadorBlanco, jugadorNegro;

    public GestorPartida() {
        tablero = new Tablero();
        jugadorBlanco = new Jugador(true);
        jugadorNegro = new Jugador(false);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void guardar(String archivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(tablero.casillas);
        }
    }

    public void cargar(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            tablero.casillas = (Pieza[][]) in.readObject();
        }
    }
}

