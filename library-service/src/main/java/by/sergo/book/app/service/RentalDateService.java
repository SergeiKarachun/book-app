package by.sergo.book.app.service;

import by.sergo.book.app.domain.dto.rentaldate.RentalDateCreateRequestDto;
import by.sergo.book.app.domain.dto.rentaldate.RentalDateResponseDto;
import by.sergo.book.app.domain.dto.rentaldate.RentalDateUpdateRequestDto;
import by.sergo.book.app.domain.dto.orderevent.OrderEvent;
import by.sergo.book.app.domain.entity.RentalDate;
import by.sergo.book.app.mapper.rentaldate.RentalDateCreateMapper;
import by.sergo.book.app.mapper.rentaldate.RentalDateResponseMapper;
import by.sergo.book.app.mapper.rentaldate.RentalDateUpdateMapper;
import by.sergo.book.app.repository.RentalDateRepository;
import by.sergo.book.app.service.exception.ExceptionMessageUtil;
import by.sergo.book.app.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentalDateService {

    private final RentalDateRepository rentalDateRepository;
    private final RentalDateCreateMapper rentalDateCreateMapper;
    private final RentalDateUpdateMapper rentalDateUpdateMapper;
    private final RentalDateResponseMapper rentalDateResponseMapper;

    @Transactional
    public Optional<RentalDateResponseDto> create(RentalDateCreateRequestDto dto) {
        return Optional.of(rentalDateCreateMapper.mapToEntity(dto))
                .map(rentalDateRepository::saveAndFlush)
                .map(rentalDateResponseMapper::mapToDto);
    }

    @Transactional
    @KafkaListener(topics = "order-event-topic", groupId = "order-event-group")
    public void processOrderEvent(OrderEvent orderEvent) {

        if (orderEvent.getEventType().equals("CreateOrder")) {
            var rentalDateDto = RentalDateCreateRequestDto.builder()
                    .idBookId(orderEvent.getBookId())
                    .startRentalDate(orderEvent.getStartRentalDate())
                    .endRentalDate(orderEvent.getEndRentalDate())
                    .orderId(orderEvent.getOrderId())
                    .build();
            var rentalDateEntity = rentalDateCreateMapper.mapToEntity(rentalDateDto);
            rentalDateRepository.saveAndFlush(rentalDateEntity);
        }

        if (orderEvent.getEventType().equals("UpdateOrder")) {
            var rentalDateUpdateDto = RentalDateUpdateRequestDto.builder()
                    .orderId(orderEvent.getOrderId())
                    .idBookId(orderEvent.getBookId())
                    .startRentalDate(orderEvent.getStartRentalDate())
                    .endRentalDate(orderEvent.getEndRentalDate())
                    .build();

            var rentalDate = rentalDateRepository.getByOrderId(orderEvent.getOrderId());

            var rentalDateEntity = rentalDateUpdateMapper.mapToEntity(rentalDateUpdateDto, rentalDate.get());
            rentalDateRepository.saveAndFlush(rentalDateEntity);
        }

        if (orderEvent.getEventType().equals("DeleteOrder")) {
            rentalDateRepository.deleteByOrderId(orderEvent.getOrderId());
        }
    }

    @Transactional
    public Optional<RentalDateResponseDto> update(Long id, RentalDateUpdateRequestDto dto) {
        var rentalDate = getByIdOrElseThrow(id);

        return Optional.of(rentalDateUpdateMapper.mapToEntity(dto, rentalDate))
                .map(rentalDateRepository::saveAndFlush)
                .map(rentalDateResponseMapper::mapToDto);
    }

    public Optional<RentalDateResponseDto> getById(Long id) {
        return Optional.of(getByIdOrElseThrow(id))
                .map(rentalDateResponseMapper::mapToDto);
    }

    public List<RentalDateResponseDto> getAll() {
        return rentalDateRepository.findAll()
                .stream()
                .map(rentalDateResponseMapper::mapToDto)
                .toList();
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (rentalDateRepository.existsById(id)){
            rentalDateRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private RentalDate getByIdOrElseThrow(Long id) {
        return rentalDateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageUtil.getNotFoundMessage("RentalDate", "id", id)));
    }
}
