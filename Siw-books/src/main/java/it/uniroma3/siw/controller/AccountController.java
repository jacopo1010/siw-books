package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.sessionData.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @Autowired private SessionData sessionData;

    @GetMapping("/myAccount")
    public String myAccount(Model model) {
        Utente corrente = this.sessionData.getLoggedUtente();
        model.addAttribute("utente", corrente);
        return "myAccount.html";
    }


}
