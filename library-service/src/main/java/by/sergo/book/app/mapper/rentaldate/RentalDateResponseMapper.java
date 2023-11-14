package by.sergo.book.app.mapper.rentaldate;

import by.sergo.book.app.domain.dto.rentaldate.RentalDateResponseDto;
import by.sergo.book.app.domain.entity.RentalDate;
import by.sergo.book.app.mapper.ResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class RentalDateResponseMapper implements ResponseMapper<RentalDate, RentalDateResponseDto> {
    @Override
    public RentalDateResponseDto mapToDto(RentalDate entity) {
        return new RentalDateResponseDto(entity.getOrderId(),
                entity.getStartRentalDate(),
                entity.getEndRentalDate(),
                entity.getIdBook().getBookId());
    }
}
