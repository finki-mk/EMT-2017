package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookPicture;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.persistence.BookPictureRepository;
import mk.ukim.finki.emt.persistence.CategoryRepository;
import mk.ukim.finki.emt.persistence.QueryRepository;
import mk.ukim.finki.emt.persistence.SearchRepository;
import mk.ukim.finki.emt.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Riste Stojanov
 */
@Service
public class QueryServiceImpl implements QueryService {

  QueryRepository queryRepository;

  CategoryRepository categoryRepository;

  BookPictureRepository bookPictureRepository;

  @Autowired
  SearchRepository searchRepository;

  @Autowired
  public QueryServiceImpl(
    QueryRepository bookRepository,
    CategoryRepository categoryRepository,
    BookPictureRepository bookPictureRepository
  ) {
    this.queryRepository = bookRepository;
    this.categoryRepository = categoryRepository;
    this.bookPictureRepository = bookPictureRepository;
  }

  @Override
  public Page<Book> getBooksInCategory(Long categoryId, int page, int pageSize) {
    return queryRepository.findBooksByCategoryPaged(categoryId, page, pageSize);
  }

  @Override
  public Page<Book> getPromotedBooks(int page, int pageSize) {
    return queryRepository.findPromotedBooks(page, pageSize);

  }

  @Override
  public List<Category> findTopLevelCategories() {
    return categoryRepository.findByParentIsNull();
  }

  @Override
  public BookPicture getByBookId(Long bookId) {
    return bookPictureRepository.findByBookId(bookId);
  }

  @Override
  public List<Book> searchBook(String query) {
    return searchRepository.searchPhrase(
      Book.class,
      query,
      "name", "isbn", "category.name", "authors.nameAndLastName");
  }
}
