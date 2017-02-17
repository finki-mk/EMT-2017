package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.jpa.*;
import mk.ukim.finki.emt.model.search.BookSearchCriteria;

import java.util.List;


/**
 * @author Riste Stojanov
 */
public interface StoreClientService {

  List<Category> getTopLevelCategories();

  List<Category> getSubCategories(Long categoryId);

  List<Book> findBook(BookSearchCriteria criteria);

  List<Book> findBookInCategory(Long categoryId);

  void addToCart(Long cartId, Long bookId, int quantity);

  void removeFromCart(Long cartId, Long bookId, int quantity);

  void clearExpiredCart(Long cartId);

  Checkout checkout(Long cartId);

  void provideShippingInfo(Long checkoutId, String city, String postalCode, String address);

  void provideContactInfo(Long checkoutId, String phone);

  Invoice pay(Long checkoutId, String cardNumber, String owner, String cardType, String cvs, String cardExpirationDate);

  void revokeCheckout(Long checkoutId);

  void confirmDelivery(Long deliveryId);

  User registerUser(String username, String password, String email);


}
