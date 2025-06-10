package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.*;

@Entity
public class Librelandia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.REMOVE)
    @MapKey
    private Map<Long, Libro> libri;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Utente> utentiRegistrati;

    public Librelandia(){
        this.libri = new HashMap<Long,Libro>();
        this.utentiRegistrati = new ArrayList<Utente>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, Libro> getLibri() {
        return libri;
    }

    public void setLibri(Map<Long, Libro> libri) {
        this.libri = libri;
    }

    public List<Utente> getUtentiRegistrati() {
        return utentiRegistrati;
    }

    public void setUtentiRegistrati(List<Utente> utentiRegistrati) {
        this.utentiRegistrati = utentiRegistrati;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Librelandia that = (Librelandia) o;
        return this.id == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
