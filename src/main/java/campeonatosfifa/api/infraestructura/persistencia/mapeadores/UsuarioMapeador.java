package campeonatosfifa.api.infraestructura.persistencia.mapeadores;

import campeonatosfifa.api.dominio.entidades.Usuario;
import campeonatosfifa.api.infraestructura.persistencia.entidades.UsuarioEntidad;

public class UsuarioMapeador {
    public static Usuario haciaDominio(UsuarioEntidad entidad) {
        if (entidad == null) {
            return null;
        }
        return new Usuario(
                entidad.getId(),
                entidad.getUsuario(),
                entidad.getNombre(),
                entidad.getClave(),
                entidad.getRoles());
    }

    public static UsuarioEntidad haciaEntidad(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioEntidad(
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getNombre(),
                usuario.getClave(),
                usuario.getRoles());
    }
}
