package by.sergo.book.app.libraryservice.mapper.rentaldate;

import by.sergo.book.app.libraryservice.domain.dto.rentaldate.RentalDateCreateRequestDto;
import by.sergo.book.app.libraryservice.domain.entity.IdBook;
import by.sergo.book.app.libraryservice.domain.entity.RentalDate;
import by.sergo.book.app.libraryservice.mapper.CreateMapper;
import by.sergo.book.app.libraryservice.repository.IdBookRepository;
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
