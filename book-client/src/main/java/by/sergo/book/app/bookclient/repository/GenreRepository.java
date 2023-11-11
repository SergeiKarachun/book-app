package by.sergo.book.app.bookclient.repository;

import by.sergo.book.app.bookclient.domain.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<GenreRepository> findByName(String name);

    Boolean existsByNameIgnoreCase(String name);
}
