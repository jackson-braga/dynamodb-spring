package com.ada.dynamo.mapper;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.model.Coluna;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColunaMapper {
    Coluna toModel(ColunaRequest colunaRequest);
}
