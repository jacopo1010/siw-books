package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import it.uniroma3.siw.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping("/autori")
    public String autori(Model model) {
        List<Autore> autori = this.autoreService.getAllAutori();
        model.addAttribute("autori", autori);
        return "autori.html";
    }

    @PostMapping("/ricercaHomeAutori")
    public String ricercaBarraHome(Model model, @Param("keyword") String keyword) {
        List<Autore> autori = this.autoreService.listAllKeyword(keyword);
        model.addAttribute("autori", autori);
        model.addAttribute("keyword", keyword);
        return "ricercaBarraAutori.html";
    }


}
