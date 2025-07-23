package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.model.LibroDto;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class LibrelandiaController {

    @Autowired private LibroService libroService;
    @Autowired private AutoreService autoreService;

    @GetMapping("/admin/paginaModifiche")
    public String getAdminPage(){
        return "/admin/paginaModifiche.html";
    }

    @GetMapping("/admin/vediModifiche")
    public String getPaginaDati(Model model){
        List<Libro> libri = this.libroService.getAllLibri();
        List<Autore> autori = this.autoreService.getAllAutori();
        model.addAttribute("libri", libri);
        model.addAttribute("autori", autori);
        return "/admin/vediModifiche.html";
    }

    @GetMapping("/admin/aggiungiLibro")
    public String getPaginaModifiche(Model model){
        LibroDto nuovoLibro = new LibroDto();
        List<Autore> autori = this.autoreService.getAllAutori();
        model.addAttribute("autori", autori);
        model.addAttribute("libro",nuovoLibro);
        return "/admin/aggiungiLibro.html";
    }

    @PostMapping("/admin/aggiungiLibro")
    public String addLibro(@Valid @ModelAttribute("libro") LibroDto l, BindingResult bindingResult, Model model,
                              @RequestParam(name = "autori", required = false) List<Long> autoriId) {
        if (!bindingResult.hasErrors()) {
            if (autoriId != null && !autoriId.isEmpty()) {
                List<Autore> autori = this.autoreService.listAllById(autoriId);
                l.setScrittoriIds(autori);
            }
                Libro nuovolibro = this.libroService.creaLibro(l.getTitolo(),l.getAnnoPubblicazione(),l.getScrittoriIds());
                model.addAttribute("nuovolibro", nuovolibro);
                return "/admin/paginaModifiche.html";
        } else {
            List<Autore> autori = this.autoreService.getAllAutori();
            model.addAttribute("autori", autori);
            model.addAttribute("libro", l);
            return "/admin/aggiungiLibro.html";
        }

    }

    @GetMapping("/admin/modificaLibro/{id}")
    public String modificaLibro(Model model, @PathVariable Long id){
         List<Autore> autori = this.autoreService.getAllAutori();
         Libro daModificare = this.libroService.getLibro(id);
         LibroDto  nuovoLibro = new LibroDto();
         nuovoLibro.setTitolo(daModificare.getTitolo());
         nuovoLibro.setAnnoPubblicazione(daModificare.getAnnoPubblicazione());
         nuovoLibro.setScrittoriIds(daModificare.getScrittori());
         model.addAttribute("libroDto", nuovoLibro);
         model.addAttribute("autori", autori);
         return "/admin/modificaLibro.html";
    }

    @PostMapping("/admin/modificaLibro/{id}")
    public String modificaLibro(Model model, @PathVariable Long id,@Valid @ModelAttribute("libroDto") LibroDto libroDto,BindingResult bindingResult,
                                @RequestParam(name = "autori", required = false) List<Long> autoriId){
         if (!bindingResult.hasErrors()) {
             if (autoriId != null && !autoriId.isEmpty()) {
                 List<Autore> autori = this.autoreService.getAllAutori();
                 libroDto.setScrittoriIds(autori);
             }
             Libro daModificare = this.libroService.getLibro(id);
             daModificare.setTitolo(libroDto.getTitolo());
             daModificare.setAnnoPubblicazione(libroDto.getAnnoPubblicazione());
             daModificare.setScrittori(libroDto.getScrittoriIds());
             this.libroService.saveLibro(daModificare);
             return "/admin/vediModificaLibro.html";
         }
         throw new IllegalArgumentException("L'libro non valido");
    }

     @PostMapping("/admin/cancellaLibro/{id}")
     public String cancellaLibro(@PathVariable Long id){
        this.libroService.deleteLibro(id);
        return "/admin/paginaModifiche.html";
     }

}
