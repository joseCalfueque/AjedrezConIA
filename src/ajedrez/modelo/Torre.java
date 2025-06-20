package ajedrez.modelo;

import java.util.ArrayList;
import java.util.List;

public class Torre extends Pieza {
    public Torre(boolean esBlanca, int fila, int columna) {
        super(esBlanca, fila, columna);
    }

    @Override
    public List<int[]> movimientosValidos(Tablero tablero) {
        List<int[]> movs = new ArrayList<>();
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] d : dirs) {
            int f = fila + d[0];
            int c = columna + d[1];
            while (tablero.enRango(f, c)) {
                if (tablero.estaVacio(f, c)) movs.add(new int[]{f, c});
                else {
                    if (tablero.hayEnemigo(f, c, esBlanca)) movs.add(new int[]{f, c});
                    break;
                }
                f += d[0]; c += d[1];
            }
        }
        return movs;
    }

    public String toString() { return esBlanca ? "♖" : "♜"; }
}