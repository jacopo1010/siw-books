package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.AutoreRepository;
import it.uniroma3.siw.Repository.LibroRepository;
import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class LibroService {

    @Autowired private LibroRepository libroRepository;
    @Autowired private AutoreRepository autoreRepository;

    @Transactional
    public Libro creaLibro(String titolo, LocalDate t,List<Autore> autori){
        Libro libro = new Libro(titolo,t,autori);
        //libro.addFoto(urlI);
        libro = this.libroRepository.save(libro);
        return libro;
    }

    @Transactional
    public void addAutore(Long autoreId, Long libroId) {
        Autore autore = this.autoreRepository.findById(autoreId).orElseThrow(() -> new IllegalArgumentException("Autore non trovato"));
        Libro libro = this.libroRepository.findById(libroId).orElseThrow(() -> new IllegalArgumentException("Libro non trovato"));
        libro.addAutore(autore);
        this.libroRepository.save(libro);
    }


    public Libro getLibro(Long Id){
          return this.libroRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Libro non trovato"));
    }

    public List<Libro> getAllLibri(){
         return this.libroRepository.findAll();
    }

    public List<Libro> listAllKeyWord(String keyWord){
        if (keyWord != null){
            return this.libroRepository.findAllWithThatKeyword(keyWord);
        }
        return this.getAllLibri();
    }

    public Libro saveLibro(Libro libro){
        return this.libroRepository.save(libro);
    }

    @Transactional
    public void deleteLibro(Long Id){
        this.libroRepository.deleteById(Id);
    }
}
