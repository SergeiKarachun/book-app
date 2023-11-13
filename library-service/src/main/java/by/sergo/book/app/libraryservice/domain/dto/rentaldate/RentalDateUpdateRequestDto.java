package by.sergo.book.app.libraryservice.domain.dto.rentaldate;

import java.time.LocalDate;

public record RentalDateUpdateRequestDto(LocalDate startRentalDate,
                                         LocalDate endRentalDate) {
}
