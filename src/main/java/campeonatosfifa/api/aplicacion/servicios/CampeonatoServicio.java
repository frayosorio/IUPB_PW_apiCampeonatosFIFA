package campeonatosfifa.api.aplicacion.servicios;

import java.util.List;

import campeonatosfifa.api.core.repositorios.ICampeonatoPaisRepositorio;
import campeonatosfifa.api.core.repositorios.ICampeonatoRepositorio;
import campeonatosfifa.api.core.repositorios.ISeleccionRepositorio;
import org.springframework.stereotype.Service;

import campeonatosfifa.api.core.servicios.*;
import campeonatosfifa.api.dominio.entidades.*;

@Service
public class CampeonatoServicio implements ICampeonatoServicio {

    private ICampeonatoRepositorio repositorio;
    private ICampeonatoPaisRepositorio repositorioCampeonatoPais;
    private ISeleccionRepositorio repositorioPais;

    public CampeonatoServicio(ICampeonatoRepositorio repositorio,
                              ICampeonatoPaisRepositorio repositorioCampeonatoPais,
                              ISeleccionRepositorio repositorioPais) {
        this.repositorio = repositorio;
        this.repositorioCampeonatoPais = repositorioCampeonatoPais;
        this.repositorioPais = repositorioPais;
    }

    @Override
    public List<Campeonato> listar() {
        return repositorio.listar();
    }

    @Override
    public Campeonato obtener(int id) {
        var campeonatoEncontrado = repositorio.obtenerPorId(id);
        return campeonatoEncontrado.isEmpty() ? null : campeonatoEncontrado.get();
    }

    @Override
    public List<Campeonato> buscar(String nombre) {
        return repositorio.buscarPorNombre(nombre);
    }

    @Override
    public Campeonato agregar(Campeonato campeonato) {
        campeonato.setId(0);
        return repositorio.guardar(campeonato);
    }

    @Override
    public Campeonato modificar(Campeonato campeonato) {
        var campeonatoEncontrado = repositorio.obtenerPorId(campeonato.getId());
        return campeonatoEncontrado.isEmpty() ? null : repositorio.guardar(campeonato);
    }

    @Override
    public boolean eliminar(int id) {
        return repositorio.eliminar(id);
    }

    // ***** Paises Organizadores *****
    @Override
    public List<CampeonatoPais> listarPaises(int idCampeonato) {
        return repositorioCampeonatoPais.listarPaises(idCampeonato);
    }

    @Override
    public CampeonatoPais obtenerPais(int idCampeonato, int idPais) {
        var campeonatoPais = repositorioCampeonatoPais.obtenerPorId(idCampeonato, idPais);
        return campeonatoPais.isEmpty() ? null : campeonatoPais.get();
    }

    @Override
    public CampeonatoPais agregarPais(int idCampeonato, int idPais) {
        var campeonato = repositorio.obtenerPorId(idCampeonato);
        var pais = repositorioPais.obtenerPorId(idPais);
        if (campeonato.isEmpty() || pais.isEmpty()) {
            return null;
        }
        var campeonatoPais = repositorioCampeonatoPais.obtenerPorId(idCampeonato, idPais);
        return campeonatoPais.isEmpty() ? repositorioCampeonatoPais.guardar(new CampeonatoPais(campeonato.get(), pais.get())) : null;
    }

    @Override
    public CampeonatoPais modificarPais(int idCampeonato, int idPaisActual, int idPaisNuevo) {
        var campeonato = repositorio.obtenerPorId(idCampeonato);
        var nuevoPais = repositorioPais.obtenerPorId(idPaisNuevo);

        if (campeonato.isEmpty() || nuevoPais.isEmpty()) {
            return null;
        }

        // Verificar que existe la relación actual que se quiere modificar
        var campeonatoPaisExistente = repositorioCampeonatoPais.obtenerPorId(idCampeonato, idPaisActual);
        if (campeonatoPaisExistente.isEmpty()) {
            return null; // No existe la relación original
        }

        repositorioCampeonatoPais.eliminar(idCampeonato, idPaisActual);
        return repositorioCampeonatoPais.guardar(new CampeonatoPais(campeonato.get(), nuevoPais.get()));
    }

    @Override
    public boolean eliminarPais(int idCampeonato, int idPais) {
        return repositorioCampeonatoPais.eliminar(idCampeonato, idPais);
    }

}
