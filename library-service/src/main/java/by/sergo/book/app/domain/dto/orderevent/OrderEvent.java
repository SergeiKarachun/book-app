package by.sergo.book.app.domain.dto.orderevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEvent {
    String eventType;
    Long bookId;
    LocalDate startRentalDate;
    LocalDate endRentalDate;
    Long orderId;
}
