package ajedrez.modelo;

public class Jugador {
    private boolean esBlanco;

    public Jugador(boolean esBlanco) {
        this.esBlanco = esBlanco;
    }

    public boolean esBlanco() {
        return esBlanco;
    }
}