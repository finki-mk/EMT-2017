package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Category;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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


  private Book book;
  private Category child;
  private Category nephew;

  @After
  public void clearTestEntities() {

  }

  @Test
  public void testCrud() throws CategoryInUseException {
    book = serviceHelper.createBook("name", 1l, new String[]{AUTHOR_NAME}, "123", 300d);
    Assert.assertNotNull(book);
    Assert.assertNotNull(book.id);
    Assert.assertEquals(1, book.authors.size());
    Assert.assertEquals(AUTHOR_NAME, book.authors.get(0).nameAndLastName);


    List<Book> books = serviceHelper.getBooksInCategory(1l);
    Assert.assertEquals(1, books.size());
    Assert.assertEquals(true, books.contains(book));


    book = serviceHelper.updateBook(book.id, "newName", new String[]{"Riste Stojanov"}, "123");
    Assert.assertEquals("newName", book.name);



  }

}
