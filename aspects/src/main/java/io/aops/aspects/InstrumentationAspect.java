package io.aops.aspects;

import com.codahale.metrics.Timer;
import com.codahale.metrics.Timer.Context;
import io.aops.annotations.Instrumentation.TimerRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class InstrumentationAspect {

  private final TimerRegistry<Timer> registry;

  public InstrumentationAspect(TimerRegistry<Timer> registry) {
    this.registry = registry;
  }

  @Around("@annotation(io.aops.annotations.Instrumentation)")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    try (final Context ctx = registry
        .timer(point.getTarget().getClass(), point.getSignature().getName()).time()) {
      return point.proceed();
    }
  }
}