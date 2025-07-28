package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.RecensioneRepository;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class RecensioneService {

    @Autowired private RecensioneRepository recensioneRepository;

    @Transactional
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

    public Recensione findRecensione(Long id){
        return this.recensioneRepository.findById(id).orElse(null);
    }

    @Transactional
    public void eliminaRecensione(Recensione recensione){
        recensione.getUtente().getRecensioni().remove(recensione);
        recensione.getLibro().getRecensioni().remove(recensione);
        this.recensioneRepository.delete(recensione);
    }

    public boolean HaRecensito(Long libroId, Long utenteId) {
        return this.recensioneRepository.existsByLibroIdAndUtenteId(libroId,utenteId);
    }

    public Recensione getRecensioneByUtenteAndLibro(Long userId, Long bookId) {
        return this.recensioneRepository.findByUtenteIdAndLibroId(userId, bookId);
    }

}
