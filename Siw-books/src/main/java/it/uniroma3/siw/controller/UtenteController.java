package it.uniroma3.siw.controller;

import it.uniroma3.siw.Dto.RecensioneDto;
import it.uniroma3.siw.Dto.UtenteDto;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.Utente;
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

    @Autowired
    private RecensioneService recensioneService;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private SessionData sessionData;
    @Autowired
    private LibroService libroService;

    @GetMapping("/aggiungiRecensione/{id}")
    public String aggiungiRecensione(Model model, @PathVariable Long id) {
        RecensioneDto recensione = new RecensioneDto();
        recensione.setLibroId(id);
        recensione.setUtenteId(this.sessionData.getLoggedUtente().getId());
        model.addAttribute("recensione", recensione);
        model.addAttribute("libro", this.libroService.getLibro(id));
        model.addAttribute("utente", this.sessionData.getLoggedUtente());
        return "aggiungiRecensione.html";
    }

    @PostMapping("/aggiungiRecensione/{id}")
    public String aggiungiRecensionePost(Model model, @Valid @ModelAttribute("recensione") RecensioneDto r,
                                         BindingResult result) {
        if (!result.hasErrors()) {
            Libro libro = this.libroService.getLibro(r.getLibroId());
            Utente utente = this.utenteService.getUtentePerId(r.getUtenteId());
            Recensione recensione = new Recensione(r.getVoto(), r.getTitolo(), r.getTesto(), libro, utente);
            this.recensioneService.salvaRecensione(recensione);
            model.addAttribute("recensione", recensione);
            return "redirect:/visualizzaLibro/" + libro.getId();
        } else {
            throw new RuntimeException("Errore nel aggiungere una recensione");
        }
    }

    @GetMapping("/modificaRecensione/{id}")
    public String modificaRecensione(Model model, @PathVariable Long id) {
        Recensione recensione = this.recensioneService.findRecensione(id);
        Utente corrente = this.sessionData.getLoggedUtente();
        if (corrente.getId().equals(recensione.getUtente().getId())) {
            RecensioneDto recensioneDto = new RecensioneDto();
            recensioneDto.setTitolo(recensione.getTitolo());
            recensioneDto.setTesto(recensione.getTesto());
            recensioneDto.setVoto(recensione.getVoto());
            recensioneDto.setUtenteId(recensione.getUtente().getId());
            recensioneDto.setLibroId(recensione.getLibro().getId());
            model.addAttribute("recensione", recensioneDto);
            model.addAttribute("libro", recensione.getLibro());
            model.addAttribute("utente", recensione.getUtente());
            model.addAttribute("id", id);
            return "modificaRecensione.html";
        }else {
          return  "redirect:/visualizzaLibro/" + recensione.getLibro().getId();
        }
    }

    @PostMapping("/modificaRecensione/{id}")
    public String modificaRecensionePost(Model model, @Valid @ModelAttribute("recensione") RecensioneDto r,
                                         BindingResult result, @PathVariable Long id) {
        if (!result.hasErrors()) {
            Recensione recensione = this.recensioneService.findRecensione(id);
            recensione.setTitolo(r.getTitolo());
            recensione.setTesto(r.getTesto());
            recensione.setVoto(r.getVoto());
            Utente utente = this.utenteService.getUtentePerId(r.getUtenteId());
            recensione.setUtente(utente);
            Libro libro = this.libroService.getLibro(r.getLibroId());
            recensione.setLibro(libro);
            this.recensioneService.salvaRecensione(recensione);
            model.addAttribute("recensione", recensione);
            return "redirect:/visualizzaLibro/" + libro.getId();
        } else {
            throw new RuntimeException("Errore nel modificare una recensione");
        }
    }

    @PostMapping("/cancellaRecensione/{id}")
    public String eliminaRecensione(Model model, @PathVariable Long id) {
        Recensione recensione = this.recensioneService.findRecensione(id);
        Libro libro = recensione.getLibro();
        this.recensioneService.eliminaRecensione(recensione);
        return "redirect:/visualizzaLibro/" + libro.getId();
    }


}
