package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Utente;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import static it.uniroma3.siw.model.Credenziali.ADMIN_ROLE;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("utente")
    public UserDetails getUtente() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails u = null;
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            u = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return u;
    }

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAnonymous = auth == null || auth instanceof AnonymousAuthenticationToken;
        model.addAttribute("isAnonymous", isAnonymous);

        // Se l'utente non è anonimo puoi aggiungere altre informazioni
        if(!isAnonymous) {
            Object principal = auth.getPrincipal();
            if(principal instanceof UserDetails) {
                boolean isAdmin = auth.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(ADMIN_ROLE));
                model.addAttribute("isAdmin", isAdmin);
            }
            else {
                model.addAttribute("isAdmin", false);
            }
        }
    }

}
