package by.sergo.book.app.bookclient.repository;

import by.sergo.book.app.bookclient.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(Long isbn);
    Optional<Book> findByName(String name);

}
