package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private LocalDateTime dataNascita;
    private LocalDateTime dataMorte;
    private String  nazionalita;
    private String url_foto;
    @ManyToMany(mappedBy = "scrittori")
    private List<Libro> libriScritti;

    public Autore(String nome,String cognome, LocalDateTime dn,LocalDateTime dm,String na,String foto) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dn;
        this.dataMorte = dm;
        this.nazionalita = na;
        this.url_foto = foto;
        this.libriScritti = new ArrayList<>();
    }

    public Autore(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDateTime getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDateTime dataNascita) {
        this.dataNascita = dataNascita;
    }

    public LocalDateTime getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(LocalDateTime dataMorte) {
        this.dataMorte = dataMorte;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public List<Libro> getLibriScritti() {
        return libriScritti;
    }

    public void setLibriScritti(List<Libro> libriScritti) {
        this.libriScritti = libriScritti;
    }

    public void addLibriScritti(Libro libro){
        libro.getScrittori().add(this);
        this.libriScritti.add(libro);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autore autore = (Autore) o;
        return Objects.equals(id, autore.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
