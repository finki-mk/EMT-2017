package mk.ukim.finki.emt.model.jpa;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

  public LocalDateTime expiryDate;

  public Double totalPrice;



}
