package by.sergo.book.app.libraryservice.mapper.rentaldate;

import by.sergo.book.app.libraryservice.domain.dto.rentaldate.RentalDateResponseDto;
import by.sergo.book.app.libraryservice.domain.entity.RentalDate;
import by.sergo.book.app.libraryservice.mapper.ResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class RentalDateResponseMapper implements ResponseMapper<RentalDate, RentalDateResponseDto> {
    @Override
    public RentalDateResponseDto mapToDto(RentalDate entity) {
        return new RentalDateResponseDto(entity.getId(),
                entity.getStartRentalDate(),
                entity.getEndRentalDate(),
                entity.getIdBook().getBookId());
    }
}
