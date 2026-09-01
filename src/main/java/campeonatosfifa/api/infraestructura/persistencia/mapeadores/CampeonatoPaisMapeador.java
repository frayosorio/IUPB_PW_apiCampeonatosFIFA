package campeonatosfifa.api.infraestructura.persistencia.mapeadores;

import campeonatosfifa.api.dominio.entidades.CampeonatoPais;
import campeonatosfifa.api.infraestructura.persistencia.entidades.CampeonatoPaisEntidad;

public class CampeonatoPaisMapeador {

    public static CampeonatoPais haciaDominio(CampeonatoPaisEntidad entidad) {
        if (entidad == null)
            return null;

        return new CampeonatoPais(
                CampeonatoMapeador.haciaDominio(entidad.getCampeonato()),
                SeleccionMapeador.haciaDominio(entidad.getPais()));
    }

    public static CampeonatoPaisEntidad haciaEntidad(CampeonatoPais campeonatopais) {
        if (campeonatopais == null)
            return null;
        return new CampeonatoPaisEntidad(
                CampeonatoMapeador.haciaEntidad(campeonatopais.getCampeonato()),
                SeleccionMapeador.haciaEntidad(campeonatopais.getPais()));
    }

}
