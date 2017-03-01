package mk.ukim.finki.emt.model.jpa;


import mk.ukim.finki.emt.model.enums.UserType;

import javax.persistence.*;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  public String username;

  public String password;

  public String email;

  @Enumerated(EnumType.STRING)
  public UserType type;

  @OneToOne
  public ContactInfo contactInfo;

}
