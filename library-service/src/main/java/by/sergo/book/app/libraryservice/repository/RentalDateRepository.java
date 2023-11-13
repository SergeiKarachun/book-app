package by.sergo.book.app.libraryservice.repository;

import by.sergo.book.app.libraryservice.domain.entity.RentalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalDateRepository extends JpaRepository<RentalDate, Long> {
}
