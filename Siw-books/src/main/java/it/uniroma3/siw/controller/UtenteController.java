package it.uniroma3.siw.controller;

import it.uniroma3.siw.Dto.RecensioneDto;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.service.UtenteService;
import it.uniroma3.siw.sessionData.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtenteController {

   @Autowired private RecensioneService recensioneService;
   @Autowired private UtenteService utenteService;
   @Autowired private SessionData sessionData;

   @GetMapping("/aggiungiRecensione")
   public String aggiungiRecensione(Model model) {
       RecensioneDto recensione = new RecensioneDto();
       model.addAttribute("recensione", recensione);
       return "aggiungiRecensione.html";
   }

}
