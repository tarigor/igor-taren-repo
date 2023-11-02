package com.senla.hotel.util;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EntityDtoMapper {
    private final ModelMapper modelMapper;

    public EntityDtoMapper() {
        this.modelMapper = new ModelMapper();
    }

    public <E, D> D convertFromEntityToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <E, D> E convertFromDtoToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
