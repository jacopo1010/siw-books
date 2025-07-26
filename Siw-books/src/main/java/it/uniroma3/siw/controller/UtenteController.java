package it.uniroma3.siw.controller;

import it.uniroma3.siw.Dto.RecensioneDto;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.service.UtenteService;
import it.uniroma3.siw.sessionData.SessionData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UtenteController {

   @Autowired private RecensioneService recensioneService;
   @Autowired private UtenteService utenteService;
   @Autowired private SessionData sessionData;
   @Autowired private LibroService libroService;

   @GetMapping("/aggiungiRecensione/{id}")
   public String aggiungiRecensione(Model model, @PathVariable Long id) {
       Libro libro = this.libroService.getLibro(id);
       RecensioneDto recensione = new RecensioneDto();
       recensione.setLibro(libro);
       recensione.setUtente(this.sessionData.getLoggedUtente());
       model.addAttribute("recensione", recensione);
       return "aggiungiRecensione.html";
   }

   @PostMapping("/aggiungiRecensione/{id}")
   public String aggiungiRecensionePost(Model model, @Valid @ModelAttribute("recensione") RecensioneDto r,
                                        BindingResult result) {
       if (!result.hasErrors()) {
           Recensione recensione = new Recensione(r.getVoto(), r.getTitolo(), r.getTesto(), r.getLibro(), r.getUtente());
           this.recensioneService.salvaRecensione(recensione);
           model.addAttribute("recensione", recensione);
           return "vediLibro.html";
       } else {
           throw new RuntimeException("Errore nel aggiungere una recensione");
       }
   }




}
