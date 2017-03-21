package mk.ukim.finki.emt.persistence;

import mk.ukim.finki.emt.model.jpa.Author;
import mk.ukim.finki.emt.model.jpa.BookPicture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Riste Stojanov
 */
@Repository
public interface BookPictureRepository extends CrudRepository<BookPicture, Long> {

  BookPicture findByBookId(Long id);
}
