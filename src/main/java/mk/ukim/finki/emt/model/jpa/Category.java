package mk.ukim.finki.emt.model.jpa;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

  public String name;

  @ManyToOne
  public Category parent; // column parent_id

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Category{");
    sb.append("id='").append(id).append('\'');
    sb.append("name='").append(name).append('\'');
    sb.append(", parent=").append(parent);
    sb.append('}');
    return sb.toString();
  }
}
