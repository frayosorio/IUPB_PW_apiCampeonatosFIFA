package campeonatosfifa.api.presentacion.controladores;

import campeonatosfifa.api.core.servicios.IFaseServicio;
import campeonatosfifa.api.dominio.entidades.Fase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fases")
public class FaseControlador {

    @Autowired
    private IFaseServicio servicio;

    @GetMapping
    public ResponseEntity<List<Fase>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Fase> obtener(@PathVariable int id) {
        var seleccionBuscada = servicio.obtener(id);
        if (seleccionBuscada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(seleccionBuscada);
    }

    @GetMapping(value = "/buscar/{nombre}")
    public ResponseEntity<List<Fase>> buscar(@PathVariable String nombre) {
        return ResponseEntity.ok(servicio.buscar(nombre));
    }

    @PostMapping
    public ResponseEntity<Fase> agregar(@RequestBody Fase seleccion) {
        var seleccionCreada = servicio.agregar(seleccion);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(seleccionCreada);
    }

    @PutMapping
    public ResponseEntity<Fase> modificar(@RequestBody Fase seleccion) {
        var seleccionModificada = servicio.modificar(seleccion);
        if (seleccionModificada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(seleccionModificada);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        boolean respuesta = servicio.eliminar(id);
        if (!respuesta) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.noContent().build();
    }

}

