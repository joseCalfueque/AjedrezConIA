package ajedrez.modelo;

public class Tablero {
    public Pieza[][] casillas;

    public Tablero() {
        casillas = new Pieza[8][8];
        inicializar();
    }

    public void inicializar() {
        // Peones
        for (int i = 0; i < 8; i++) {
            casillas[1][i] = new Peon(false, 1, i);
            casillas[6][i] = new Peon(true, 6, i);
        }
        // Torres
        casillas[0][0] = new Torre(false, 0, 0);
        casillas[0][7] = new Torre(false, 0, 7);
        casillas[7][0] = new Torre(true, 7, 0);
        casillas[7][7] = new Torre(true, 7, 7);

        // Caballos
        casillas[0][1] = new Caballo(false, 0, 1);
        casillas[0][6] = new Caballo(false, 0, 6);
        casillas[7][1] = new Caballo(true, 7, 1);
        casillas[7][6] = new Caballo(true, 7, 6);

        // Alfiles
        casillas[0][2] = new Alfil(false, 0, 2);
        casillas[0][5] = new Alfil(false, 0, 5);
        casillas[7][2] = new Alfil(true, 7, 2);
        casillas[7][5] = new Alfil(true, 7, 5);

        // Reinas
        casillas[0][3] = new Reina(false, 0, 3);
        casillas[7][3] = new Reina(true, 7, 3);

        // Reyes
        casillas[0][4] = new Rey(false, 0, 4);
        casillas[7][4] = new Rey(true, 7, 4);
    }

    public boolean enRango(int fila, int col) {
        return fila >= 0 && fila < 8 && col >= 0 && col < 8;
    }

    public boolean estaVacio(int fila, int col) {
        return enRango(fila, col) && casillas[fila][col] == null;
    }

    public boolean hayEnemigo(int fila, int col, boolean esBlanca) {
        return enRango(fila, col) && casillas[fila][col] != null && casillas[fila][col].esBlanca() != esBlanca;
    }

    public Pieza getPieza(int fila, int col) {
        return casillas[fila][col];
    }

    public void moverPieza(int f1, int c1, int f2, int c2) {
        Pieza p = casillas[f1][c1];
        casillas[f1][c1] = null;
        casillas[f2][c2] = p;
        if (p != null) p.setPosicion(f2, c2);
    }

    public Tablero copiar() {
        Tablero copia = new Tablero();
        copia.casillas = new Pieza[8][8];
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                Pieza p = casillas[f][c];
                if (p != null) {
                    copia.casillas[f][c] = PiezaFactory.clonar(p);
                }
            }
        }
        return copia;
    }
}
