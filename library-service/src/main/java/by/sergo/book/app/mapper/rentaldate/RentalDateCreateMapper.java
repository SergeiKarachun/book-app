package by.sergo.book.app.mapper.rentaldate;

import by.sergo.book.app.domain.dto.rentaldate.RentalDateCreateRequestDto;
import by.sergo.book.app.domain.entity.IdBook;
import by.sergo.book.app.domain.entity.RentalDate;
import by.sergo.book.app.mapper.CreateMapper;
import by.sergo.book.app.repository.IdBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RentalDateCreateMapper implements CreateMapper<RentalDateCreateRequestDto, RentalDate> {

    private final IdBookRepository idBookRepository;

    @Override
    public RentalDate mapToEntity(RentalDateCreateRequestDto requestDto) {
        var idBook = getIdBook(requestDto.idBookId());
        var rentalDate = RentalDate.builder()
                .startRentalDate(requestDto.startRentalDate())
                .endRentalDate(requestDto.endRentalDate())
                .orderId(requestDto.orderId())
                .build();
        rentalDate.setIdBook(idBook);
        return rentalDate;
    }

    private IdBook getIdBook(Long idBookId) {
        return Optional.ofNullable(idBookId)
                .flatMap(idBookRepository::findById)
                .orElse(null);
    }
}
