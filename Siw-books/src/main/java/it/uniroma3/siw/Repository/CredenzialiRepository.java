package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Credenziali;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredenzialiRepository extends CrudRepository<Credenziali,Long> {

    public Optional<Credenziali> findByUsername(String username);

}
