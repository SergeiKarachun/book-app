package by.sergo.book.app.bookclient.controller;

import by.sergo.book.app.bookclient.domain.dto.order.OrderCreateRequestDto;
import by.sergo.book.app.bookclient.domain.dto.order.OrderResponseDto;
import by.sergo.book.app.bookclient.domain.dto.order.OrderUpdateRequestDto;
import by.sergo.book.app.bookclient.service.OrderService;
import by.sergo.book.app.bookclient.service.exception.BadRequestException;
import by.sergo.book.app.bookclient.service.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create order")
    public OrderResponseDto create(@RequestBody OrderCreateRequestDto dto) {
        return orderService.create(dto)
                .orElseThrow(() -> new BadRequestException("Can't create new order, please check input parameters"));
    }

    @GetMapping()
    @Operation(summary = "Get all orders")
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public OrderResponseDto getById(@PathVariable("id") Long id) {
        return orderService.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order with id %s doesn't exist", id)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update order")
    public OrderResponseDto update(@PathVariable("id")Long id, @RequestBody OrderUpdateRequestDto dto) {
        return orderService.update(id, dto)
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Can't update order, please check input parameters"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return orderService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
