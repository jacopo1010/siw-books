package it.uniroma3.siw.controller;


import it.uniroma3.siw.model.Utente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutenticazioneController {


    @GetMapping("/login")
    public String login(){
         return "login.html";
    }

    @GetMapping("/register")
    public String iniviaModuloregistraUtente(Model model){
        return "register.html";
    }

    @GetMapping("/register")
    public String creaUtenze(){
        return "";
    }

    @PostMapping("/register")
    public String registraUtente(){

        return "";
    }
    @GetMapping("/default")
    public String defaultAfterLogin(){

        return "index.html";
    }

}
