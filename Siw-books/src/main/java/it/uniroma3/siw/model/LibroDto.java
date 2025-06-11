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

    @NotBlank
    private String titolo;

    @NotNull
    private LocalDate annopublicazione;

    private List<String> immagine = new LinkedList<>();

    @NotNull
    private Set<Long> scrittoriIds = new HashSet<>(); // ID degli autori

    // getter/setter

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public LocalDate getAnnopublicazione() { return annopublicazione; }
    public void setAnnopublicazione(LocalDate annopublicazione) { this.annopublicazione = annopublicazione; }

    public List<String> getImmagine() { return immagine; }
    public void setImmagine(List<String> immagine) { this.immagine = immagine; }

    public Set<Long> getScrittoriIds() { return scrittoriIds; }
    public void setScrittoriIds(Set<Long> scrittoriIds) { this.scrittoriIds = scrittoriIds; }
}


