package com.ada.dynamo.mapper;
import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.model.CartaoTarefa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartaoTarefaMapper extends ToModelMapper<CartaoTarefa, CartaoTarefaRequest> {
CartaoTarefa toModel(CartaoTarefaRequest cartaoTarefaRequest);
}
