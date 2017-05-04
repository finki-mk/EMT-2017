package mk.ukim.finki.emt.security;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.context.SecurityContext;

/**
 * @author Riste Stojanov
 */
public interface Policy<T> {

  boolean shouldApply(SecurityContext context, JpaSpecificationExecutor repo, Class repoGenericType);

  Specification getSpecification();


}
