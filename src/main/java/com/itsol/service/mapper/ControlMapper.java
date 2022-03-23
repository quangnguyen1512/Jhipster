package com.itsol.service.mapper;


import com.itsol.domain.Control;
import com.itsol.service.dto.ControlDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link Control} and its DTO {@link ControlDTO}
 */
@Component
public class ControlMapper implements EntityMapper<ControlDTO, Control> {

    @Override
    public Control toEntity(ControlDTO dto){
        if (dto == null){
            return null;
        }
        Control entity = new Control();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public ControlDTO toDto(Control entity) {
        if (entity == null) {
            return null;
        }
        ControlDTO dto = new ControlDTO();
        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    @Override
    public List<Control> toEntity(List<ControlDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<ControlDTO> toDto(List<Control> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
