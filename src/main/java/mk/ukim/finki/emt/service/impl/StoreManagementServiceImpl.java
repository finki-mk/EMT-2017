package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;
import mk.ukim.finki.emt.service.StoreManagementService;

/**
 * @author Riste Stojanov
 */
public class StoreManagementServiceImpl implements StoreManagementService {

  @Override
  public void createCategory(String name, Long parentId) {

  }

  @Override
  public void updateCategoryName(Long id, String newName) {

  }

  @Override
  public void changeCategoryParent(Long id, Long parentId) {

  }

  @Override
  public void removeCategory(Long id) throws CategoryInUseException {

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
  public void markExpired(Long invoiceId) {

  }

  @Override
  public DeliveryPackage markPayed(Long invoiceId) {
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
}
