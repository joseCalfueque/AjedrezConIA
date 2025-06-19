package ajedrez.modelo;

import java.util.ArrayList;
import java.util.List;

public class Peon extends Pieza {
    public Peon(boolean esBlanca, int fila, int columna) {
        super(esBlanca, fila, columna);
    }

    @Override
    public List<int[]> movimientosValidos(Tablero tablero) {
        List<int[]> movimientos = new ArrayList<>();
        int direccion = esBlanca ? -1 : 1;
        int nuevaFila = fila + direccion;

        if (tablero.estaVacio(nuevaFila, columna)) {
            movimientos.add(new int[]{nuevaFila, columna});
            if ((esBlanca && fila == 6) || (!esBlanca && fila == 1)) {
                int salto = fila + 2 * direccion;
                if (tablero.estaVacio(salto, columna)) {
                    movimientos.add(new int[]{salto, columna});
                }
            }
        }
        for (int dc : new int[]{-1, 1}) {
            int nc = columna + dc;
            if (tablero.enRango(nuevaFila, nc) && tablero.hayEnemigo(nuevaFila, nc, esBlanca)) {
                movimientos.add(new int[]{nuevaFila, nc});
            }
        }
        return movimientos;
    }

    @Override
    public String toString() {
        return esBlanca ? "♙" : "♟";
    }
}