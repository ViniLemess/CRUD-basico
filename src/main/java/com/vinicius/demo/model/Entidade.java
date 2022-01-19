package com.vinicius.demo.model;

import com.vinicius.demo.infra.excecao.DominioInvalido;

import javax.validation.*;
import java.util.HashSet;
import java.util.Set;


public class Entidade {

    void isValid() {
        Configuration<?> configuracaoes = Validation.byDefaultProvider().configure();
        ValidatorFactory fabrica = configuracaoes.buildValidatorFactory();
        Validator validador = fabrica.getValidator();
        Set<ConstraintViolation<Entidade>> regrasVioladas = validador.validate(this);

        Set<String> mensagens = new HashSet<>();

        for (ConstraintViolation<Entidade> constraintViolation : regrasVioladas) {
            String campo = constraintViolation.getPropertyPath().toString();
            String msg = constraintViolation.getMessage();
            mensagens.add(campo.concat(" : ").concat(msg));
        }

        if (!mensagens.isEmpty()) {
            throw new DominioInvalido(mensagens);
        }
    }
}
