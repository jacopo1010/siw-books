package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.service.UtenteService;
import it.uniroma3.siw.sessionData.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class homePageController {

    @Autowired private LibroService libroService;
    @Autowired private AutoreService autoreService;

    @GetMapping("/")
    public String homePage(){
        return "index.html";
    }

    @GetMapping("/index")
    public String showhomePage(Model model){
        return "index.html";
    }

    @PostMapping("/ricercaHome")
    public String ricercaBarraHome(Model model, @Param("keyword") String keyword) {
        Set<Libro> books = this.libroService.listAllKeyWord(keyword);
        List<Autore> autori = this.autoreService.listAll(keyword);
        model.addAttribute("books", books);
        model.addAttribute("autori", autori);
        model.addAttribute("keyword", keyword);
        return "ricercaBarra.html";
    }

}
