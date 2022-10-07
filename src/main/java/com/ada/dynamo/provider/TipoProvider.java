package com.ada.dynamo.provider;

public enum TipoProvider {
    CARTAO_TAREFA {
        @Override
        public String stringValue() {
            return "CARTAO_TAREFA";
        }
    },
    COLUNA {
        @Override
        public String stringValue() {
            return "COLUNA";
        }
    };

    public abstract String stringValue();
}
