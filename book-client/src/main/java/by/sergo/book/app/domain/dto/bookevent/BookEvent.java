package by.sergo.book.app.domain.dto.bookevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEvent {
    String eventType;
    Long bookId;
}
