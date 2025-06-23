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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibrelandiaController {

    @Autowired private LibroService libroService;
    @Autowired private AutoreService autoreService;
    @Autowired private RecensioneService recensioneService;


    @GetMapping("/admin/indexAdmin")
    public String getAdminPage(){
        return "/admin/indexAdmin.html";
    }

    @GetMapping("/admin/aggiungiLibro")
    public String getPaginaModifica(Model model){
        LibroDto nuovoLibro = new LibroDto();
        model.addAttribute("prodotto",nuovoLibro);
        return "/admin/aggiungiLibro.html";
    }

    @PostMapping("/admin/aggiungiLibro")
    public String addProdotto(@Valid @ModelAttribute("prodotto") LibroDto l,  BindingResult bindingResult, Model model){
         if (!bindingResult.hasErrors()){
             List<Autore> autori = this.autoreService.getAllAutori();
             model.addAttribute("autori", autori);

         }
         return "";
    }


}
