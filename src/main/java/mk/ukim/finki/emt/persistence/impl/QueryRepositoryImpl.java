package mk.ukim.finki.emt.persistence.impl;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.persistence.BookRepository;
import mk.ukim.finki.emt.persistence.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

/**
 * @author Riste Stojanov
 */
@Repository
public class QueryRepositoryImpl implements QueryRepository {

  BookRepository bookRepository;

  @Autowired
  public QueryRepositoryImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }


  @Override
  public Page<Book> findBooksByCategoryPaged(Long categoryId, int page, int pageSize) {
    return bookRepository.findAll(
      (book, cq, cb) -> cb.equal(book.join("category").get("id"), categoryId),
      new PageRequest(page, pageSize)
    );
  }

  @Override
  public Page<Book> findPromotedBooks(int page, int pageSize) {
    return bookRepository.findAll(
      (book, cq, cb) -> cb.equal(book.get("promoted"), true),
      new PageRequest(page, pageSize)
    );
  }
}
