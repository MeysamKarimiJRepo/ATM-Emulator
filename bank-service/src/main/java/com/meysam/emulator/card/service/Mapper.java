package com.meysam.emulator.card.service;


public interface Mapper<T, E> {

    E mapEntityToDTO(T t);

    T mapDTOToEntity(E e);
}
