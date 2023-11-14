package by.sergo.book.app.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"book", "user"})
@EqualsAndHashCode(exclude = {"book", "user"})
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate startRentalDate;

    @Column(nullable = false)
    private LocalDate endRentalDate;

    public void setUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void setBook(Book book) {
        this.book = book;
        book.getOrders().add(this);
    }

}
