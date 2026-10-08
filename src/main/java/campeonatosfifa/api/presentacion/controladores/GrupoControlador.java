package campeonatosfifa.api.presentacion.controladores;


import campeonatosfifa.api.core.servicios.IGrupoServicio;
import campeonatosfifa.api.dominio.dtos.TablaPosicionesDto;
import campeonatosfifa.api.dominio.entidades.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
public class GrupoControlador {

    @Autowired
    private IGrupoServicio servicio;

    @GetMapping(value = "/campeonato/{idCampeonato}")
    public ResponseEntity<List<Grupo>> listarPorCampeonato(int idCampeonato) {
        return ResponseEntity.ok(servicio.listarPorCampeonato(idCampeonato));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Grupo> obtener(@PathVariable int id) {
        var grupoBuscada = servicio.obtener(id);
        if (grupoBuscada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grupoBuscada);
    }

    @PostMapping
    public ResponseEntity<Grupo> agregar(@RequestBody Grupo grupo) {
        var grupoCreada = servicio.agregar(grupo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(grupoCreada);
    }

    @PutMapping
    public ResponseEntity<Grupo> modificar(@RequestBody Grupo grupo) {
        var grupoModificada = servicio.modificar(grupo);
        if (grupoModificada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grupoModificada);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        boolean respuesta = servicio.eliminar(id);
        if (!respuesta) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.noContent().build();
    }

    // ***** Tabla de Posiciones *****

    @GetMapping(value = "/posiciones/{id}")
    public ResponseEntity<List<TablaPosicionesDto>> listarTablaPosiciones(@PathVariable int id) {
        return ResponseEntity.ok(servicio.listarTablaPosiciones(id));
    }
}

