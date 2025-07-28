package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AutoreRepository extends JpaRepository<Autore, Long> {

    public List<Autore> findAll();

    public List<Autore> findByNome(String name);

    public Optional<Autore> findById(Long aLong);

    @Query(value = "SELECT l.* FROM libro l JOIN libro_autore la ON l.id = la.libro_id WHERE la.autore_id = ?1", nativeQuery = true)
    public List<Libro> findLibriByAutoreId(Long id);

    /**
     * Query per trovare tutti gli autori che hanno scritto un libro x
     */

    @Query(value = "SELECT a.* FROM Autore a JOIN libro_autore la ON a.id = la.autore_id WHERE la.libro_id =?1", nativeQuery = true)
    public List<Autore> autoriCheHannoScrittoUnLibroX(Long libroId);

    @Query("SELECT a FROM Autore a WHERE " +
            "LOWER(CONCAT(a.nome, '', a.cognome, '', a.id,'',a.nazionalita)) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Autore> searchByKeyword(@Param("keyword") String keyword);
}
