package it.uniroma3.siw.Dto;

import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Utente;

public class RecensioneDto {

    private Integer voto;
    private String titolo;
    private String testo;
    private Libro libro;
    private Utente utente;

    public RecensioneDto() {}

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
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


}
