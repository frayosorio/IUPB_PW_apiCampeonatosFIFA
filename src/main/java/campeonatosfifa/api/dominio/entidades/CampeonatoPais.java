package campeonatosfifa.api.dominio.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "campeonatopais")
public class CampeonatoPais {

    @Id
    @JoinColumn(name = "idcampeonato", referencedColumnName = "id")
    @ManyToOne
    private Campeonato campeonato;

    @Id
    @JoinColumn(name = "idpais", referencedColumnName = "id")
    @ManyToOne
    private Seleccion pais;

    public CampeonatoPais() {
    }

    public CampeonatoPais(Campeonato campeonato, Seleccion pais) {
        this.campeonato = campeonato;
        this.pais = pais;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Seleccion getPais() {
        return pais;
    }

    public void setPais(Seleccion pais) {
        this.pais = pais;
    }

}
