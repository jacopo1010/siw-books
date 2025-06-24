package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private Set<Libro> libriScritti;

    public Autore(String nome,String cognome, LocalDateTime dn,LocalDateTime dm,String na,String foto) {
        this.nome = cognome;
        this.cognome = cognome;
        this.dataNascita = dn;
        this.dataMorte = dm;
        this.nazionalita = na;
        this.url_foto = foto;
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

    public Set<Libro> getLibriScritti() {
        return libriScritti;
    }

    public void setLibriScritti(Set<Libro> libriScritti) {
        this.libriScritti = libriScritti;
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
