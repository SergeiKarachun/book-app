package by.sergo.book.app.domain.dto.rentaldate;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RentalDateUpdateRequestDto(LocalDate startRentalDate,
                                         LocalDate endRentalDate,
                                         Long orderId,
                                         Long idBookId) {
}
