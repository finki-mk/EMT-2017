package mk.ukim.finki.emt.model.jpa;


import mk.ukim.finki.emt.model.enums.DeliveryStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

/**
 * @author Riste Stojanov
 */
public class DeliveryPackage extends BaseEntity {

  @OneToOne
  public Checkout checkout;

  @Enumerated(EnumType.STRING)
  public DeliveryStatus status;
}
