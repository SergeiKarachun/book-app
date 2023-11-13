package by.sergo.book.app.libraryservice.service;

import by.sergo.book.app.libraryservice.domain.dto.rentaldate.RentalDateCreateRequestDto;
import by.sergo.book.app.libraryservice.domain.dto.rentaldate.RentalDateResponseDto;
import by.sergo.book.app.libraryservice.domain.dto.rentaldate.RentalDateUpdateRequestDto;
import by.sergo.book.app.libraryservice.domain.entity.RentalDate;
import by.sergo.book.app.libraryservice.mapper.rentaldate.RentalDateCreateMapper;
import by.sergo.book.app.libraryservice.mapper.rentaldate.RentalDateResponseMapper;
import by.sergo.book.app.libraryservice.mapper.rentaldate.RentalDateUpdateMapper;
import by.sergo.book.app.libraryservice.repository.RentalDateRepository;
import by.sergo.book.app.libraryservice.service.exception.ExceptionMessageUtil;
import by.sergo.book.app.libraryservice.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
