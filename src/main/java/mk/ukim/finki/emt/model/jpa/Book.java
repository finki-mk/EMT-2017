package mk.ukim.finki.emt.model.jpa;

import javax.persistence.*;
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

  public Boolean promoted;

  @ManyToMany(fetch = FetchType.EAGER)
  public List<Author> authors;
}
