package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.CredenzialiRepository;
import it.uniroma3.siw.Repository.UtenteRepository;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UtenteService {

    @Autowired private UtenteRepository utenteRepository;
    @Autowired private CredenzialiRepository credenzialiRepository;

    @Transactional
    public Utente creaUtente(String nome, String cognome, String email){
        Utente utente = new Utente(nome,cognome,email);
        utente = this.utenteRepository.save(utente);
        return utente;
    }

    public Utente getUtentePerId(Long id){
        Utente utente = this.utenteRepository.findById(id).orElse(null);
        return utente;
    }

    @Transactional
    public Utente getUser(String email) {
        Optional<Utente> user = this.utenteRepository.findByEmail(email);
        return user.orElse(null);
    }

    @Transactional
    public void updateResetPassword(String email, String token) throws UserNotFoundException {
        Utente user = this.getUser(email);
        if (user != null) {
            Credenziali credenziali = user.getCredenziali();
            credenziali.setResetPasswordToken(token);
            this.credenzialiRepository.save(credenziali);
        } else {
            throw new UserNotFoundException("non abbiamo trovato alcun credenziali associata a: " + email);
        }
    }

    @Transactional
    public Utente getByResetPasswordToken(String resetPasswordToken) {
        Credenziali credenziali = this.credenzialiRepository.findByResetPasswordToken(resetPasswordToken).orElse(null);
        Utente user = credenziali.getUtente();
        return user;
    }

    @Transactional
    public void updatePassword(String email, String newPassword) throws UserNotFoundException {
        BCryptPasswordEncoder codificatore = new BCryptPasswordEncoder();
        String encodePassword = codificatore.encode(newPassword);
        Utente user = this.getUser(email);
        if (user != null) {
            Credenziali credenziali = user.getCredenziali();
            credenziali.setPassword(encodePassword);
            credenziali.setResetPasswordToken(null);
            this.credenzialiRepository.save(credenziali);
        } else {
            throw new UserNotFoundException("non abbiamo trovato alcun credenziali associata a: " + email);
        }
    }
}
