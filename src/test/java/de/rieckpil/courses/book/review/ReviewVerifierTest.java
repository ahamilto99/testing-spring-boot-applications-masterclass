package de.rieckpil.courses.book.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

<<<<<<< HEAD
import static de.rieckpil.courses.book.review.RandomReviewParameterResolverExtension.RandomReview;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(RandomReviewParameterResolverExtension.class)
=======
import static de.rieckpil.courses.book.review.RandomReviewParameterResolver.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(RandomReviewParameterResolver.class)
>>>>>>> next steps for JUnit
class ReviewVerifierTest {

  private ReviewVerifier reviewVerifier;

  @BeforeEach
  public void setup() {
    reviewVerifier = new ReviewVerifier();
  }

  @Test
  void shouldFailWhenReviewContainsSwearWord() throws InterruptedException {

    Thread.sleep(1000);
    String review = "This book is shit";
    System.out.println("Testing a review");

    boolean result = reviewVerifier.doesMeetQualityStandards(review);
    assertFalse(result, "ReviewVerifier did not detect swear word");
  }

  @Test
<<<<<<< HEAD
  @DisplayName("Should fail when review contains 'lorem ipsum'")
  void testLoremIpsum() {
=======
  @DisplayName("Should fail when review contains 'Lorem Ipsum'")
  void nextTest() throws InterruptedException {

    Thread.sleep(1000);
>>>>>>> next steps for JUnit

    String review = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr," +
      " sed diam nonumy eirmod tempor invidunt ut labore et " +
      "dolore magna aliquyam erat, sed diam voluptua. " +
      "At vero eos et accusam et justo duo dolores et ea rebum";

    boolean result = reviewVerifier.doesMeetQualityStandards(review);
    assertFalse(result, "ReviewVerifier did not detect lorem ipsum");
  }

<<<<<<< HEAD
  @ParameterizedTest
  @CsvFileSource(resources = "/badReview.csv")
  void shouldFailWhenReviewIsOfBadQuality(String review) {
=======

  @ParameterizedTest
  @CsvFileSource(resources = "/badReviews.csv")
  void shouldFailWhenReviewIsOfBadQuality(String review) throws InterruptedException {
    Thread.sleep(1000);

>>>>>>> next steps for JUnit
    boolean result = reviewVerifier.doesMeetQualityStandards(review);
    assertFalse(result, "ReviewVerifier did not detect bad review");
  }

  @RepeatedTest(5)
  void shouldFailWhenRandomReviewQualityIsBad(@RandomReview String review) {
    System.out.println(review);
    boolean result = reviewVerifier.doesMeetQualityStandards(review);
<<<<<<< HEAD
    assertFalse(result, "ReviewVerifier did not detect random bad review");
  }

  @Test
  void shouldPassWhenReviewIsGood() {
=======
    assertFalse(result, "ReviewVerifier did not detect bad review");
  }

  @Test
  void shouldPassWhenReviewIsGood() throws InterruptedException {

    Thread.sleep(1000);

>>>>>>> next steps for JUnit
    String review = "I can totally recommend this book " +
      "who is interested in learning how to write Java code!";

    boolean result = reviewVerifier.doesMeetQualityStandards(review);
<<<<<<< HEAD
    assertTrue(result, "ReviewVerifier did not pass a good review");
  }

=======
    assertTrue(result, "ReviewVerifier did not pass good review");
  }
>>>>>>> next steps for JUnit
}
