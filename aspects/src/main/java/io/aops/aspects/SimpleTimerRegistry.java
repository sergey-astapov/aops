package io.aops.aspects;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import io.aops.annotations.Instrumentation.TimerRegistry;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleTimerRegistry implements TimerRegistry<Timer> {

  private final MetricRegistry registry = new MetricRegistry();
  private final Map<String, Timer> timers = new ConcurrentHashMap<>();

  @Override
  public Timer timer(Class<?> clazz, String... names) {
    return timers.computeIfAbsent(MetricRegistry.name(clazz, names), registry::timer);
  }
}