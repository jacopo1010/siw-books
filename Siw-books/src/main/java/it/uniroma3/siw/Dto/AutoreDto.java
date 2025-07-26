package it.uniroma3.siw.Dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class AutoreDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    private LocalDateTime dataNascita;
    private LocalDateTime dataMorte;
    @NotBlank
    private String  nazionalita;
    private MultipartFile url_foto;

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
    public MultipartFile getUrl_foto() {
        return url_foto;
    }
    public void setUrl_foto(MultipartFile url_foto) {
        this.url_foto = url_foto;
    }

}
