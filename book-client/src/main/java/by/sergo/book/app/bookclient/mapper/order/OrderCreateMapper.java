package by.sergo.book.app.bookclient.mapper.order;

import by.sergo.book.app.bookclient.domain.dto.order.OrderCreateRequestDto;
import by.sergo.book.app.bookclient.domain.entity.Book;
import by.sergo.book.app.bookclient.domain.entity.Order;
import by.sergo.book.app.bookclient.domain.entity.User;
import by.sergo.book.app.bookclient.mapper.CreateMapper;
import by.sergo.book.app.bookclient.repository.BookRepository;
import by.sergo.book.app.bookclient.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class OrderCreateMapper implements CreateMapper<OrderCreateRequestDto, Order> {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public Order mapToEntity(OrderCreateRequestDto requestDto) {

        var order = Order.builder()
                .date(LocalDate.now())
                .startRentalDate(requestDto.getStartRentalDate())
                .endRentalDate(requestDto.getEndRentalDate())
                .build();

        var user = getUser(requestDto.getUserId());
        var book = getBook(requestDto.getBookId());

        order.setBook(book);
        order.setUser(user);

        return order;
    }


    private User getUser(Long userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }

    private Book getBook(Long bookId) {
        return Optional.ofNullable(bookId)
                .flatMap(bookRepository::findById)
                .orElse(null);
    }
}
