package campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.infraestructura.persistencia.entidades.FaseEntidad;

@Repository
public interface IFaseRepositorioJpa extends JpaRepository<FaseEntidad, Integer>{

    List<FaseEntidad> findByNombreContaining(String nombre);

}
