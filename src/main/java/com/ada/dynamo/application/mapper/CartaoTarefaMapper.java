package com.ada.dynamo.application.mapper;

import com.ada.dynamo.application.request.CreateCartaoTarefaRequest;
import com.ada.dynamo.application.request.UpdateCartaoTarefaRequest;
import com.ada.dynamo.application.response.CartaoTarefaResponse;
import com.ada.dynamo.domain.model.CartaoTarefa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartaoTarefaMapper {

    CartaoTarefaMapper INSTANCE = Mappers.getMapper(CartaoTarefaMapper.class);

    CartaoTarefa updateCartaoTarefaRequestToCartaoTarefa(UpdateCartaoTarefaRequest updateCartaoTarefaRequest);

    CartaoTarefa createCartaoTarefaRequestToCartaoTarefa(CreateCartaoTarefaRequest createCartaoTarefaRequest);

    CartaoTarefaResponse cartaoTarefaToCartaoTarefaResponse(CartaoTarefa cartaoTarefa);

    List<CartaoTarefaResponse> cartaoTarefaListToCartaoTarefaResponseList(List<CartaoTarefa> cartaoTarefa);

}
