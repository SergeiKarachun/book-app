package by.sergo.book.app.mapper.order;

import by.sergo.book.app.domain.dto.order.OrderResponseDto;
import by.sergo.book.app.domain.entity.Order;
import by.sergo.book.app.mapper.ResponseMapper;
import by.sergo.book.app.mapper.book.BookResponseMapper;
import by.sergo.book.app.mapper.user.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderResponseMapper implements ResponseMapper<Order, OrderResponseDto> {

    private final UserResponseMapper userResponseMapper;
    private final BookResponseMapper bookResponseMapper;
    @Override
    public OrderResponseDto mapToDto(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .date(order.getDate())
                .book(bookResponseMapper.mapToDto(order.getBook()))
                .user(userResponseMapper.mapToDto(order.getUser()))
                .startRentalDate(order.getStartRentalDate())
                .endRentalDate(order.getEndRentalDate())
                .build();
    }
}
