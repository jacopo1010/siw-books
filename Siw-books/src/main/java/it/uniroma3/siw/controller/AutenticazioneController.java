package it.uniroma3.siw.controller;


import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.model.UtenteDto;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.UtenteService;
import it.uniroma3.siw.sessionData.SessionData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import static it.uniroma3.siw.model.Credenziali.ADMIN_ROLE;

@Controller
public class AutenticazioneController {

    @Autowired private UtenteService utenteService;
    @Autowired private CredenzialiService credenzialiService;
    @Autowired private SessionData sessionData;


    @GetMapping("/login")
    public String login(){
         return "login.html";
    }

    @GetMapping("/register")
    public String iniviaModuloregistraUtente(Model model){
        UtenteDto utenteDto = new UtenteDto();
        model.addAttribute("utente1", utenteDto);
        return "register.html";
    }

    @PostMapping("/register")
    public String registraUtente(@Valid @ModelAttribute("utente1") UtenteDto u, BindingResult bindingResult, Model model){

        if (!bindingResult.hasErrors()){
           Utente nuovoUtente = this.utenteService.creaUtente(u.getNome(),u.getCognome(),u.getEmail());
           Credenziali credenziali = this.credenzialiService.creaCredenziali(u.getUsername(),u.getPassword(),Credenziali.DEFAULT_ROLE,nuovoUtente);
           model.addAttribute("utente",nuovoUtente);
           return "login.html";
        }
        System.out.println("Errori nel form: " + bindingResult.getAllErrors());
        return "register.html";
    }


    @GetMapping("/success")
    public String defaultAfterLogin(Model model){
        UserDetails userD = null;
        Credenziali credenziali = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken){
            return "index.html";
        }else {
            userD = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            credenziali = this.credenzialiService.getCredenzialiPerUsername(userD.getUsername());
            model.addAttribute("utente", userD);

            if (credenziali.getRuolo().trim().equals(ADMIN_ROLE)){
                return "/admin/indexAdmin.html";
            }
        }
        return "index.html";
    }

}
