package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.AutoreRepository;
import it.uniroma3.siw.Repository.LibroRepository;
import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AutoreService {

    @Autowired private AutoreRepository autoreRepository;
    @Autowired private LibroRepository libroRepository;

    public List<Autore> getAllAutori(){
        return this.autoreRepository.findAll();
    }

    @Transactional
    public Autore creaAutore(String nome, String cognome, LocalDateTime dataNascita,LocalDateTime dataMorte,String nazionalita,String url_foto) {
        Autore nuovo = new Autore(nome,cognome,dataNascita,dataMorte,nazionalita,url_foto);
        nuovo = this.autoreRepository.save(nuovo);
        return nuovo;
    }

    public Autore getAutore(Long id){
        return this.autoreRepository.findById(id).get();
    }

    @Transactional
    public void AddLibro(Long libroId,Long idAutore){
       Libro daAggiungere = this.libroRepository.findById(libroId).orElse(null);
       Autore autore = this.autoreRepository.findById(idAutore).orElse(null);
       autore.addLibriScritti(daAggiungere);
       this.autoreRepository.save(autore);
    }

    public List<Autore> listAll(String keyWord){
        if (keyWord != null){
            return this.autoreRepository.findAllWithThatKeyWord(keyWord);
        }
        return this.getAllAutori();
    }

    public List<Autore> listAllById(List<Long> ids){
          return this.autoreRepository.findAllById(ids);
    }

    @Transactional
    public void deleteAutore(Long id){
         this.autoreRepository.deleteById(id);
    }

}
