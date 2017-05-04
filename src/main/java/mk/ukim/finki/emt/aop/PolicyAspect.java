package mk.ukim.finki.emt.aop;

import mk.ukim.finki.emt.security.Policy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Riste Stojanov
 */
@Aspect
@Component
public class PolicyAspect {

  @Autowired
  private List<Policy> policies;


  @Around("execution(* org.springframework.data.jpa.repository.JpaSpecificationExecutor.findAll(..)) && args(specification)")
  private void baseRepository(ProceedingJoinPoint joinPoint, Specification specification) throws Throwable {
    Specifications spec = Specifications.where(specification);

    JpaSpecificationExecutor repo = (JpaSpecificationExecutor) joinPoint.getTarget();

    Class<?>[] args = GenericTypeResolver.resolveTypeArguments(repo.getClass(), JpaSpecificationExecutor.class);

    policies.stream().forEach(p -> {
      if (p.shouldApply(SecurityContextHolder.getContext(), repo, args[0])) {
        spec.and(p.getSpecification());
      }
    });

    joinPoint.proceed(new Object[]{spec});
  }

}
