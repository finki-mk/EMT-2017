package mk.ukim.finki.emt;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Payment;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.service.impl.PaypalServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringWebShowcaseApplicationTests {

  @Autowired
  PaypalServiceImpl paypalService;

  @Test
  public void contextLoads() {


    List<Book> products = new ArrayList<>();
    Book p1 = new Book();
    p1.name = "Book1 1";
    p1.price = 10d;
    Book p2 = new Book();
    products.add(p1);
    p2.name = "Book 2";
    p2.price = 20d;
    products.add(p2);

    Address billingAddress = new Address();
    billingAddress.setCity("Johnstown");
    billingAddress.setCountryCode("US");
    billingAddress.setLine1("52 N Main ST");
    billingAddress.setPostalCode("43210");
    billingAddress.setState("OH");

    // ###CreditCard
    // A resource representing a credit card that can be
    // used to fund a payment.
    CreditCard creditCard = new CreditCard();
    creditCard.setBillingAddress(billingAddress);
    creditCard.setCvv2(111);
    creditCard.setExpireMonth(06);
    creditCard.setExpireYear(2020);
    creditCard.setFirstName("Joe");
    creditCard.setLastName("Shopper");
    creditCard.setNumber("4032030455141821");
    creditCard.setType("visa");

    List<Payment> payments = paypalService.executeCreditCardPayment(creditCard, products);
    Assert.assertEquals("payments number missmatch", 2, payments.size());
    System.out.println(payments);
  }

}
