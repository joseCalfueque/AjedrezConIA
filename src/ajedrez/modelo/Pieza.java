package ajedrez.modelo;

import java.util.List;

public abstract class Pieza {
    protected boolean esBlanca;
    protected int fila, columna;

    public Pieza(boolean esBlanca, int fila, int columna) {
        this.esBlanca = esBlanca;
        this.fila = fila;
        this.columna = columna;
    }

    public abstract List<int[]> movimientosValidos(Tablero tablero);
    public boolean esBlanca() { return esBlanca; }
    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    public abstract String toString();
}

