package mk.ukim.finki.emt.model.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "delivery_info")
public class DeliveryInfo extends BaseEntity {

  public String country;

  public String city;

  public String postalCode;

  public String address;

  @ManyToOne
  public User customer;

}
