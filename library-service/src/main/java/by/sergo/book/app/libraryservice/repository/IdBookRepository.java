package by.sergo.book.app.libraryservice.repository;

import by.sergo.book.app.libraryservice.domain.entity.IdBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdBookRepository extends JpaRepository<IdBook, Long> {
}
