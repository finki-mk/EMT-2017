package mk.ukim.finki.emt.listeners;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ristes on 3/2/16.
 */
@Component
public class BuildSearchIndex {

  @PersistenceContext
  private EntityManager entityManager;


  @PostConstruct
  public void init() {
    try {
      FullTextEntityManager fullTextEntityManager =
        Search.getFullTextEntityManager(entityManager);
      fullTextEntityManager.createIndexer().startAndWait();
    } catch (InterruptedException e) {
      System.out.println(
        "An error occurred trying to build the serach index: " +
          e.toString());
    }
  }


}
