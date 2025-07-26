package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Objects;


@Entity
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titolo;
    @Min(1)
    @Max(5)
    private Integer voto;
    @Column(columnDefinition = "TEXT")
    private String testo;
    @ManyToOne
    @JoinColumn(nullable = false,name = "libro_id")
    private Libro libro;
    @ManyToOne
    @JoinColumn(nullable = false, name = "utente_id")
    private Utente utente;

    public Recensione(Integer voto, String titolo, String testo, Libro libro, Utente utente) {
        this.titolo = titolo;
        this.voto = voto;
        this.testo = testo;
        this.libro = libro;
        this.utente = utente;
        this.libro = libro;
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

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public String getTesto() {
        return testo;
    }
    public void setTesto(String testo) {
         this.testo = testo;
    }
    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    public Utente getUtente() {
        return utente;
    }
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Recensione that = (Recensione) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
