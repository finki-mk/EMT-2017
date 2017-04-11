package mk.ukim.finki.emt.model.jpa;


import mk.ukim.finki.emt.model.enums.DeliveryStatus;

import javax.persistence.*;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "delivery_packages")
public class DeliveryPackage extends BaseEntity {

  @OneToOne
  public Checkout checkout;

  @Enumerated(EnumType.STRING)
  public DeliveryStatus status;
}
