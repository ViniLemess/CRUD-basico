package com.vinicius.demo.infra.excecao;

public abstract class ExcecaoGenericaDeNegocio extends RuntimeException {
    public ExcecaoGenericaDeNegocio(String message) {
        super(message);
    }
}
