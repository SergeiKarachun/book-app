package by.sergo.book.app.bookclient.domain.dto.order;

import by.sergo.book.app.bookclient.domain.dto.book.BookResponseDto;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class OrderUserReportDto {
    Long id;
    BookResponseDto book;
    LocalDate date;
    LocalDate startRentalDate;
    LocalDate endRentalDate;
}
