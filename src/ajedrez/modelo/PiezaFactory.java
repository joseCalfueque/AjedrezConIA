package ajedrez.modelo;

public class PiezaFactory {
    public static Pieza clonar(Pieza p) {
        if (p instanceof Peon) return new Peon(p.esBlanca(), p.getFila(), p.getColumna());
        if (p instanceof Torre) return new Torre(p.esBlanca(), p.getFila(), p.getColumna());
        if (p instanceof Caballo) return new Caballo(p.esBlanca(), p.getFila(), p.getColumna());
        if (p instanceof Alfil) return new Alfil(p.esBlanca(), p.getFila(), p.getColumna());
        if (p instanceof Reina) return new Reina(p.esBlanca(), p.getFila(), p.getColumna());
        if (p instanceof Rey) return new Rey(p.esBlanca(), p.getFila(), p.getColumna());
        return null;
    }
}

