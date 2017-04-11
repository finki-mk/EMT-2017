package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.persistence.BookRepository;
import mk.ukim.finki.emt.persistence.CategoryRepository;
import mk.ukim.finki.emt.persistence.SearchRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Riste Stojanov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BookHelperTest {

  public static final String AUTHOR_NAME = "Riste Stojanov";
  @Autowired
  BookServiceHelper serviceHelper;

  @Autowired
  SearchRepository searchRepository;

  @Autowired
  BookRepository bookRepository;

  @Autowired
  CategoryRepository categoryRepository;


  private Book book;
  private Category child;
  private Category nephew;

  @After
  public void clearTestEntities() {

  }

  @Test
  public void testCrud() throws CategoryInUseException {

    Category c = new Category();
    c.name = "base";
    Category base = categoryRepository.save(c);
    Book book = serviceHelper.createBook("Java essentials", base.id, new String[]{"Joshua Bloch"}, "123", 100d);


//    List<Book> foundBooks = searchRepository.searchKeyword(Book.class, "java", "name", "category.name");


  }

}
