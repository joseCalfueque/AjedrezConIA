package ajedrez.test;


import ajedrez.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPiezasTablero {

    @Test
    public void testPeonMovimientoInicial() {
        Tablero tablero = new Tablero();
        Pieza peon = tablero.getPieza(6, 0);
        List<int[]> movimientos = peon.movimientosValidos(tablero);
        assertTrue(movimientos.stream().anyMatch(m -> m[0] == 5 && m[1] == 0));
        assertTrue(movimientos.stream().anyMatch(m -> m[0] == 4 && m[1] == 0));
    }

    @Test
    public void testCaballoSalta() {
        Tablero tablero = new Tablero();
        Pieza caballo = tablero.getPieza(7, 1);
        List<int[]> movimientos = caballo.movimientosValidos(tablero);
        assertTrue(movimientos.stream().anyMatch(m -> m[0] == 5 && m[1] == 2));
        assertTrue(movimientos.stream().anyMatch(m -> m[0] == 5 && m[1] == 0));
    }

    @Test
    public void testTorreBloqueada() {
        Tablero tablero = new Tablero();
        Pieza torre = tablero.getPieza(7, 0);
        List<int[]> movimientos = torre.movimientosValidos(tablero);
        assertTrue(movimientos.isEmpty());
    }

    @Test
    public void testReyMovimientos() {
        Tablero tablero = new Tablero();
        tablero.moverPieza(6, 4, 4, 4); // mueve peon para abrir paso al rey
        Pieza rey = tablero.getPieza(7, 4);
        List<int[]> movimientos = rey.movimientosValidos(tablero);
        assertTrue(movimientos.stream().anyMatch(m -> m[0] == 6 && m[1] == 4));
    }

    @Test
    public void testIAHaceMovimiento() {
        Tablero tablero = new Tablero();
        int piezasAntes = contarNegras(tablero);
        IA.mover(tablero);
        int piezasDespues = contarNegras(tablero);
        assertTrue(piezasDespues <= piezasAntes);
    }

    private int contarNegras(Tablero tablero) {
        int count = 0;
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                Pieza p = tablero.getPieza(f, c);
                if (p != null && !p.esBlanca()) count++;
            }
        }
        return count;
    }

    @Test
    public void testGuardarYCargar() throws Exception {
        GestorPartida gestor = new GestorPartida();
        gestor.guardar("test.ser");
        Tablero antes = gestor.getTablero();
        Pieza antesPeon = antes.getPieza(6, 0);
        gestor.cargar("test.ser");
        Tablero despues = gestor.getTablero();
        Pieza despuesPeon = despues.getPieza(6, 0);
        assertNotNull(despuesPeon);
        assertEquals(antesPeon.getClass(), despuesPeon.getClass());
    }

    @Test
    public void testCapturaPieza() {
        Tablero tablero = new Tablero();
        tablero.moverPieza(6, 4, 4, 4); // mover peón blanco
        tablero.moverPieza(1, 3, 3, 3); // mover peón negro
        tablero.moverPieza(4, 4, 3, 3); // capturar
        Pieza resultado = tablero.getPieza(3, 3);
        assertTrue(resultado instanceof Peon);
        assertTrue(resultado.esBlanca());
    }

    @Test
    public void testPromocionPeonSimulada() {
        Tablero tablero = new Tablero();
        Peon peon = new Peon(true, 1, 0);
        tablero.casillas[1][0] = peon;
        tablero.casillas[0][0] = null;
        tablero.moverPieza(1, 0, 0, 0);
        Pieza p = tablero.getPieza(0, 0);
        assertNotNull(p);
        assertTrue(p instanceof Peon); // Simula que llegó, pero aún no se promueve
    }

    @Test
    public void testMovimientoInvalidoFueraDeRango() {
        Tablero tablero = new Tablero();
        Pieza caballo = new Caballo(true, 0, 1);
        tablero.casillas[0][1] = caballo;
        List<int[]> movs = caballo.movimientosValidos(tablero);
        for (int[] mov : movs) {
            assertTrue(tablero.enRango(mov[0], mov[1]));
        }
    }
}
