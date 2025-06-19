package com.ajedrez.AjedrezIA_Spring.modelo;

import java.util.ArrayList;
import java.util.List;

public class Caballo extends Pieza {
    public Caballo(boolean esBlanca, int fila, int columna) {
        super(esBlanca, fila, columna);
    }

    @Override
    public List<int[]> movimientosValidos(Tablero tablero) {
        List<int[]> movs = new ArrayList<>();
        int[][] deltas = {{-2,-1}, {-2,1}, {-1,-2}, {-1,2}, {1,-2}, {1,2}, {2,-1}, {2,1}};
        for (int[] d : deltas) {
            int f = fila + d[0], c = columna + d[1];
            if (tablero.enRango(f, c) && (tablero.estaVacio(f, c) || tablero.hayEnemigo(f, c, esBlanca))) {
                movs.add(new int[]{f, c});
            }
        }
        return movs;
    }

    public String toString() { return esBlanca ? "♘" : "♞"; }
}
