package com.ada.dynamo.provider;

public enum TipoProvider {
    CARTAO_TAREFA {
        @Override
        public String stringValue() {
            return "CARTAO_TAREFA";
        }
    };

    public abstract String stringValue();
}
