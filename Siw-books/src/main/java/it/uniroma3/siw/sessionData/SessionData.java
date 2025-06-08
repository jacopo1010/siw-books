package it.uniroma3.siw.sessionData;

import it.uniroma3.siw.Repository.CredenzialiRepository;
import it.uniroma3.siw.Repository.UtenteRepository;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData {

    private Utente utente;
    private Credenziali credenziali;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private CredenzialiRepository credenzialiRepository;

    public Credenziali getLoggedCredenziali() {
        if (this.credenziali == null)
            this.update();
        return this.credenziali;
    }

    public Utente getLoggedUtente() {
        if (this.utente == null)
            this.update();
        return this.utente;
    }

    private void update() {
       Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if (obj instanceof UserDetails) {
           UserDetails userLoggato = (UserDetails) obj;
           this.credenziali = this.credenzialiRepository.findByUsername(userLoggato.getUsername()).get();
           this.credenziali.setPassword("[PROTECTED]");
           this.utente = this.credenziali.getUtente();
       }else {
           throw new RuntimeException();
       }
    }


}
