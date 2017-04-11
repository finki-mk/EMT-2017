package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.jpa.*;
import mk.ukim.finki.emt.persistence.AuthorsRepository;
import mk.ukim.finki.emt.persistence.BookPictureRepository;
import mk.ukim.finki.emt.persistence.BookRepository;
import mk.ukim.finki.emt.persistence.CategoryRepository;
import mk.ukim.finki.emt.service.BookServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Riste Stojanov
 */
@Service
public class BookHelperImpl implements BookServiceHelper {

  /**
   * TODO: move this into book details helper
   */
  @Autowired
  BookPictureRepository bookPictureRepository;
  private CategoryRepository categoryRepository;
  private BookRepository bookRepository;
  private AuthorsRepository authorsRepository;


  @Autowired
  public BookHelperImpl(
    CategoryRepository categoryRepository,
    BookRepository bookRepository,
    AuthorsRepository authorsRepository
  ) {
    this.categoryRepository = categoryRepository;
    this.bookRepository = bookRepository;
    this.authorsRepository = authorsRepository;
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
  public Book createBook(String name, Long categoryId, String[] authors, Long[] existingAuthors, String isbn, Double price) {
    Book book = createBookWithNewAuthors(name, categoryId, authors, isbn, price);
    for (Long authorId : existingAuthors) {
      book.authors.add(authorsRepository.findOne(authorId));
    }
    return bookRepository.save(book);
  }


  @Override
  public Book createBook(String name, Long categoryId, String[] authors, String isbn, Double price) {
    Book book = createBookWithNewAuthors(name, categoryId, authors, isbn, price);
    return bookRepository.save(book);
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

  @Override
  public BookPicture addBookPicture(Long bookId, byte[] bytes, String contentType) throws SQLException {
    BookPicture bookPicture = new BookPicture();
    bookPicture.book = bookRepository.findOne(bookId);
    FileEmbeddable picture = new FileEmbeddable();
    picture.contentType = contentType;
    picture.data = new SerialBlob(bytes);
    picture.size = bytes.length;
    picture.fileName = bookPicture.book.name;
    bookPicture.picture = picture;
    return bookPictureRepository.save(bookPicture);
  }


  private Author createAuthor(String authorName) {
    Author author = new Author();
    author.nameAndLastName = authorName;
    author = authorsRepository.save(author);
    return author;
  }


  private Book createBookWithNewAuthors(String name, Long categoryId, String[] authors, String isbn, Double price) {
    Book book = new Book();
    book.name = name;
    book.isbn = isbn;
    book.price = price;
    book.category = categoryRepository.findOne(categoryId);
    for (String authorName : authors) {
      Author author = createAuthor(authorName);
      book.authors.add(author);
    }
    return book;
  }
}
