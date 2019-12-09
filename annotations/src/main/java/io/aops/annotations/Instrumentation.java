package io.aops.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Instrumentation {

  String INSTRUMENTATION_ANNOTATION = "@annotation(io.aops.annotations.Instrumentation)";

  interface TimerRegistry<T> {
    T timer(Class<?> clazz, String... names);
  }
}