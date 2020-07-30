package de.rieckpil.courses.book.review;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.annotation.ElementType.PARAMETER;

public class RandomReviewParameterResolver implements ParameterResolver {

  private static final List<String> badReviews = List.of("This book was shit I don't like it",
    "I was reading the book and I think the book is okay. I have read better books and I think I know what's good",
    "Good book with good agende and good example. I can recommend for everyong");

  @Retention(RetentionPolicy.RUNTIME)
  @Target(PARAMETER)
  public @interface RandomReview {
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    return parameterContext.isAnnotated(RandomReview.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    return badReviews.get(ThreadLocalRandom.current().nextInt(0, badReviews.size()));
  }

}
