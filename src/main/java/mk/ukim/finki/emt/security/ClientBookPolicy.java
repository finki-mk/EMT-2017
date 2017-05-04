package mk.ukim.finki.emt.security;

import mk.ukim.finki.emt.model.enums.UserType;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.context.SecurityContext;

/**
 * @author Riste Stojanov
 */
public class ClientBookPolicy implements Policy {


  @Override
  public boolean shouldApply(SecurityContext context, JpaSpecificationExecutor repo, Class repoGenericType) {

    boolean applicableForRepository = repoGenericType.isAssignableFrom(Book.class);


    if (context == null
      || context.getAuthentication() == null
      || context.getAuthentication().isAuthenticated() == false)
      return false;

    User user = (User) context.getAuthentication().getPrincipal();

    boolean applicableForUser = user.type.equals(UserType.ROLE_CUSTOMER);

    return applicableForUser && applicableForRepository;
  }

  @Override
  public Specification getSpecification() {

    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(
      root.get("quantityInStock"),
      0
    );

  }
}
