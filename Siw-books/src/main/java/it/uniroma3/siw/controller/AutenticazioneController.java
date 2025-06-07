package it.uniroma3.siw.controller;


import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.model.UtenteDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutenticazioneController {


    @GetMapping("/login")
    public String login(){
         return "login.html";
    }

    @GetMapping("/register")
    public String iniviaModuloregistraUtente(Model model){
        model.addAttribute("Utente", new UtenteDto());
        return "register.html";
    }

    @PostMapping("/register")
    public String registraUtente(@Valid @ModelAttribute("Utente") UtenteDto u, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "register.html";
        }


        return "";
    }
    @GetMapping("/default")
    public String defaultAfterLogin(){

        return "index.html";
    }

}
