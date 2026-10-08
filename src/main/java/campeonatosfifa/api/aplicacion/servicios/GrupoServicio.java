package campeonatosfifa.api.aplicacion.servicios;

import java.util.List;

import campeonatosfifa.api.core.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

import campeonatosfifa.api.core.servicios.*;
import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.dominio.dtos.*;
import campeonatosfifa.api.infraestructura.persistencia.repositorios.*;

@Service
public class GrupoServicio implements IGrupoServicio {


    private IGrupoRepositorio repositorio;
    private IGrupoSeleccionRepositorio repositorioGrupoSelecciones;
    private ISeleccionRepositorio repositorioSelecciones;

    public GrupoServicio(IGrupoRepositorio repositorio,
                         IGrupoSeleccionRepositorio repositorioGrupoSelecciones,
                         ISeleccionRepositorio repositorioSelecciones
    ) {
        this.repositorio = repositorio;
        this.repositorioGrupoSelecciones = repositorioGrupoSelecciones;
        this.repositorioSelecciones = repositorioSelecciones;
    }

    @Override
    public List<Grupo> listarPorCampeonato(int idCampeonato) {
        return repositorio.listarPorCampeonato(idCampeonato);
    }

    @Override
    public Grupo obtener(int id) {
        var grupoEncontrado = repositorio.obtenerPorId(id);
        return grupoEncontrado.isEmpty() ? null : grupoEncontrado.get();
    }

    @Override
    public Grupo agregar(Grupo grupo) {
        grupo.setId(0);
        return repositorio.guardar(grupo);
    }

    @Override
    public Grupo modificar(Grupo grupo) {
        var grupoEncontrado = repositorio.obtenerPorId(grupo.getId());
        return grupoEncontrado.isEmpty() ? null : repositorio.guardar(grupo);
    }

    @Override
    public boolean eliminar(int id) {
        return repositorio.eliminar(id);
    }

    // ***** Selecciones del Grupo *****

    @Override
    public List<GrupoSeleccion> listarSelecciones(int idGrupo) {
        return repositorioGrupoSelecciones.listarSelecciones(idGrupo);
    }

    @Override
    public GrupoSeleccion obtenerSeleccion(int idGrupo, int idSeleccion) {
        var grupoSeleccion = repositorioGrupoSelecciones.obtenerPorId(idGrupo, idSeleccion);
        return grupoSeleccion.isEmpty() ? null : grupoSeleccion.get();
    }

    @Override
    public GrupoSeleccion agregarSeleccion(int idGrupo, int idSeleccion) {
        var grupo = repositorio.obtenerPorId(idGrupo);
        var pais = repositorioSelecciones.obtenerPorId(idSeleccion);
        if (grupo.isEmpty() || pais.isEmpty()) {
            return null;
        }
        var grupoSeleccion = repositorioGrupoSelecciones.obtenerPorId(idGrupo, idSeleccion);
        return grupoSeleccion.isEmpty() ? repositorioGrupoSelecciones.guardar(new GrupoSeleccion(grupo.get(), pais.get())) : null;
    }

    @Override
    public GrupoSeleccion modificarSeleccion(int idGrupo, int idPaisActual, int idPaisNuevo) {
        var grupo = repositorio.obtenerPorId(idGrupo);
        var nuevoPais = repositorioSelecciones.obtenerPorId(idPaisNuevo);

        if (grupo.isEmpty() || nuevoPais.isEmpty()) {
            return null;
        }

        // Verificar que existe la relación actual que se quiere modificar
        var grupoSeleccionExistente = repositorioGrupoSelecciones.obtenerPorId(idGrupo, idPaisActual);
        if (grupoSeleccionExistente.isEmpty()) {
            return null; // No existe la relación original
        }

        repositorioGrupoSelecciones.eliminar(idGrupo, idPaisActual);
        return repositorioGrupoSelecciones.guardar(new GrupoSeleccion(grupo.get(), nuevoPais.get()));
    }

    @Override
    public boolean eliminarSeleccion(int idGrupo, int idPais) {
        return repositorioGrupoSelecciones.eliminar(idGrupo, idPais);
    }

    // ***** Tabla de Posiciones *****

    @Override
    public List<TablaPosicionesDto> listarTablaPosiciones(int idGrupo) {
        return repositorio.listarTablaPosiciones(idGrupo);
    }
}
