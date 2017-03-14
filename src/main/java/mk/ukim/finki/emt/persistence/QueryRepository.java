package mk.ukim.finki.emt.persistence;

import mk.ukim.finki.emt.model.jpa.Book;
import org.springframework.data.domain.Page;

/**
 * @author Riste Stojanov
 */
public interface QueryRepository {

  Page<Book> findBooksByCategoryPaged(Long categoryId, int page, int pageSize);

  Page<Book> findPromotedBooks(int page, int pageSize);
}
