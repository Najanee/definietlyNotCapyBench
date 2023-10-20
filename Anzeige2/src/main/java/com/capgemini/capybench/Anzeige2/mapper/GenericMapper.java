package com.capgemini.capybench.Anzeige2.mapper;

public interface GenericMapper <E, D>{
    D toDto(E entity);
    E toEntity(D dto);
}
