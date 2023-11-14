package by.sergo.book.app.domain.dto.rentaldate;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RentalDateCreateRequestDto(LocalDate startRentalDate,
                                         LocalDate endRentalDate,
                                         Long idBookId,
                                         Long orderId) {
}
