package campeonatosfifa.api.infraestructura.persistencia.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import campeonatosfifa.api.core.repositorios.ISeleccionRepositorio;
import campeonatosfifa.api.dominio.entidades.Seleccion;
import campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa.ISeleccionRepositorioJpa;
import campeonatosfifa.api.infraestructura.persistencia.mapeadores.SeleccionMapeador;

@Component
public class SeleccionRepositorio implements ISeleccionRepositorio {

    @Autowired
    private ISeleccionRepositorioJpa repositorio;

    @Override
    public List<Seleccion> listar() {
        return repositorio.findAll()
                .stream()
                .map(SeleccionMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Seleccion> obtenerPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public List<Seleccion> buscarPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorNombre'");
    }

    @Override
    public Seleccion guardar(Seleccion seleccion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public boolean eliminar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
