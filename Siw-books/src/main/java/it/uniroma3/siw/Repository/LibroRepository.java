package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    public Set<Libro> findAll();

   public Optional<Libro> findById(Long aLong);

    @Query("select l from Libro l where CONCAT(l.id,'',l.titolo) LIKE %?1%")
    public Set<Libro> findAllWithThatKeyword(String keyword);

    @Query(value = "select a.* from Autore a join libro_autore la on la.autore_id = a.id  where a.id =?1", nativeQuery = true)
    public Set<Autore> findAutoriDelLibro(Long idAutore);


}
