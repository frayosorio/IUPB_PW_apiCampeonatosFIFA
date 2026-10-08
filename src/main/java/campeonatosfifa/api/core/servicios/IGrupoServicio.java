package campeonatosfifa.api.core.servicios;

import java.util.List;
import java.util.Optional;

import campeonatosfifa.api.dominio.dtos.TablaPosicionesDto;
import campeonatosfifa.api.dominio.entidades.Grupo;
import campeonatosfifa.api.dominio.entidades.GrupoSeleccion;

public interface IGrupoServicio {

    List<Grupo> listarPorCampeonato(int idCampeonato);

    Grupo obtener(int id);

    Grupo agregar(Grupo grupo);

    Grupo modificar(Grupo grupo);

    boolean eliminar(int id);

    // ***** Selecciones del Grupo *****

    List<GrupoSeleccion> listarSelecciones(int idGrupo);

    GrupoSeleccion obtenerSeleccion(int idGrupo, int idSeleccion);

    GrupoSeleccion agregarSeleccion(int idGrupo, int idSeleccion);

    GrupoSeleccion modificarSeleccion(int idCampeonato, int idPaisActual, int idPaisNuevo);

    boolean eliminarSeleccion(int idGrupo, int idSeleccion);

    // ***** Tabla de posiciones *****

    List<TablaPosicionesDto> listarTablaPosiciones(int idGrupo);


}
