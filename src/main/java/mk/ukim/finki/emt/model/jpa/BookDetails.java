package mk.ukim.finki.emt.model.jpa;

import javax.persistence.*;

/**
 * @author Riste Stojanov
 */
@Entity
@Table(name = "book_details")
public class BookDetails extends BaseEntity {

  @Column(length = 5000)
  public String description;

  @OneToOne
  public Book book;

  @Embedded
  public FileEmbeddable downloadFile;


}
