package mk.ukim.finki.emt.model.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;


/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

  public String name;

  public Double price;

  public String isbn;

  public Integer quantityInStock;

  @ManyToOne
  public Category category;

  @ManyToMany
  public List<Author> authors;
}
