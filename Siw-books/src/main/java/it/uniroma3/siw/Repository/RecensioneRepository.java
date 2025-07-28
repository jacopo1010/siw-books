package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecensioneRepository extends JpaRepository<Recensione, Integer> {

   Optional<Recensione> findById(Long id);

   // Per verificare se un utente ha già scritto una recensione per un determinato libro
   @Query("SELECT COUNT(r) > 0 FROM Recensione r WHERE r.libro.id = :bookId AND r.utente.id = :userId")
   public boolean existsByBookIdAndUserId(@Param("libroId") Long bookId, @Param("utenteId") Long userId);

   // Per prendere la recensione scritta dall'utente corrente (se esiste) per un determinato libro
   public Recensione findByUtenteIdAndLibroId(Long userId, Long libroId);
}
