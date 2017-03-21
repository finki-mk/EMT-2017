package mk.ukim.finki.emt.model.jpa;


import org.hibernate.search.annotations.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

  @Field(index = Index.YES, store = Store.NO, analyze = Analyze.YES)
  @Analyzer(definition = "emtAnalyser")
  @Boost(1f)
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
