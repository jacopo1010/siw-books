package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titolo;
    @Column(name = "anno_di_pubblicazione")
    private LocalDate annopublicazione;
    private String immagine;
    @OneToMany
    private Set<Autore> scrittori;

    public Libro(String titolo, LocalDate d, String g) {
        this.titolo = titolo;
        this.annopublicazione = d;
        this.immagine = g;
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

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
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

    public void addAutore(Autore autore){
         this.scrittori.add(autore);
    }

}
