package campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.infraestructura.persistencia.entidades.CampeonatoPaisEntidad;
import campeonatosfifa.api.infraestructura.persistencia.entidades.CampeonatoPaisId;

@Repository
public interface ICampeonatoPaisRepositorioJpa extends JpaRepository<CampeonatoPaisEntidad, CampeonatoPaisId> {
    
    @Query("SELECT cp FROM CampeonatoPaisEntidad cp WHERE cp.campeonato.id=?1 ORDER BY cp.pais.nombre ASC")
    public List<CampeonatoPaisEntidad> listarPaises(int idcampeonato);
}
