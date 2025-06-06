package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends CrudRepository<Utente,Long> {
}
