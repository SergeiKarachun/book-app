package by.sergo.book.app.repository;

import by.sergo.book.app.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByDateBetween(LocalDate start, LocalDate end);

    @Query(value = "SELECT o " +
                   "FROM orders o " +
                   "JOIN users u ON o.user_id = u.id " +
                   "WHERE u.id = :id",
            nativeQuery = true)
    List<Order> findAllByUserId(@Param("id") Long id);


    @Query(value = "SELECT o " +
                   "FROM orders o " +
                   "JOIN book b ON o.book_id = b.id " +
                   "WHERE b.id = :id",
    nativeQuery = true)
    List<Order> findAllByBookId(@Param("id") Long id);
}
