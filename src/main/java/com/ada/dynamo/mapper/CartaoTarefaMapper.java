package com.ada.dynamo.mapper;
import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.model.Tarefa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartaoTarefaMapper extends ToModelMapper<CartaoTarefa, CartaoTarefaRequest> {
    CartaoTarefa toModel(CartaoTarefaRequest cartaoTarefaRequest);
    Tarefa toTarefa(CartaoTarefaRequest cartaoTarefaRequest);
    CartaoTarefa toCartaoTarefa (Tarefa tafera);
}
