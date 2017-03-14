package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.persistence.CategoryRepository;
import mk.ukim.finki.emt.persistence.QueryRepository;
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

  @Autowired
  public QueryServiceImpl(
    QueryRepository bookRepository,
    CategoryRepository categoryRepository
  ) {
    this.queryRepository = bookRepository;
    this.categoryRepository = categoryRepository;
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
}
