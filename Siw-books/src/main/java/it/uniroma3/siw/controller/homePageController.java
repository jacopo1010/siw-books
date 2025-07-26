package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.service.UtenteService;
import it.uniroma3.siw.sessionData.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class homePageController {

    @Autowired private LibroService libroService;
    @Autowired private AutoreService autoreService;
    @Autowired private RecensioneService recensioneService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("libri", this.libroService.getAllLibri());
        return "index.html";
    }

    @GetMapping("/index")
    public String showhomePage(Model model){
        model.addAttribute("libri",this.libroService.getAllLibri());
        return "index.html";
    }

    @PostMapping("/ricercaHome")
    public String ricercaBarraHome(Model model, @Param("keyword") String keyword) {
        List<Libro> books = this.libroService.listAllKeyWord(keyword);
        List<Autore> autori = this.autoreService.listAllKeyword(keyword);
        model.addAttribute("libri", books);
        model.addAttribute("autori", autori);
        model.addAttribute("keyword", keyword);
        for(Libro libro : books){
            System.err.println(libro.getScrittori().size());
        }
        return "ricercaBarra.html";
    }

    @GetMapping("/visualizzaLibro/{id}")
    public String vediLibroSpecifico(Model model, @PathVariable("id") Long id){
        Libro book = this.libroService.getLibro(id);
        List<Recensione> recensioni = this.recensioneService.listaRecensioni();
        model.addAttribute("libro", book);
        model.addAttribute("recensioni", recensioni);
        return "vediLibro.html";
    }


}
