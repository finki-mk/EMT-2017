package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookDetails;
import mk.ukim.finki.emt.model.jpa.Category;

import java.util.List;

/**
 * @author Riste Stojanov
 */
public interface BookServiceHelper {

  List<Category> getTopLevelCategories();

  List<Category> getSubCategories(Long categoryId);

  List<Book> getBooksInCategory(Long categoryId);

  BookDetails getBookDetails(Long bookId);

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

}
