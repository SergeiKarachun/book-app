package by.sergo.book.app.bookclient.domain.dto.order;

import by.sergo.book.app.bookclient.domain.dto.book.BookResponseDto;
import by.sergo.book.app.bookclient.domain.dto.user.UserResponseDto;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class OrderResponseDto {
    Long id;
    UserResponseDto user;
    BookResponseDto book;
    LocalDate date;
    LocalDate startRentalDate;
    LocalDate endRentalDate;
}
