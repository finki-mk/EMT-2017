package mk.ukim.finki.emt.model.jpa;


import mk.ukim.finki.emt.model.enums.InvoiceStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

  @OneToOne
  public Checkout checkout;

  public Double grossPrice;

  public Double taxAmount;

  public LocalDateTime expiryDate;

  @Enumerated(EnumType.STRING)
  public InvoiceStatus status;
}
