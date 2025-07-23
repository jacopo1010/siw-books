package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.LibroDto;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.service.RecensioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LibrelandiaController {

    @Autowired private LibroService libroService;
    @Autowired private AutoreService autoreService;

    @GetMapping("/admin/paginaModifiche")
    public String getAdminPage(){
        return "paginaModifiche.html";
    }

    @GetMapping("/admin/aggiungiLibro")
    public String getPaginaModifica(Model model){
        LibroDto nuovoLibro = new LibroDto();
        List<Autore> autori = this.autoreService.getAllAutori();
        model.addAttribute("autori", autori);
        model.addAttribute("libro",nuovoLibro);
        return "/admin/aggiungiLibro.html";
    }

    @PostMapping("/admin/aggiungiLibro")
    public String addLibro(@Valid @ModelAttribute("libro") LibroDto l, BindingResult bindingResult, Model model,
                              @RequestParam(name = "autori", required = false) List<Long> autoriId) {
        if (bindingResult.hasErrors()) {
            if (!(autoriId == null && autoriId.isEmpty())) {
                List<Autore> autori = this.autoreService.listAllById(autoriId);
                l.setScrittoriIds(autori);
            }
                Libro nuovolibro = new Libro(l.getTitolo(), l.getAnnopublicazione());
                nuovolibro.setScrittori(l.getScrittoriIds());
                this.libroService.saveLibro(nuovolibro);
                model.addAttribute("nuovolibro", nuovolibro);
                return "vediModifiche.html";
            } else {
                List<Autore> autori = this.autoreService.getAllAutori();
                model.addAttribute("autori", autori);
                model.addAttribute("libro", l);
                return "/admin/aggiungiLibro.html";
            }

    }


}
