package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Deliverable;


/**
 * @author Riste Stojanov
 */
public interface StoreManagementService {

  void createCategory(String name, Long parentId);

  void updateCategory(Long id, String newName);

  void changeCategoryParent(Long id, Long parentId);

  void removeCategory(Long id) throws CategoryInUseException;

  Book createBook(String name, Long categoryId, String[] authors, String isbn, Double price);

  Book updateBook(Long id, String name, Long categoryId, String[] authors, String isbn, Double price);

  void addBooksInStock(Long bookId, int quantity);

  void donateBooks(Long bookId, int quantity);

  void markExpired(Long invoiceId);

  Deliverable markPayed(Long invoiceId);

  void preparedDelivery(Long deliverId);

  void shippedDelivery(Long deliveryId);

  void closeDeliveryWithoutConfirmation(Long deliveryId);



}
