package by.sergo.book.app.bookclient.repository;

import by.sergo.book.app.bookclient.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(Long isbn);

    Optional<Book> findByName(String name);

    @Query(value = "SELECT count(o.id) = 0 " +
                   "FROM orders o " +
                   "JOIN book b on o.book_id = b.id " +
                   "WHERE b.id = :id AND o.start_rental_date <= :endDate AND o.end_rental_date >= :startDate",
            nativeQuery = true)
    boolean isBookAvailable(@Param("id") Long id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT count(o.id) = 0 " +
                   "FROM orders o " +
                   "JOIN book b on o.book_id = b.id " +
                   "WHERE b.id = :id AND o.id != :orderId AND o.start_rental_date <= :endDate AND o.end_rental_date >= :startDate",
            nativeQuery = true)
    boolean isBookAvailableByOrderId(@Param("orderId") Long orderId, @Param("id") Long id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}

