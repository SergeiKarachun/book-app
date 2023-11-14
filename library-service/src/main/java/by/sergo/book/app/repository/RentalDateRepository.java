package by.sergo.book.app.repository;

import by.sergo.book.app.domain.entity.RentalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalDateRepository extends JpaRepository<RentalDate, Long> {

    Optional<RentalDate> getByOrderId(Long orderId);

    void deleteByOrderId(Long orderId);
}
