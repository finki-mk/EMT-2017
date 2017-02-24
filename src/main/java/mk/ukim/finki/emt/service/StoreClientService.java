package mk.ukim.finki.emt.service;

import com.sun.corba.se.pept.transport.ContactInfo;
import mk.ukim.finki.emt.model.jpa.*;
import mk.ukim.finki.emt.model.search.BookSearchCriteria;
import org.hibernate.annotations.Check;

import java.util.List;


/**
 * @author Riste Stojanov
 */
public interface StoreClientService {

  Cart takeCart();

  List<Book> findBooks(BookSearchCriteria criteria);

  List<Category> getTopLevelCategories();

  List<Category> getSubCategories(Long categoryId);

  List<Book> getBooksInCategory(Long categoryId);

  BookDetails getBookDetails(Long bookId);

  void addToCart(Long cartId, Long bookId, int quantity);

  void removeFromCart(Long cartId, Long bookId, int quantity);

  Checkout startCheckout(Long cartId);


  DeliveryInfo provideDeliveryInfo(
    Long checkoutId,
    String country,
    String city,
    String postalCode,
    String address);

  ContactInfo provideContactInfo(
    Long checkoutId,
    String firstName,
    String lastName,
    String phone
  );

  void provideDeliveryAndContactInfoFromCustomerCard(
    Long checkoutId,
    Long customerCardId
  );

  void provideCoupon(
    Long checkoutId,
    String coupon
  );

  Invoice providePaymentInfo(
    Long checkoutId,
    String cardNumber,
    String cardHolder,
    String cardType,
    String cvs,
    String expiryDate
  );


  void confirmDelivery(
    Long invoiceId,
    Integer rating,
    String comment
  );

}
