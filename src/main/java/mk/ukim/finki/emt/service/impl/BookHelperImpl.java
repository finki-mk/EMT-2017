package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookDetails;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.service.BookServiceHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Riste Stojanov
 */
@Service
public class BookHelperImpl implements BookServiceHelper {

  @Override
  public List<Category> getTopLevelCategories() {
    return null;
  }

  @Override
  public List<Category> getSubCategories(Long categoryId) {
    return null;
  }

  @Override
  public List<Book> getBooksInCategory(Long categoryId) {
    return null;
  }

  @Override
  public BookDetails getBookDetails(Long bookId) {
    return null;
  }

  @Override
  public Book createBook(String name, Long categoryId, String[] authors, String isbn, Double price) {
    return null;
  }

  @Override
  public Book updateBook(Long bookId, String name, String[] authors, String isbn) {
    return null;
  }

  @Override
  public Book updateBookPrice(Long bookId, Double price) {
    return null;
  }

  @Override
  public Book updateBookCategory(Long bookId, Long newCategoryId) {
    return null;
  }
}
