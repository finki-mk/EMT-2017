package mk.ukim.finki.emt.model.jpa;

import javax.annotation.Generated;
import javax.persistence.*;


/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  public String name;

  public Double price;
}
