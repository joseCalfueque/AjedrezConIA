package com.ajedrez.AjedrezIA_Spring.servicio;

import com.ajedrez.AjedrezIA_Spring.modelo.Tablero;
import org.springframework.stereotype.Service;

@Service
public class TableroService {

    private final Tablero tablero;

    public TableroService() {
        this.tablero = new Tablero();
    }

    public Tablero getTablero() {
        return tablero;
    }

    // Aquí más adelante irán métodos como moverPieza(), reiniciarPartida(), etc.
}