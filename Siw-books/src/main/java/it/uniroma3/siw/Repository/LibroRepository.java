package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    public Set<Libro> findAll();

    @Query(value = "SELECT l.* FROM libro l JOIN libro_autore la ON l.id = la.libro_id WHERE la.autore_id = :autoreId", nativeQuery = true)
    List<Libro> findByAutoreId(@Param("autoreId") Long autoreId);

}
