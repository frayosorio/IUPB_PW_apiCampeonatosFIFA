package campeonatosfifa.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import campeonatosfifa.api.core.servicios.ISeleccionServicio;
import campeonatosfifa.api.dominio.entidades.Seleccion;

@RestController
@RequestMapping("/api/selecciones")
public class SeleccionControlador {

  @Autowired
  private ISeleccionServicio servicio;

  @GetMapping(value = "/")
  public List<Seleccion> listar() {
    return servicio.listar();
  }

}
