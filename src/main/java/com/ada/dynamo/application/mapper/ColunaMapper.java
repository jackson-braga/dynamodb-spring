package com.ada.dynamo.application.mapper;

import com.ada.dynamo.application.request.CreateColunaRequest;
import com.ada.dynamo.application.request.UpdateColunaRequest;
import com.ada.dynamo.application.response.ColunaResponse;
import com.ada.dynamo.domain.model.Coluna;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ColunaMapper {
    
    ColunaMapper INSTANCE = Mappers.getMapper(ColunaMapper.class);
    
    Coluna createColunaRequestToColuna(CreateColunaRequest colunaRequest);

    Coluna updateColunaRequestToColuna(UpdateColunaRequest colunaRequest);

    ColunaResponse colunaToColunaResponse(Coluna coluna);

    List<ColunaResponse> colunaListToColunaResponseList(List<Coluna> colunaList);

}
