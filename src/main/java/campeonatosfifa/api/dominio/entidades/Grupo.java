package campeonatosfifa.api.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupo")
public class Grupo {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_grupo")
    @SequenceGenerator(
        name = "secuencia_grupo",
        sequenceName = "secuencia_grupo",
        allocationSize = 1
    )
    private int id;

    @Column(name = "grupo", length = 5, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idcampeonato", referencedColumnName = "id", nullable = false)
    private Campeonato campeonato;

    public Grupo() {
    }

    public Grupo(int id, String nombre, Campeonato campeonato) {
        this.id = id;
        this.nombre = nombre;
        this.campeonato = campeonato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
}