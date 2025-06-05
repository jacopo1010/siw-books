package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.CredenzialiRepository;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredenzialiService {

    @Autowired private CredenzialiRepository credenzialiRepository;

    public Credenziali creaCredenziali(String username,String password,String ruolo, Utente u){
         Credenziali credenziali = new Credenziali(username,password,ruolo,u);
         credenziali = this.credenzialiRepository.save(credenziali);
         return credenziali;
    }

    public Credenziali getCredenziali(Long id){
        Credenziali credenziali = this.credenzialiRepository.findById(id).orElse(null);
        return credenziali;
    }

    public Credenziali getCredenzialiPerUsername(String username){
        Credenziali credenziali = this.credenzialiRepository.findByUsername(username).orElse(null);
        return credenziali;
    }


}
