package com.ajedrez.AjedrezIA_Spring.modelo;

import java.util.ArrayList;
import java.util.List;

public class Reina extends Pieza {
    public Reina(boolean esBlanca, int fila, int columna) {
        super(esBlanca, fila, columna);
    }

    @Override
    public List<int[]> movimientosValidos(Tablero tablero) {
        List<int[]> movs = new ArrayList<>();
        movs.addAll(new Torre(esBlanca, fila, columna).movimientosValidos(tablero));
        movs.addAll(new Alfil(esBlanca, fila, columna).movimientosValidos(tablero));
        return movs;
    }

    public String toString() { return esBlanca ? "♕" : "♛"; }
}