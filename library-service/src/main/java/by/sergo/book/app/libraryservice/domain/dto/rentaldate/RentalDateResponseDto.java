package by.sergo.book.app.libraryservice.domain.dto.rentaldate;

import java.time.LocalDate;

public record RentalDateResponseDto(Long id,
                                    LocalDate startRentalDate,
                                    LocalDate endRentalDate,
                                    Long idBookId) {
}
