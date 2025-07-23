package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LibroDto {

    //@NotBlank
    private String titolo;

    //@NotNull
    private LocalDate annoPubblicazione;

    private List<String> immagine = new LinkedList<String>();

    //@NotNull
    private List<Autore> scrittoriIds = new LinkedList<Autore>(); // ID degli autori

    // getter/setter

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public LocalDate getAnnoPubblicazione() { return annoPubblicazione; }
    public void setAnnoPubblicazione(LocalDate annoPubblicazione) { this.annoPubblicazione = annoPubblicazione; }

    public List<String> getImmagine() { return immagine; }
    public void setImmagine(List<String> immagine) { this.immagine = immagine; }

    public List<Autore> getScrittoriIds() { return scrittoriIds; }

    public void setScrittoriIds(List<Autore> scrittoriIds) {
        this.scrittoriIds = scrittoriIds;
    }
}


