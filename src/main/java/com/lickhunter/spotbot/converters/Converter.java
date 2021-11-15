package com.lickhunter.spotbot.converters;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Converter<DTO, ENTITY> {
    private final Function<DTO, ENTITY> fromDto;
    private final Function<ENTITY, DTO> fromEntity;

    public final ENTITY convertFromDto(final DTO dto) {
        return fromDto.apply(dto);
    }

    public final DTO convertFromEntity(final ENTITY entity) {
        return fromEntity.apply(entity);
    }

    public final List<ENTITY> createFromDtos(final Collection<DTO> dtos) {
        return dtos.stream().map(this::convertFromDto).collect(Collectors.toList());
    }

    public final List<DTO> createFromEntities(final Collection<ENTITY> entities) {
        return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }
}
