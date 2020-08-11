package de.rieckpil.courses.book.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewRepositoryNoH2Test {

  @Container
  // show error without specifying database version
  static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
    .withDatabaseName("test")
    .withUsername("duke")
    .withPassword("s3crEt");

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.username", container::getUsername);
  }

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private DataSource dataSource;

  @Test
  void notNull() throws SQLException {

    // show failure as Flyway is not compatible
    assertNotNull(dataSource);
    assertNotNull(entityManager);
    assertNotNull(reviewRepository);

    Review review = new Review();
    review.setTitle("Review 101");
    review.setContent("Good review");
    review.setCreatedAt(LocalDateTime.now());
    review.setRating(5);
    review.setBook(null);
    review.setUser(null);

    // Review result = testEntityManager.persistFlushFind(review);
    Review result = reviewRepository.save(review);

    System.out.println(result);
    System.out.println(dataSource.getConnection().getMetaData().getDatabaseProductName());
  }

  @Test
  @Sql(scripts = "/scripts/INIT_REVIEW_EACH_BOOK.sql")
  public void shouldGetTwoReviewStatisticWhenDatabaseContainsTwoBooksWithReviews() {
    assertEquals(3, reviewRepository.count());
    assertEquals(2, reviewRepository.getReviewStatistics().size());
    reviewRepository.getReviewStatistics().forEach(reviewStatistic -> System.out.println(reviewStatistic));
  }

}
