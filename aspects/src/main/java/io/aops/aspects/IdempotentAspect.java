package io.aops.aspects;

import static org.slf4j.LoggerFactory.getLogger;

import io.aops.annotations.Idempotent.IsDuplicateFunction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;

@Aspect
public class IdempotentAspect {

  private final static Logger LOG = getLogger(IdempotentAspect.class);

  private final IsDuplicateFunction isDuplicateFunction;

  public IdempotentAspect(IsDuplicateFunction isDuplicateFunction) {
    this.isDuplicateFunction = isDuplicateFunction;
  }

  @Around("@annotation(io.aops.annotations.Idempotent)")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    if (isDuplicateFunction.apply(point.getArgs())) {
      LOG.info("No need to proceed");
      return null;
    }

    LOG.info("No entries found, proceed");

    Object proceed = point.proceed();
    
    return proceed;
  }
}