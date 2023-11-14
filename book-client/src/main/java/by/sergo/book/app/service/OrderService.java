package by.sergo.book.app.service;

import by.sergo.book.app.domain.dto.order.OrderCreateRequestDto;
import by.sergo.book.app.domain.dto.order.OrderResponseDto;
import by.sergo.book.app.domain.dto.order.OrderUpdateRequestDto;
import by.sergo.book.app.domain.dto.orderevent.OrderEvent;
import by.sergo.book.app.domain.entity.Order;
import by.sergo.book.app.mapper.order.OrderCreateMapper;
import by.sergo.book.app.mapper.order.OrderResponseMapper;
import by.sergo.book.app.mapper.order.OrderUpdateMapper;
import by.sergo.book.app.repository.BookRepository;
import by.sergo.book.app.repository.OrderRepository;
import by.sergo.book.app.repository.UserRepository;
import by.sergo.book.app.service.exception.BadRequestException;
import by.sergo.book.app.service.exception.ExceptionMessageUtil;
import by.sergo.book.app.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderCreateMapper orderCreateMapper;
    private final OrderUpdateMapper orderUpdateMapper;
    private final OrderResponseMapper orderResponseMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Optional<OrderResponseDto> create(OrderCreateRequestDto dto) {
        checkIsUserExist(dto.getUserId());
        checkIsBookExist(dto.getBookId());

        if (!isBookAvailable(dto.getBookId(), dto.getStartRentalDate(), dto.getEndRentalDate())) {
            return Optional.empty();
        }

        var orderResponseDto = Optional.of(orderCreateMapper.mapToEntity(dto))
                .map(orderRepository::saveAndFlush)
                .map(orderResponseMapper::mapToDto);

        orderResponseDto.ifPresent(orderDto -> {
            var orderEvent = OrderEvent.builder()
                    .eventType("CreateOrder")
                    .startRentalDate(orderDto.getStartRentalDate())
                    .endRentalDate(orderDto.getEndRentalDate())
                    .bookId(orderDto.getBook().getId())
                    .orderId(orderDto.getId())
                    .build();
            kafkaTemplate.send("order-event-topic", orderEvent);
        });

        return orderResponseDto;
    }

    @Transactional
    public Optional<OrderResponseDto> update(Long id, OrderUpdateRequestDto dto) {
        var order = getOrderOrElseThrow(id);

        if (!isBookAvailableByOrderId(id, dto.getBookId(), dto.getStartRentalDate(), dto.getEndRentalDate())) {
            return Optional.empty();
        }

        var orderResponseDto = Optional.of(orderUpdateMapper.mapToEntity(dto, order))
                .map(orderRepository::saveAndFlush)
                .map(orderResponseMapper::mapToDto);

        orderResponseDto.ifPresent(orderDto -> {

            var orderEvent = OrderEvent.builder()
                    .eventType("UpdateOrder")
                    .startRentalDate(orderDto.getStartRentalDate())
                    .endRentalDate(orderDto.getEndRentalDate())
                    .bookId(orderDto.getBook().getId())
                    .orderId(id)
                    .build();
            kafkaTemplate.send("order-event-topic", orderEvent);
        });

        return orderResponseDto;
    }

    public Optional<OrderResponseDto> getById(Long id) {
        return Optional.of(getOrderOrElseThrow(id))
                .map(orderResponseMapper::mapToDto);
    }

    public List<OrderResponseDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderResponseMapper::mapToDto)
                .toList();
    }

    @Transactional
    public Boolean deleteById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
                var orderEvent = OrderEvent.builder()
                        .eventType("DeleteOrder")
                        .orderId(id)
                        .build();
                kafkaTemplate.send("order-event-topic", orderEvent);
            return true;
        }
        return false;
    }

    private Order getOrderOrElseThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageUtil.getNotFoundMessage("Order", "id", id)));
    }

    private void checkIsBookExist(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BadRequestException(ExceptionMessageUtil.getNotFoundMessage("Book", "bookId", bookId));
        }
    }

    private void checkIsUserExist(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new BadRequestException(ExceptionMessageUtil.getNotFoundMessage("User", "userid", userId));
        }
    }

    private boolean isBookAvailable(Long bookId, LocalDate startDate, LocalDate endDate) {
        return bookRepository.isBookAvailable(bookId, startDate, endDate);
    }

    private boolean isBookAvailableByOrderId(Long orderId, Long bookId, LocalDate startDate, LocalDate endDate) {
        return bookRepository.isBookAvailableByOrderId(orderId, bookId, startDate, endDate);
    }


}
