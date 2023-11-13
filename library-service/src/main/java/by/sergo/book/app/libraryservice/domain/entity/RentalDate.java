package by.sergo.book.app.libraryservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "rental_date")
public class RentalDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startRentalDate;

    private LocalDate endRentalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idbook_id", nullable = false)
    private IdBook idBook;

    public void setIdBook(IdBook idBook) {
        this.idBook = idBook;
        idBook.getRentalDates().add(this);
    }
}
