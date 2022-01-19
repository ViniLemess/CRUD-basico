package com.vinicius.demo.infra.excecao;

import java.util.Set;

public class DominioInvalido extends ExcecaoGenericaDeNegocio {

    public DominioInvalido(Set<String> mensagens) {
        super(mensagens.toString());
    }
    public DominioInvalido(String mensagem) {
        super(mensagem);
    }
}
