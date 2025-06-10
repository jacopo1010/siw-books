package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.sessionData.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibrelandiaController {

    @Autowired private LibroService libroService;
    @Autowired private AutoreService autoreService;
    @Autowired private RecensioneService recensioneService;


    @GetMapping("/admin/indexAdmin")
    public String adminHompage(){
        return "/admin/indexAdmin.html";
    }



}
