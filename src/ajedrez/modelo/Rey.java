package ajedrez.modelo;

import java.util.ArrayList;
import java.util.List;

public class Rey extends Pieza {
    public Rey(boolean esBlanca, int fila, int columna) {
        super(esBlanca, fila, columna);
    }

    @Override
    public List<int[]> movimientosValidos(Tablero tablero) {
        List<int[]> movs = new ArrayList<>();
        for (int df = -1; df <= 1; df++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (df == 0 && dc == 0) continue;
                int f = fila + df, c = columna + dc;
                if (tablero.enRango(f, c) && (tablero.estaVacio(f, c) || tablero.hayEnemigo(f, c, esBlanca))) {
                    movs.add(new int[]{f, c});
                }
            }
        }
        return movs;
    }

    public String toString() { return esBlanca ? "♔" : "♚"; }
}

