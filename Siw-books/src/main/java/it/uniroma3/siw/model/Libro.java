package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titolo;
    @Column(name = "anno_di_pubblicazione")
    private LocalDate annoPubblicazione;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_immagini", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "url_immagine")
    private List<String> immagine;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autore",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autore_id")
    )
    private List<Autore> scrittori;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Recensione> recensioni;

    public Libro(String titolo, LocalDate d, List<Autore> autori) {
        this.titolo = titolo;
        this.annoPubblicazione = d;
        this.immagine = new LinkedList<String>();
        this.scrittori = autori;
    }

    public Libro() {}

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

    public LocalDate getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(LocalDate annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public List<Autore> getScrittori() {
        return scrittori;
    }

    public void setScrittori(List<Autore> scrittori) {
        this.scrittori = scrittori;
    }

    public void addAutore(Autore autore) {
        autore.getLibriScritti().add(this);
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
