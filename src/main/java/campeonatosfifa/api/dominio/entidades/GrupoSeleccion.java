package campeonatosfifa.api.dominio.entidades;

public class GrupoSeleccion {

    private Seleccion seleccion;

    private Grupo grupo;

    public GrupoSeleccion() {
    }

    public GrupoSeleccion(Grupo grupo, Seleccion seleccion) {
        this.seleccion = seleccion;
        this.grupo = grupo;
    }

    public Seleccion getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
