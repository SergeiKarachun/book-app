package by.sergo.book.app.libraryservice.mapper;

public interface ResponseMapper<F, T> extends Mapper<F, T> {

    T mapToDto(F entity);
}
