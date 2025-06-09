package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


@Entity
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titolo;
    @Min(1)
    @Max(5)
    private int voto;
    @Column(columnDefinition = "TEXT")
    private String testo;
    @ManyToMany
    private Libro libro;

    public Recensione(String titolo, int voto, String testo) {
        this.titolo = titolo;
        this.voto = voto;
        this.testo = testo;
    }

    public Recensione(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
