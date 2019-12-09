package io.aops.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

  String IDEMPOTENT_ANNOTATION = "@annotation(io.aops.annotations.Idempotent)";

  @FunctionalInterface
  interface IsDuplicateFunction {

    boolean apply(Object... other);
  }
}