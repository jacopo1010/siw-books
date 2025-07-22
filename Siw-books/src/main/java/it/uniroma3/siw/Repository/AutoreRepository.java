package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AutoreRepository extends JpaRepository<Autore, Long> {

    public List<Autore> findAll();

    public List<Autore> findByNome(String name);

    public Optional<Autore> findById(Long aLong);

    @Query("select a from Autore a where concat(a.id,'',a.nome,'',a.cognome,'',a.nazionalita) LIKE %?1%")
    public List<Autore> findAllWithThatKeyWord(String keyWord);

    @Query(value = "SELECT l.* FROM libro l JOIN libro_autore la ON l.id = la.libro_id WHERE la.autore_id = ?1", nativeQuery = true)
    public List<Libro> findLibriByAutoreId(Long id);



}
