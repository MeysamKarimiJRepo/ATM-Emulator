package ir.mygroup.atmamulator.atm.service;


public interface Mapper<T, E> {

    E mapEntityToDTO(T t);

    T mapDTOToEntity(E e);
}
