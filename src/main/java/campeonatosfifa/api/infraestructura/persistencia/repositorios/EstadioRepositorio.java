package campeonatosfifa.api.infraestructura.persistencia.repositorios;

import campeonatosfifa.api.core.repositorios.IEstadioRepositorio;
import campeonatosfifa.api.dominio.entidades.Estadio;
import campeonatosfifa.api.infraestructura.persistencia.entidades.EstadioEntidad;
import campeonatosfifa.api.infraestructura.persistencia.mapeadores.EstadioMapeador;
import campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa.IEstadioRepositorioJpa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EstadioRepositorio implements IEstadioRepositorio {

    private IEstadioRepositorioJpa repositorio;

    @Override
    public List<Estadio> listar() {
        return repositorio.findAll()
                .stream()
                .map(EstadioMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Estadio> listarPorPais(int idPais) {
        return repositorio.listarPorPais(idPais)
                .stream()
                .map(EstadioMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Estadio> listarPorCampeonato(int idCampeonato) {
        return repositorio.listarPorCampeonato(idCampeonato)
                .stream()
                .map(EstadioMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Estadio> obtenerPorId(int id) {
        return repositorio.findById(id)
                .map(EstadioMapeador::haciaDominio);
    }

    @Override
    public List<Estadio> buscarPorNombre(String nombre) {
        return repositorio.findByNombreContaining(nombre)
                .stream()
                .map(EstadioMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Estadio guardar(Estadio campeonato) {
        EstadioEntidad entidad = EstadioMapeador.haciaEntidad(campeonato);
        EstadioEntidad entidadGuardada = repositorio.save(entidad);
        return EstadioMapeador.haciaDominio(entidadGuardada);
    }

    @Override
    public boolean eliminar(int id) {
        try {
            if (repositorio.existsById(id)) {
                repositorio.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

}
