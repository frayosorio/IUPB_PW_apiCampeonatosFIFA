package campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.infraestructura.persistencia.entidades.CampeonatoEntidad;

@Repository
public interface ICampeonatoRepositorioJpa extends JpaRepository<CampeonatoEntidad, Integer>{

    List<CampeonatoEntidad> findByNombreContaining(String nombre);

}
