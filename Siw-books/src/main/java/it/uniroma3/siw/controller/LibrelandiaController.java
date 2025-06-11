package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.service.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibrelandiaController {

    @Autowired private LibroService libroService;
    @Autowired private AutoreService autoreService;
    @Autowired private RecensioneService recensioneService;


    @GetMapping("/admin/indexAdmin")
    public String adminHomepage(){
        return "/admin/indexAdmin.html";
    }



}
