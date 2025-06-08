package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.CredenzialiRepository;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredenzialiService {

    @Autowired private CredenzialiRepository credenzialiRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public Credenziali creaCredenziali(String username,String password,String ruolo, Utente u){
        String passCriptata = this.passwordEncoder.encode(password);
        Credenziali credenziali = new Credenziali(username,passCriptata,ruolo,u);
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
