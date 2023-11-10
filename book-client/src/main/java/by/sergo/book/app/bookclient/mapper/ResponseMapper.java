package by.sergo.book.app.bookclient.mapper;

public interface ResponseMapper<F, T> extends Mapper<F, T> {

    T mapToDto(F entity);
}
