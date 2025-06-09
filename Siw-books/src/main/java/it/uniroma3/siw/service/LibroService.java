package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.LibroRepository;
import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class LibroService {

    @Autowired private LibroRepository libroRepository;

    public Libro creaLibro(String titolo, LocalDate t, String urlI, Autore aut){
        Libro libro = new Libro(titolo,t);
        libro.addAutore(aut);
        libro.addFoto(urlI);
        libro = this.libroRepository.save(libro);
        return libro;
    }

    public Libro getLibro(Long Id){
        Libro daCercare = this.libroRepository.findById(Id).orElse(null);
        return daCercare;
    }

    public Set<Libro> getAllLibri(){
        Set<Libro> sette = this.libroRepository.findAll();
        return sette;
    }

}
