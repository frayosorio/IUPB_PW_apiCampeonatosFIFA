package campeonatosfifa.api.infraestructura.persistencia.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import campeonatosfifa.api.core.repositorios.ICampeonatoRepositorio;
import campeonatosfifa.api.dominio.entidades.Campeonato;
import campeonatosfifa.api.infraestructura.persistencia.mapeadores.CampeonatoMapeador;
import campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa.ICampeonatoRepositorioJpa;

public class CampeonatoRepositorio implements ICampeonatoRepositorio {

    private ICampeonatoRepositorioJpa repositorio;

    @Override
    public List<Campeonato> listar() {
        return repositorio.findAll()
                .stream()
                .map(CampeonatoMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Campeonato> obtenerPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public List<Campeonato> buscarPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorNombre'");
    }

    @Override
    public Campeonato guardar(Campeonato campeonato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public boolean eliminar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
