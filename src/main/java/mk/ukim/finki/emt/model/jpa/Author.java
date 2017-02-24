package mk.ukim.finki.emt.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ristes on 2/23/17.
 */
@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

  public String nameAndLastName;
}
