package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Utente;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

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

}
