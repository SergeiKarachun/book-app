package by.sergo.book.app.mapper.rentaldate;

import by.sergo.book.app.domain.dto.rentaldate.RentalDateUpdateRequestDto;
import by.sergo.book.app.domain.entity.RentalDate;
import by.sergo.book.app.mapper.UpdateMapper;
import by.sergo.book.app.repository.IdBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class RentalDateUpdateMapper implements UpdateMapper<RentalDateUpdateRequestDto, RentalDate> {

    private final IdBookRepository idBookRepository;

    @Override
    public RentalDate mapToEntity(RentalDateUpdateRequestDto dto, RentalDate entity) {
        merge(dto, entity);
        return entity;
    }

    @Override
    public void merge(RentalDateUpdateRequestDto dto, RentalDate entity) {
        var idBook = idBookRepository.findById(dto.idBookId()).get();

        entity.setIdBook(idBook);
        entity.setStartRentalDate(dto.startRentalDate());
        entity.setEndRentalDate(dto.endRentalDate());
        entity.setOrderId(dto.orderId());
    }
}
