package campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.infraestructura.persistencia.entidades.GrupoEntidad;

@Repository
public interface IGrupoRepositorioJpa extends JpaRepository<GrupoEntidad, Integer>{

    List<GrupoEntidad> findByNombreContaining(String nombre);

    @Query("SELECT g FROM GrupoEntidad g WHERE g.campeonato.id = ?1 ORDER BY g.nombre")
    List<GrupoEntidad> listarPorCampeonato(int idCampeonato);

}
