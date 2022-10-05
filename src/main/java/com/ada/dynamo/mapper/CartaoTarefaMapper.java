package com.ada.dynamo.mapper;
import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.model.CartaoTarefa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartaoTarefaMapper {
    CartaoTarefa toModel(CartaoTarefaRequest cartaoTarefaRequest);
}
