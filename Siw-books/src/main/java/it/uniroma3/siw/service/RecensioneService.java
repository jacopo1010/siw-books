package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.RecensioneRepository;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecensioneService {

    @Autowired private RecensioneRepository recensioneRepository;


    public Recensione creaRecensione(Integer voto, String titolo, String descrizione, Libro libro, Utente utente) {
        Recensione recensione = new Recensione(voto,titolo,descrizione,libro,utente);
         recensione = this.recensioneRepository.save(recensione);
         return recensione;
    }


    public List<Recensione> listaRecensioni(){
        return this.recensioneRepository.findAll();
    }

    public void salvaRecensione(Recensione recensione){
        this.recensioneRepository.save(recensione);
    }

}
