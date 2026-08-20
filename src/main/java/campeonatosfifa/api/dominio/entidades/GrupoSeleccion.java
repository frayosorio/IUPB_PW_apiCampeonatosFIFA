package campeonatosfifa.api.dominio.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupopais")
public class GrupoSeleccion {

    @Id
    @JoinColumn(name = "idpais", referencedColumnName = "id")
    @ManyToOne
    private Seleccion seleccion;

    @Id
    @JoinColumn(name = "idgrupo", referencedColumnName = "id")
    @ManyToOne
    private Grupo grupo;

    public GrupoSeleccion() {
    }

    public GrupoSeleccion(Seleccion seleccion, Grupo grupo) {
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
