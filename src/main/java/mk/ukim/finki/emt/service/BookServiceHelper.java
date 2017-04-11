package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookDetails;
import mk.ukim.finki.emt.model.jpa.BookPicture;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Riste Stojanov
 */
public interface BookServiceHelper {

  List<Book> getBooksInCategory(Long categoryId);

  BookDetails getBookDetails(Long bookId);

  Book createBook(
    String name,
    Long categoryId,
    String[] authors,
    Long[] existingAuthors, String isbn,
    Double price
  );


  Book createBook(
    String name,
    Long categoryId,
    String[] authors,
    String isbn,
    Double price
  );

  Book updateBook(
    Long bookId,
    String name,
    String[] authors,
    String isbn
  );

  Book updateBookPrice(
    Long bookId,
    Double price
  );

  Book updateBookCategory(
    Long bookId,
    Long newCategoryId
  );


  BookPicture addBookPicture(
    Long bookId,
    byte[] bytes,
    String contentType) throws SQLException;

}
