package by.sergo.book.app.repository;

import by.sergo.book.app.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByName(String name);
    Optional<Author> findAuthorBySurname(String surname);

    Optional<Author> findAuthorByNameAndSurname(String name, String surname);
    Optional<Author> findAuthorByNameIgnoreCaseAndSurnameIgnoreCase(String name, String surname);
}
