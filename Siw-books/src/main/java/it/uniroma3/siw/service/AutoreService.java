package it.uniroma3.siw.service;

import it.uniroma3.siw.Repository.AutoreRepository;
import it.uniroma3.siw.model.Autore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreService {

    @Autowired private AutoreRepository autoreRepository;

    public List<Autore> getAllAutori(){
        return this.autoreRepository.findAll();
    }


}
