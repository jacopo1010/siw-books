package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.UtenteRepository;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    @Autowired private UtenteRepository utenteRepository;

    public Utente creaUtente(String nome, String cognome, String email){
        Utente utente = new Utente(nome,cognome,email);
        utente = this.utenteRepository.save(utente);
        return utente;
    }

}
