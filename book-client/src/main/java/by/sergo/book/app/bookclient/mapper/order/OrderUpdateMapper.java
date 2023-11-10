package by.sergo.book.app.bookclient.mapper.order;

import by.sergo.book.app.bookclient.domain.dto.order.OrderUpdateRequestDto;
import by.sergo.book.app.bookclient.domain.entity.Book;
import by.sergo.book.app.bookclient.domain.entity.Order;
import by.sergo.book.app.bookclient.mapper.UpdateMapper;
import by.sergo.book.app.bookclient.repository.BookRepository;
import by.sergo.book.app.bookclient.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderUpdateMapper implements UpdateMapper<OrderUpdateRequestDto, Order> {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    @Override
    public Order mapToEntity(OrderUpdateRequestDto dto, Order entity) {
        merge(dto, entity);
        return entity;
    }

    @Override
    public void merge(OrderUpdateRequestDto dto, Order entity) {
        var book = getBook(dto.getBookId());
        entity.setBook(book);
        entity.setStartRentalDate(dto.getStartRentalDate());
        entity.setEndRentalDate(dto.getEndRentalDate());
    }


    private Book getBook(Long bookId) {
        return Optional.ofNullable(bookId)
                .flatMap(bookRepository::findById)
                .orElse(null);
    }
}
