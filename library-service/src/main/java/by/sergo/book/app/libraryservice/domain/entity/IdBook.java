package by.sergo.book.app.libraryservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "idbook")
public class IdBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookid",nullable = false, unique = true)
    private Long bookId;

    @Builder.Default
    @OneToMany(mappedBy = "idBook", cascade = CascadeType.ALL)
    private List<RentalDate> rentalDates = new ArrayList<>();
}
