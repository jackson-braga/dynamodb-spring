package com.ada.dynamo.domain.exception;

public class ItemNaoExistenteException extends RuntimeException {

    public ItemNaoExistenteException(Class<?> clazz) {
        super(clazz.getSimpleName());
    }

}
