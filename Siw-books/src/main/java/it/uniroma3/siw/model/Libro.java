package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titolo;
    @Column(name = "anno_di_pubblicazione")
    private LocalDate annopublicazione;
    @ElementCollection
    private List<String> immagine;
    @OneToMany
    private Set<Autore> scrittori;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Recensione> recensioni;

    public Libro(String titolo, LocalDate d) {
        this.titolo = titolo;
        this.annopublicazione = d;
        this.immagine = new LinkedList<>();
        this.scrittori = new HashSet<Autore>();
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

    public List<String> getImmagine() {
        return immagine;
    }

    public void setImmagine(List<String> immagine) {
        this.immagine = immagine;
    }

    public LocalDate getAnnopublicazione() {
        return annopublicazione;
    }

    public void setAnnopublicazione(LocalDate annopublicazione) {
        this.annopublicazione = annopublicazione;
    }

    public Set<Autore> getScrittori() {
        return scrittori;
    }

    public void setScrittori(Set<Autore> scrittori) {
        this.scrittori = scrittori;
    }

    public void addAutore(Autore autore) {
        this.scrittori.add(autore);
    }

    public void addFoto(String s){
        this.immagine.add(s);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Libro other = (Libro) obj;
        return this.id == other.getId() && this.titolo.equals(other.getTitolo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titolo);
    }
}
