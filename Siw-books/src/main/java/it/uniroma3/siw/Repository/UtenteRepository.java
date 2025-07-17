package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtenteRepository extends CrudRepository<Utente,Long> {

    public Optional<Utente> findById(Long id);

}
