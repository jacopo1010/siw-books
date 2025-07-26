    package it.uniroma3.siw.controller;

    import it.uniroma3.siw.Dto.AutoreDto;
    import it.uniroma3.siw.model.Autore;
    import it.uniroma3.siw.model.Libro;
    import it.uniroma3.siw.Dto.LibroDto;
    import it.uniroma3.siw.service.AutoreService;
    import it.uniroma3.siw.service.LibroService;
    import jakarta.validation.Valid;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.io.InputStream;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.nio.file.StandardCopyOption;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    @Controller
    public class LibrelandiaController {

        @Autowired
        private LibroService libroService;
        @Autowired
        private AutoreService autoreService;

        @GetMapping("/admin/paginaModifiche")
        public String getAdminPage() {
            return "admin/paginaModifiche.html";
        }

        @GetMapping("/admin/vediModifiche")
        public String getPaginaDati(Model model) {
            List<Libro> libri = this.libroService.getAllLibri();
            List<Autore> autori = this.autoreService.getAllAutori();
            model.addAttribute("libri", libri);
            model.addAttribute("autori", autori);
            return "admin/vediModifiche.html";
        }

        @GetMapping("/admin/aggiungiLibro")
        public String getPaginaModifiche(Model model) {
            LibroDto nuovoLibro = new LibroDto();
            List<Autore> autori = this.autoreService.getAllAutori();
            model.addAttribute("autori", autori);
            model.addAttribute("libro", nuovoLibro);
            return "admin/aggiungiLibro.html";
        }

            @PostMapping("/admin/aggiungiLibro")
            public String addLibro(@Valid @ModelAttribute("libro") LibroDto l, BindingResult bindingResult, Model model,
                                   @RequestParam(name = "autore", required = false) List<Long> autoriId,@RequestParam(name = "immagini", required = false)List<MultipartFile> immagini) {
                if (!bindingResult.hasErrors()) {
                    if (autoriId != null && !autoriId.isEmpty()) {
                        List<Autore> autori = this.autoreService.listAllById(autoriId);
                        l.setScrittoriIds(autori);
                    }
                    Libro nuovolibro = this.libroService.creaLibro(l.getTitolo(), l.getAnnoPubblicazione(), l.getScrittoriIds(),immagini);
                    model.addAttribute("nuovolibro", nuovolibro);
                    return "admin/paginaModifiche.html";
                } else {
                    List<Autore> autori = this.autoreService.getAllAutori();
                    model.addAttribute("autori", autori);
                    model.addAttribute("libro", l);
                    return "admin/aggiungiLibro.html";
                }
            }

        @GetMapping("/admin/modificaLibro/{id}")
        public String modificaLibro(Model model, @PathVariable Long id) {
            List<Autore> autori = this.autoreService.getAllAutori();
            Libro daModificare = this.libroService.getLibro(id);
            LibroDto nuovoLibro = new LibroDto();
            nuovoLibro.setTitolo(daModificare.getTitolo());
            nuovoLibro.setAnnoPubblicazione(daModificare.getAnnoPubblicazione());
            nuovoLibro.setScrittoriIds(daModificare.getScrittori());
            model.addAttribute("id", id);
            model.addAttribute("libroDto", nuovoLibro);
            model.addAttribute("autori", autori);
            return "admin/modificaLibro.html";
        }

        @PostMapping("/admin/modificaLibro/{id}")
        public String modificaLibro(Model model, @PathVariable Long id, @Valid @ModelAttribute("libroDto") LibroDto libroDto, BindingResult bindingResult,
                                    @RequestParam(name = "autore", required = false) List<Long> autoriId) {
            if (!bindingResult.hasErrors()) {
                if (autoriId != null && !autoriId.isEmpty()) {
                    List<Autore> autori = this.autoreService.listAllById(autoriId);
                    libroDto.setScrittoriIds(autori);
                }
                Libro daModificare = this.libroService.getLibro(id);
                daModificare.setTitolo(libroDto.getTitolo());
                daModificare.setAnnoPubblicazione(libroDto.getAnnoPubblicazione());
                for (Autore autore : libroDto.getScrittoriIds()) {
                    this.libroService.addAutore(autore.getId(), daModificare.getId());
                }
                this.libroService.saveLibro(daModificare);
                return "admin/paginaModifiche.html";
            }
            throw new IllegalArgumentException("il libro non e' valido");
        }

        @PostMapping("/admin/cancellaLibro/{id}")
        public String cancellaLibro(@PathVariable Long id, Model model) {
            Libro daCancellare = this.libroService.getLibro(id);
            this.libroService.deleteLibro(daCancellare);
            model.addAttribute("id", id);
            return "admin/paginaModifiche.html";
        }

        @GetMapping("/admin/aggiungiAutore")
        public String aggiungiAutore(Model model) {
            AutoreDto daAutore = new AutoreDto();
            model.addAttribute("autore", daAutore);
            return "admin/aggiungiAutore.html";
        }

        @PostMapping("/admin/aggiungiAutore")
        public String aggiungiAutorePost(Model model, @Valid @ModelAttribute("autore") AutoreDto daAutore,
                                         BindingResult bindingResult,@RequestParam("url_foto") MultipartFile file) {
            if (!bindingResult.hasErrors()) {
                Autore autore = this.autoreService.creaAutore(daAutore.getNome(), daAutore.getCognome(), daAutore.getDataNascita(), daAutore.getDataNascita(), daAutore.getNazionalita(),daAutore.getUrl_foto());
                this.autoreService.salva(autore);
                return "admin/paginaModifiche.html";
            } else {
                System.err.println("errori" +  bindingResult.getAllErrors());
                throw new IllegalArgumentException("L'autore non valido");
            }
        }

        @GetMapping("/admin/modificaAutore/{id}")
        public String modificaAutore(Model model, @PathVariable Long id) {
            AutoreDto daAutore = new AutoreDto();
            model.addAttribute("autoreDto", daAutore);
            return "admin/modificaAutore.html";
        }

        @PostMapping("/admin/modificaAutore/{id}")
        public String modificaAutorePost(Model model, @PathVariable Long id, @Valid @ModelAttribute("autoreDto") AutoreDto daAutore,
                                         BindingResult bindingResult) {
            if (!bindingResult.hasErrors()) {
                Autore autore = this.autoreService.getAutore(id);
                autore.setNome(daAutore.getNome());
                autore.setCognome(daAutore.getCognome());
                autore.setDataNascita(daAutore.getDataNascita());
                autore.setDataNascita(daAutore.getDataNascita());
               // autore.setUrl_foto(daAutore.getUrl_foto());
                autore.setNazionalita(daAutore.getNazionalita());
                this.autoreService.salva(autore);
                return "admin/paginaModifiche.html";
            } else {
                throw new IllegalArgumentException("L'autore non valido");
            }
        }

        @PostMapping("/admin/cancellaAutore/{id}")
        public String cancellaAutore(Model model,@PathVariable  Long id) {
            Autore daCancellare = this.autoreService.getAutore(id);
            this.autoreService.deleteAutore(id);
            model.addAttribute("id", id);
            return "admin/paginaModifiche.html";
        }


    }
