package mk.ukim.finki.emt.persistence;

import mk.ukim.finki.emt.model.jpa.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Riste Stojanov
 */
@Repository
public interface AuthorsRepository extends CrudRepository<Author, Long> {

  @Query(value = "select a from mk.ukim.finki.emt.model.jpa.Author a where a.nameAndLastName=:authorName")
  Author findByNameAndLastName(@Param("authorName") String authorName);
}
