package mk.ukim.finki.emt.service;


import mk.ukim.finki.emt.model.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookPicture;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;

import java.sql.SQLException;

/**
 * @author Riste Stojanov
 */
public interface StoreManagementService {


  Category createTopLevelCategory(String name);

  Category createCategory(
    String name,
    Long parentId
  );

  Category updateCategoryName(
    Long id,
    String newName
  );

  Category changeCategoryParent(
    Long id,
    Long parentId
  );

  void removeCategory(Long id)
    throws CategoryInUseException;

  Book createBook(
    String name,
    Long categoryId,
    String[] newAuthors,
    Long[] existingAuthors,
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


  void addBooksInStock(
    Long bookId,
    int quantity
  );

  void donateBooks(
    Long bookId,
    int quantity
  );

  void clearCart(
    Long cartId
  );

  void markInvoiceAsExpired(
    Long invoiceId
  );

  DeliveryPackage markInvoiceAsPayed(
    Long invoiceId
  );

  void preparedDelivery(
    Long deliverId
  );

  void shippedDelivery(
    Long deliveryId
  );

  void closeDeliveryWithoutConfirmation(
    Long deliveryId
  );


  BookPicture addBookPicture(Long bookId, byte[] bytes, String contentType) throws SQLException;
}
