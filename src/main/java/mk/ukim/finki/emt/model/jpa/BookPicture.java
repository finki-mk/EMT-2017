package mk.ukim.finki.emt.model.jpa;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "book_pictures")
public class BookPicture extends BaseEntity {

  @Embedded
  public FileEmbeddable picture;

  @ManyToOne
  public Book book;

}
