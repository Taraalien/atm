package com.software.atm.common;

import java.util.List;

public interface EntityMapper <D,E>{

    E toEntity(D dto);

    D toDto(E entity);

    List<E>toEntity(List<D>dto);

    List<D>toDto(List<E>toEntity);
}
