package it.uniroma3.siw.Dto;

import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Utente;

public class RecensioneDto {

    private Integer voto;
    private String titolo;
    private String testo;
    private Long libroId;
    private Long utenteId;

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

    public Long getLibroId() {
        return libroId;
    }
    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }
    public Long getUtenteId() {
        return utenteId;
    }
    public void setUtenteId(Long utenteId) {
        this.utenteId = utenteId;
    }

}
