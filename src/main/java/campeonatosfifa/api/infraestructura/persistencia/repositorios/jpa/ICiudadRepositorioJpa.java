package campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.infraestructura.persistencia.entidades.CiudadEntidad;

@Repository
public interface ICiudadRepositorioJpa extends JpaRepository<CiudadEntidad, Integer> {

    List<CiudadEntidad> findByNombreContaining(String nombre);

    @Query("SELECT c FROM CiudadEntidad c WHERE c.pais.id=:idPais ORDER BY c.nombre")
    List<CiudadEntidad> listarPorPais(int idPais);

    @Query("SELECT c FROM CiudadEntidad c " +
            " JOIN CampeonatoPaisEntidad cp ON c.pais.id = cp.pais.id" +
            " WHERE cp.campeonato.id=:idCampeonato ORDER BY c.nombre")
    List<CiudadEntidad> listarPorCampeonato(int idCampeonato);

}
