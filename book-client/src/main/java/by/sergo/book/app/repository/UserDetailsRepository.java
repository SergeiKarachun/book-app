package by.sergo.book.app.repository;

import by.sergo.book.app.domain.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByName(String name);
    Optional<UserDetails> findBySurname(String surname);
    Optional<UserDetails> findByPhone(String phone);
}
