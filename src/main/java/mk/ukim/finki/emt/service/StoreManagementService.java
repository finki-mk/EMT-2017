package mk.ukim.finki.emt.service;


import mk.ukim.finki.emt.model.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Deliverable;

/**
 * @author Riste Stojanov
 */
public interface StoreManagementService {

  void createCategory(
    String name,
    Long parentId
  );

  void updateCategoryName(
    Long id,
    String newName
  );

  void changeCategoryParent(
    Long id,
    Long parentId
  );

  void removeCategory(Long id)
    throws CategoryInUseException;

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

  void markExpired(
    Long invoiceId
  );

  Deliverable markPayed(
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


}
