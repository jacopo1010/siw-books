package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    public List<Libro> findAll();

   public Optional<Libro> findById(Long id);

    @Query("SELECT l FROM Libro l WHERE " +
            "LOWER(CONCAT(l.titolo, '', l.annoPubblicazione, '', l.id)) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Libro> searchByKeyword(@Param("keyword") String keyword);

    /**
     * query che seleziona i libri scritti
     * da un determinato autore
     */
    @Query(value = "SELECT l.* FROM Libro l JOIN libro_autore la ON la.libro_id = l.id WHERE la_autore.id =?1",nativeQuery = true)
    public List<Libro> findLibriScrittiDa(Long autoreId);

    public List<Libro> findTop10ByOrderByOraEDataCreazioneDesc();

    /**
     * fare una query che trova i migliori 10 Libri con la media di voti più alta
     */
    @Query(value = "SELECT l.*, AVG(r.voto) as media_voti " +
            "FROM libro l " +
            "JOIN recensione r ON l.id = r.libro_id " +
            "GROUP BY l.id " +
            "ORDER BY media_voti DESC", nativeQuery = true)
    public List<Libro> findByMediaVotiRecensioneMigliore();
}
