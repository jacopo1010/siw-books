package it.uniroma3.siw.Repository;

import it.uniroma3.siw.model.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    public Set<Libro> findAll();

   /** @Query("select l from Libro l join libro_scrittori s on l.id = s. ")
    List<Libro> findByAutoreId(Long autoreId);
  **/
}
