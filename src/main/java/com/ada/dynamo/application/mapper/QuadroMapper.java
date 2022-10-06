package com.ada.dynamo.application.mapper;

import com.ada.dynamo.application.request.QuadroRequest;
import com.ada.dynamo.application.response.QuadroResponse;
import com.ada.dynamo.domain.model.Quadro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuadroMapper {

    QuadroMapper INSTANCE = Mappers.getMapper(QuadroMapper.class);

    Quadro quadroRequestToQuadro(QuadroRequest quadroRequest);

    QuadroResponse quadroToQuadroResponse(Quadro quadro);

    List<QuadroResponse> quadroListToQuadroResponse(List<Quadro> quadroList);

}
