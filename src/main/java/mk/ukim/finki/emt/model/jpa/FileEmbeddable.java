package mk.ukim.finki.emt.model.jpa;

import javax.persistence.Embeddable;
import java.sql.Blob;

/**
 * @author Riste Stojanov
 */
@Embeddable
public class FileEmbeddable {

  public Blob data;

  public String fileName;

  public String contentType;

  public int size;
}
