package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.sessionData.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homePageController {


    @GetMapping("/")
    public String homePage(){
        return "index.html";
    }

}
