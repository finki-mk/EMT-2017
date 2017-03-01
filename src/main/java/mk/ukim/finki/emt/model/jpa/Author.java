package mk.ukim.finki.emt.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

  public String nameAndLastName;
}
