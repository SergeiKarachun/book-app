package by.sergo.book.app.repository;

import by.sergo.book.app.domain.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<GenreRepository> findByName(String name);

    Boolean existsByNameIgnoreCase(String name);
}
