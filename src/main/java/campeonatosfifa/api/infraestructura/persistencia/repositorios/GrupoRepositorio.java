package campeonatosfifa.api.infraestructura.persistencia.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import campeonatosfifa.api.dominio.dtos.TablaPosicionesDto;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import campeonatosfifa.api.core.repositorios.IGrupoRepositorio;
import campeonatosfifa.api.dominio.entidades.Grupo;
import campeonatosfifa.api.infraestructura.persistencia.entidades.GrupoEntidad;
import campeonatosfifa.api.infraestructura.persistencia.mapeadores.GrupoMapeador;
import campeonatosfifa.api.infraestructura.persistencia.repositorios.jpa.IGrupoRepositorioJpa;
import org.springframework.stereotype.Component;

@Component
public class GrupoRepositorio implements IGrupoRepositorio {

    @Autowired
    private EntityManager em;

    @Autowired
    private IGrupoRepositorioJpa repositorio;

    @Override
    public List<Grupo> listarPorCampeonato(int idCampeonato) {
        return repositorio.listarPorCampeonato(idCampeonato)
                .stream()
                .map(GrupoMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Grupo> obtenerPorId(int id) {
        return repositorio.findById(id)
                .map(GrupoMapeador::haciaDominio);
    }

    @Override
    public Grupo guardar(Grupo grupo) {
        GrupoEntidad entidad = GrupoMapeador.haciaEntidad(grupo);
        GrupoEntidad entidadGuardada = repositorio.save(entidad);
        return GrupoMapeador.haciaDominio(entidadGuardada);
    }

    @Override
    public boolean eliminar(int id) {
        try {
            if (repositorio.existsById(id)) {
                repositorio.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<TablaPosicionesDto> listarTablaPosiciones(int idGrupo){
        List<TablaPosicionesDto> tablaPosiciones = em
                .createNativeQuery(
                        "SELECT * FROM fobtenertablaposiciones(:idgrupotabla) ORDER BY Puntos DESC, GF - GC DESC",
                        TablaPosicionesDto.class)
                .setParameter("idgrupotabla", idGrupo)
                .getResultList();

        return tablaPosiciones;
    }

}
