package ajedrez.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IA {
    public static void mover(Tablero tablero) {
        List<Pieza> piezas = new ArrayList<>();
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                Pieza p = tablero.getPieza(f, c);
                if (p != null && !p.esBlanca()) {
                    piezas.add(p);
                }
            }
        }
        Collections.shuffle(piezas);
        for (Pieza p : piezas) {
            List<int[]> movs = p.movimientosValidos(tablero);
            if (!movs.isEmpty()) {
                int[] mov = movs.get(0);
                tablero.moverPieza(p.getFila(), p.getColumna(), mov[0], mov[1]);
                break;
            }
        }
    }
}

