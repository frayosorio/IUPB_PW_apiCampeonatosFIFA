package campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.infraestructura.persistencia.entidades.GrupoSeleccionEntidad;
import campeonatosfifa.api.infraestructura.persistencia.entidades.GrupoSeleccionId;

@Repository
public interface IGrupoSeleccionRepositorioJpa extends JpaRepository<GrupoSeleccionEntidad, GrupoSeleccionId> {

    @Query("SELECT gs FROM GrupoSeleccionEntidad gs WHERE gs.grupo.id=?1 ORDER BY gs.seleccion.nombre ASC")
    public List<GrupoSeleccionEntidad> listarSelecciones(int idGrupo);
}
