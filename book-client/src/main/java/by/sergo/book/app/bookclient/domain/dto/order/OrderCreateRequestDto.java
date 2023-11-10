package by.sergo.book.app.bookclient.domain.dto.order;

import lombok.Value;

import java.time.LocalDate;

@Value
public class OrderCreateRequestDto {
    Long userId;
    Long bookId;
    LocalDate startRentalDate;
    LocalDate endRentalDate;
}
