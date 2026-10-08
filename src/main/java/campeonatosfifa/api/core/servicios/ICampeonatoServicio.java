package campeonatosfifa.api.core.servicios;

import java.util.List;

import campeonatosfifa.api.dominio.entidades.Campeonato;
import campeonatosfifa.api.dominio.entidades.CampeonatoPais;

public interface ICampeonatoServicio {
    
    List<Campeonato> listar();

    Campeonato obtener(int id);

    List<Campeonato> buscar(String nombre);

    Campeonato agregar(Campeonato Campeonato);

    Campeonato modificar(Campeonato Campeonato);

    boolean eliminar(int id);

    // ***** Paises del Campeonato *****

    List<CampeonatoPais> listarPaises(int idCampeonato);

    CampeonatoPais obtenerPais(int idCampeonato, int idPais);

    CampeonatoPais agregarPais(int idCampeonato, int idPais);

    CampeonatoPais modificarPais(int idCampeonato, int idPaisActual, int idPaisNuevo);

    boolean eliminarPais(int idCampeonato, int idPais);
}
