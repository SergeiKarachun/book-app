package by.sergo.book.app.repository;

import by.sergo.book.app.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(Long isbn);

    Optional<Book> findByName(String name);

    /*@Query(value = "SELECT b " +
                   "FROM book b " +
                   "WHERE b.id NOT IN " +
                   "(SELECT o.book_id " +
                   "FROM orders o " +
                   "WHERE o.start_rental_date <= current_date " +
                   "AND o.end_rental_date >= current_date)",
    nativeQuery = true)
    List<Book> findAllFree();*/

    @Query(value = "select b " +
                   "from Book b " +
                   "where b.id not in " +
                   "(select o.book.id " +
                   "from Order o " +
                   "where o.startRentalDate <= current date " +
                   "and o.endRentalDate >= current date )")
    List<Book> findAllFree();

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

