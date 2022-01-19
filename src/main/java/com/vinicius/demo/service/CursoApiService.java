package com.vinicius.demo.service;

import com.vinicius.demo.infra.excecao.NomeInvalidoException;
import com.vinicius.demo.infra.excecao.RegistroJaCadastrado;
import com.vinicius.demo.infra.repository.CursoRepository;
import com.vinicius.demo.model.CursoModel;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CursoApiService {

    private CursoRepository repository;

    public CursoApiService(CursoRepository cursoRepository) {
        this.repository = cursoRepository;
    }

    public void salvar(CursoModel novoCurso) {

//        validarCursoDuplicado(novoCurso.getNome());
        repository.save(novoCurso);
    }
//    public List<CursoModel> buscarPorNome(String nome) {
//
//        if(nome == null || nome.isBlank()) {
//
//            throw new NomeInvalidoException("Nome inválido!");
//        }
//        return repository.buscarPorNome(nome);
//    }
//
//    private void validarCursoDuplicado(String nome) {
//
//        var quantidade = repository.contarCursoPorNome(nome);
//        if (quantidade != 0) {
//            throw new RegistroJaCadastrado("Registro já cadastrado");
//        }
//    }
}

