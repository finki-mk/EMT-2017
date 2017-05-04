package mk.ukim.finki.emt;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.persistence.BookRepository;
import mk.ukim.finki.emt.security.ClientBookPolicy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Store2017ApplicationTests {


  @Autowired
  BookRepository bookRepository;

  @Test
  public void contextLoads() {
    ClientBookPolicy policy=new ClientBookPolicy();
    policy.shouldApply(null, bookRepository, Book.class);
  }

}
