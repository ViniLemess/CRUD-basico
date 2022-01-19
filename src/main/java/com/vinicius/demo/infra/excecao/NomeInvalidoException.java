package com.vinicius.demo.infra.excecao;

public class NomeInvalidoException extends ExcecaoGenericaDeNegocio{

    public NomeInvalidoException(String message) {
        super(message);
    }
}
