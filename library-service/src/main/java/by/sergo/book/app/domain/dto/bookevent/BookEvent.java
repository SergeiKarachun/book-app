package by.sergo.book.app.domain.dto.bookevent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEvent {
    String eventType;
    Long bookId;
}
