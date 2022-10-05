package com.ada.dynamo.mapper;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.model.Quadro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuadroMapper {
    Quadro toModel(QuadroRequest quadroRequest);
}
