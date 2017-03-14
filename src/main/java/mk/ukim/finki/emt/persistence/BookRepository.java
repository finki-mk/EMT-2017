package mk.ukim.finki.emt.persistence;

import mk.ukim.finki.emt.model.jpa.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Riste Stojanov
 */
public interface BookRepository extends CrudRepository<Book, Long>,
  JpaSpecificationExecutor<Book> {

}
