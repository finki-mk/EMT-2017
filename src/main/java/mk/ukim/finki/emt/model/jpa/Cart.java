package mk.ukim.finki.emt.model.jpa;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
  public LocalDateTime expiryDate;

  public Double totalPrice;


}
