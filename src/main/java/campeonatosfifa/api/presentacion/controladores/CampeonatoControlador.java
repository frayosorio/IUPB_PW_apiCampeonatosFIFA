package campeonatosfifa.api.presentacion.controladores;

import campeonatosfifa.api.core.servicios.ICampeonatoServicio;
import campeonatosfifa.api.dominio.entidades.Campeonato;
import campeonatosfifa.api.dominio.entidades.Campeonato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatoControlador {
    @Autowired
    private ICampeonatoServicio servicio;

    @GetMapping
    public ResponseEntity<List<Campeonato>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Campeonato> obtener(@PathVariable int id) {
        var CampeonatoBuscada = servicio.obtener(id);
        if (CampeonatoBuscada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CampeonatoBuscada);
    }

    @GetMapping(value = "/buscar/{nombre}")
    public ResponseEntity<List<Campeonato>> buscar(@PathVariable String nombre) {
        return ResponseEntity.ok(servicio.buscar(nombre));
    }

    @PostMapping
    public ResponseEntity<Campeonato> agregar(@RequestBody Campeonato campeonato) {
        var campeonatoCreada = servicio.agregar(campeonato);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(campeonatoCreada);
    }

    @PutMapping
    public ResponseEntity<Campeonato> modificar(@RequestBody Campeonato campeonato) {
        var campeonatoModificada = servicio.modificar(campeonato);
        if (campeonatoModificada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(campeonatoModificada);
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
