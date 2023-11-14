package by.sergo.book.app.domain.dto.rentaldate;

import java.time.LocalDate;

public record RentalDateResponseDto(Long orderId,
                                    LocalDate startRentalDate,
                                    LocalDate endRentalDate,
                                    Long idBookId) {
}
