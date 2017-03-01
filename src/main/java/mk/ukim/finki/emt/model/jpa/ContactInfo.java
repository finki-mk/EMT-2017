package mk.ukim.finki.emt.model.jpa;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "contact_info")
public class ContactInfo extends BaseEntity {

  public String firstName;

  public String lastName;

  public String phone;


}
