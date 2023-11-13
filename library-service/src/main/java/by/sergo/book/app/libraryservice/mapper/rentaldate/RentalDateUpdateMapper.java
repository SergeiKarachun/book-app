package by.sergo.book.app.libraryservice.mapper.rentaldate;

import by.sergo.book.app.libraryservice.domain.dto.rentaldate.RentalDateUpdateRequestDto;
import by.sergo.book.app.libraryservice.domain.entity.RentalDate;
import by.sergo.book.app.libraryservice.mapper.UpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class RentalDateUpdateMapper implements UpdateMapper<RentalDateUpdateRequestDto, RentalDate> {


    @Override
    public RentalDate mapToEntity(RentalDateUpdateRequestDto dto, RentalDate entity) {
        merge(dto, entity);
        return entity;
    }

    @Override
    public void merge(RentalDateUpdateRequestDto dto, RentalDate entity) {
        entity.setStartRentalDate(dto.startRentalDate());
        entity.setEndRentalDate(dto.endRentalDate());
    }
}
