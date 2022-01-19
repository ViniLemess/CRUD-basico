package com.vinicius.demo.model;

import com.vinicius.demo.infra.excecao.DominioInvalido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CursoModelTest {

    @Test
    void deveInstanciarCursoComSucesso() {

           Long id = 04043410042L;
           String nome = "Curso Ténico de TI";
           String descricao = "Curso de TI com enfâse em programação";
           String escola = "Fundatec";
           Integer vagas = 20;

           var curso = new CursoModel(id, nome, descricao, escola, vagas);

           Assertions.assertEquals(curso.getId(), id);
           Assertions.assertEquals(curso.getNome(), nome);
           Assertions.assertEquals(curso.getDescricao(), descricao);
           Assertions.assertEquals(curso.getEscola(), escola);
           Assertions.assertEquals(curso.getVagas(), vagas);
    }
    @Test
    void deveValidarInstanciaDeCursoModel() {

        Long id = null;
        String nome = "";
        String descricao = "";
        String escola = "";
        Integer vagas = null;


        try {
            new CursoModel(id, nome, descricao, escola, vagas);
        } catch (DominioInvalido dominioInvalido) {
            String mensagemEsperada = "[escola : Instituição de ensino do curso deve ser informada!, vagas : numero de vagas do curso deve ser informado!, nome : Nome do curso deve ser informado!]";
            Assertions.assertEquals(mensagemEsperada, dominioInvalido.getMessage());
        }
    }
    @Test
    void deveValidarTamanhoDoCampoDescricaoDeCursoModel() {

        Long id = 04043410042L;
        String nome = "Curso Ténico de TI";
        String descricao = "4E8Bfd9yc9jIztDSjHFoYpCFLCYu69NAgDEFI08Mej7Upt6aWHv4lXAygKwGK9xBZrqt9WqDvLrrwB00SVuSsbxW04seL5icjNOoZVrPPHwFH9qEIcCScVpQbQVJb2J06eWKEugikB0GN3abkWebsS8SKr5bcD2JSmzQ1rgG5fy6mGsa6U0Yt3wVks6ptuyYvfWKWIFrr";
        String escola = "Fundatec";
        Integer vagas = 20;

        try {
            new CursoModel(id, nome, descricao, escola, vagas);
        } catch (DominioInvalido dominioInvalido) {
            String mensagemEsperada = "[descricao : Descrição deve possuir no máximo 200 caracteres]";
            Assertions.assertEquals(mensagemEsperada, dominioInvalido.getMessage());
        }
    }

}