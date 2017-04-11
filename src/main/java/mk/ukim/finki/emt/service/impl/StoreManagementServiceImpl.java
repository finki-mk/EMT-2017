package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookPicture;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;
import mk.ukim.finki.emt.service.BookServiceHelper;
import mk.ukim.finki.emt.service.CategoryServiceHelper;
import mk.ukim.finki.emt.service.StoreManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author Riste Stojanov
 */
@Service
public class StoreManagementServiceImpl implements StoreManagementService {

  @Autowired
  private CategoryServiceHelper categoryServiceHelper;

  @Autowired
  private BookServiceHelper bookServiceHelper;

  @Override
  public Category createTopLevelCategory(String name) {
    return categoryServiceHelper.createTopLevelCategory(name);
  }

  @Override
  public Category createCategory(String name, Long parentId) {
    return categoryServiceHelper.createCategory(name, parentId);
  }

  @Override
  public Category updateCategoryName(Long id, String newName) {
    return categoryServiceHelper.updateCategoryName(id, newName);
  }

  @Override
  public Category changeCategoryParent(Long id, Long parentId) {
    return categoryServiceHelper.changeCategoryParent(id, parentId);
  }

  @Override
  public void removeCategory(Long id) throws CategoryInUseException {
    categoryServiceHelper.removeCategory(id);
  }

  @Override
  public Book createBook(String name, Long categoryId, String[] newAuthors,
                         Long[] existingAuthors, String isbn, Double price) {
    return bookServiceHelper.createBook(name, categoryId, newAuthors, existingAuthors, isbn, price);
  }

  @Override
  public Book updateBook(Long bookId, String name, String[] authors, String isbn) {
    return bookServiceHelper.updateBook(bookId, name, authors, isbn);
  }

  @Override
  public Book updateBookPrice(Long bookId, Double price) {
    return bookServiceHelper.updateBookPrice(bookId, price);
  }

  @Override
  public Book updateBookCategory(Long bookId, Long newCategoryId) {
    return bookServiceHelper.updateBookCategory(bookId, newCategoryId);
  }

  @Override
  public void addBooksInStock(Long bookId, int quantity) {

  }

  @Override
  public void donateBooks(Long bookId, int quantity) {

  }

  @Override
  public void clearCart(Long cartId) {

  }

  @Override
  public void markInvoiceAsExpired(Long invoiceId) {

  }

  @Override
  public DeliveryPackage markInvoiceAsPayed(Long invoiceId) {
    return null;
  }

  @Override
  public void preparedDelivery(Long deliverId) {

  }

  @Override
  public void shippedDelivery(Long deliveryId) {

  }

  @Override
  public void closeDeliveryWithoutConfirmation(Long deliveryId) {

  }

  @Override
  public BookPicture addBookPicture(Long bookId, byte[] bytes, String contentType) throws SQLException {
    return bookServiceHelper.addBookPicture(bookId, bytes, contentType);
  }
}
