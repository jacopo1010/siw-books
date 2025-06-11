package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Autore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutoreRepository extends CrudRepository<Autore, Long> {

    public List<Autore> findAll();

}
