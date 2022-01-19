package com.vinicius.demo.model;

import com.vinicius.demo.model.annotations.Cpf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CursoModel extends Entidade{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome do curso deve ser informado!")
    @NotEmpty(message = "Nome do curso deve ser informado!")
    private String nome;

    @Size(max = 200, message = "Descrição deve possuir no máximo 200 caracteres")
    private String descricao;

    @NotNull(message = "Instituição de ensino do curso deve ser informada!")
    @NotEmpty(message = "Instituição de ensino do curso deve ser informada!")
    private String escola;

    @NotNull(message = "numero de vagas do curso deve ser informado!")
    private Integer vagas;

    public CursoModel(Long id, String nome, String descricao, String escola, Integer vagas) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.escola = escola;
        this.vagas = vagas;
        isValid();
    }

    public Long getId() { return id; }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEscola() {
        return escola;
    }

    public Integer getVagas() {
        return vagas;
    }
}
