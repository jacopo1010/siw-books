package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.sessionData.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class LibroController {

    @Autowired private LibroService libroService;
    @Autowired private SessionData sessionData;

    @GetMapping("/libri")
    public String getAllBooks(Model model) {
        List<Libro> libri = this.libroService.getAllLibri();
        model.addAttribute("libri",libri);
        return "libri.html";
    }

}
