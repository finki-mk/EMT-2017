package mk.ukim.finki.emt.model.jpa;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "checkouts")
public class Checkout extends BaseEntity {

  @OneToOne
  public Cart cart;

  @ManyToOne
  public DeliveryInfo deliveryInfo;

  @ManyToOne
  public ContactInfo contactInfo;


}
