package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtenteRepository extends CrudRepository<Utente,Long> {

    public Optional<Utente> findById(Long id);

    public Optional<Utente> findByEmail(String email);

    @Query("select u from Utente u where u.credenziali.username =?1")
    public Utente getUserByUsername(String username);


}
