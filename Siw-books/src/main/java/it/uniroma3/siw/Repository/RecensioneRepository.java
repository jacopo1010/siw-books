package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecensioneRepository extends JpaRepository<Recensione, Integer> {

   Optional<Recensione> findById(Long id);

   public boolean existsByUtenteId(Long utenteId);

}
