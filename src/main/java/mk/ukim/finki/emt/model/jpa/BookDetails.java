package mk.ukim.finki.emt.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Blob;

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

  public Blob data;

  public String fileName;

  public String contentType;

  public Long size;


}
