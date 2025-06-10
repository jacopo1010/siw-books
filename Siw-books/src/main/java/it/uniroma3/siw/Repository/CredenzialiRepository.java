package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredenzialiRepository extends CrudRepository<Credenziali,Long> {

    public Optional<Credenziali> findByUsername(String username);

}
