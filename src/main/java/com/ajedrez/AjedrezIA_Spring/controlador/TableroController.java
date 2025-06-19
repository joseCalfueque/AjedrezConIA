package com.ajedrez.AjedrezIA_Spring.controlador;

import com.ajedrez.AjedrezIA_Spring.modelo.Tablero;
import com.ajedrez.AjedrezIA_Spring.servicio.TableroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tablero")
public class TableroController {

    private final TableroService tableroService;

    public TableroController(TableroService tableroService) {
        this.tableroService = tableroService;
    }

    @GetMapping
    public Tablero obtenerTablero() {
        return tableroService.getTablero();
    }
}
