package mk.ukim.finki.emt.persistence;

import mk.ukim.finki.emt.model.jpa.Author;
import mk.ukim.finki.emt.model.jpa.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Riste Stojanov
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
