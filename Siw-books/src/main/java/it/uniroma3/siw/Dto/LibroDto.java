package it.uniroma3.siw.Dto;

import it.uniroma3.siw.model.Autore;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class LibroDto {

    //@NotBlank
    private String titolo;

    //@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate annoPubblicazione;

    //@NotNull(message = "Devi caricare almeno una immagine")
    private List<MultipartFile> immagine;

    //@NotNull
    private List<Autore> scrittoriIds = new LinkedList<Autore>(); // ID degli autori

    // getter/setter

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public LocalDate getAnnoPubblicazione() { return annoPubblicazione; }
    public void setAnnoPubblicazione(LocalDate annoPubblicazione) { this.annoPubblicazione = annoPubblicazione; }

    public List<MultipartFile> getImmagine() {
        return immagine;
    }

    public void setImmagine(List<MultipartFile> immagine) {
        this.immagine = immagine;
    }

    public List<Autore> getScrittoriIds() { return scrittoriIds; }

    public void setScrittoriIds(List<Autore> scrittoriIds) {
        this.scrittoriIds = scrittoriIds;
    }
}


