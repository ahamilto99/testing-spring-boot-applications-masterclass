package de.rieckpil.courses.book.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(properties = {
  "spring.jpa.hibernate.ddl-auto=create-drop",
  "spring.flyway.enabled=false",
  "spring.datasource.driver-class-name=com.p6spy.engine.spy.P6SpyDriver", // P6Spy
  "spring.datasource.url=jdbc:p6spy:h2:mem:testing;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false" // P6Spy
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewRepositoryTest {

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private DataSource dataSource;

  @BeforeEach
  void beforeEach() {
    assertEquals(0, reviewRepository.count());
  }

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
    System.out.println(dataSource.toString());
    System.out.println(dataSource.getConnection().getMetaData().getDatabaseProductName());
  }

  @Test
  void transactionalSupport() {
    Review review = new Review();
    review.setTitle("Review 101");
    review.setContent("Good review");
    review.setCreatedAt(LocalDateTime.now());
    review.setRating(5);
    review.setBook(null);
    review.setUser(null);

    Review result = reviewRepository.save(review);

    testEntityManager.flush();
  }

}
