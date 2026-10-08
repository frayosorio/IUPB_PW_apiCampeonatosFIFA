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

    @Autowired
    private EntityManager em;

    private IGrupoRepositorio repositorio;
    private IGrupoSeleccionRepositorio repositorioGrupoSelecciones;
    private IFaseRepositorio repositorioFases;
    private ICampeonatoRepositorio repositorioCampeonatos;
    private IEncuentroRepositorio repositorioEncuentros;
    private ISeleccionRepositorio repositorioSelecciones;

    public GrupoServicio(IGrupoRepositorio repositorio, IGrupoSeleccionRepositorio repositorioGrupoSelecciones,
            ICampeonatoRepositorio repositorioCampeonatos, ISeleccionRepositorio repositorioSelecciones,
            IFaseRepositorio repositorioFases, IEncuentroRepositorio repositorioEncuentros) {
        this.repositorio = repositorio;
        this.repositorioGrupoSelecciones = repositorioGrupoSelecciones;
        this.repositorioFases = repositorioFases;
        this.repositorioCampeonatos = repositorioCampeonatos;
        this.repositorioEncuentros = repositorioEncuentros;
        this.repositorioSelecciones=repositorioSelecciones;
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
        return  repositorioGrupoSelecciones.eliminar(idGrupo, idPais);
    }

    // ***** Tabla de Posiciones *****

    @Override
    public List<TablaPosicionesDto> listarTablaPosiciones(int idGrupo) {
        List<TablaPosicionesDto> tablaPosiciones = em
                .createNativeQuery(
                        "SELECT * FROM fobtenertablaposiciones(:idgrupotabla) ORDER BY Puntos DESC, GF - GC DESC",
                        TablaPosicionesDto.class)
                .setParameter("idgrupotabla", idGrupo)
                .getResultList();

        return tablaPosiciones;
    }

    @Override
    public void generarEncuentrosSiguienteFaseGrupos(int idGrupo1, int idGrupo2, int idFase){
        int idCampeonato = repositorio.obtenerPorId(idGrupo1)
                .orElseThrow().getCampeonato().getId();

        List<TablaPosicionesDto> posiciones1 = listarTablaPosiciones(idGrupo1);
        List<TablaPosicionesDto> posiciones2 = listarTablaPosiciones(idGrupo2);

        if (posiciones1.size() < 2 || posiciones2.size() < 2) {
            throw new IllegalArgumentException("No hay suficientes países para generar los encuentros.");
        }

        int idSeleccionGrupo1Primera = posiciones1.get(0).getId(); // 1° grupo 1
        int idSeleccionGrupo1Segunda = posiciones1.get(1).getId(); // 2° grupo 1
        int idSeleccionGrupo2Primera = posiciones2.get(0).getId(); // 1° grupo 2
        int idSeleccionGrupo2Segunda = posiciones2.get(1).getId(); // 2° grupo 2

        Seleccion pais1 = repositorioSelecciones.obtenerPorId(idSeleccionGrupo1Primera).orElseThrow();
        Seleccion pais2 = repositorioSelecciones.obtenerPorId(idSeleccionGrupo2Segunda).orElseThrow();
        Seleccion pais3 = repositorioSelecciones.obtenerPorId(idSeleccionGrupo2Primera).orElseThrow();
        Seleccion pais4 = repositorioSelecciones.obtenerPorId(idSeleccionGrupo1Segunda).orElseThrow();

        Fase fase = repositorioFases.obtenerPorId(idFase).orElseThrow();
        Campeonato campeonato = repositorioCampeonatos.obtenerPorId(idCampeonato).orElseThrow();

        Encuentro encuentro1 = new Encuentro();
        encuentro1.setSeleccion1(pais1);
        encuentro1.setSeleccion2(pais2);
        encuentro1.setFase(fase);
        encuentro1.setCampeonato(campeonato);

        Encuentro encuentro2 = new Encuentro();
        encuentro2.setSeleccion1(pais3);
        encuentro2.setSeleccion2(pais4);
        encuentro2.setFase(fase);
        encuentro2.setCampeonato(campeonato);

        repositorioEncuentros.guardar(encuentro1);
        repositorioEncuentros.guardar(encuentro2);
    }

}
