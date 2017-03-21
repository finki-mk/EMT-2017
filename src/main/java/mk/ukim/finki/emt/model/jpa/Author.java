package mk.ukim.finki.emt.model.jpa;

import org.hibernate.search.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "authors")
//@Indexed
public class Author extends BaseEntity {

  @Field(index = Index.YES, store = Store.NO, analyze = Analyze.YES)
  @Analyzer(definition = "emtAnalyser")
  @Boost(1f)
  public String nameAndLastName;
}
