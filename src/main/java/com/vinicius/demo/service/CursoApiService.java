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

        validarCursoDuplicado(novoCurso.getNome(), novoCurso.getId());
        repository.save(novoCurso);
    }
    public CursoModel buscarPorNome(String nome) {

        if(nome == null || nome.isBlank()) {

            throw new NomeInvalidoException("Nome inválido!");
        }
        return repository.buscarCursoPorNome(nome);
    }
    public List<CursoModel> listarPorNome(String nome) {

        if(nome == null || nome.isBlank()) {

            throw new NomeInvalidoException("Nome inválido!");
        }
        return repository.buscarCursos(nome);
    }
    public void deletarCurso(Long id) {

        if(id == null) {

            throw new IllegalArgumentException("Id não pode ser null!");
        }
        repository.deleteById(id);
    }
    public void editarCurso(CursoModel novoCurso) {

        validarCursoDuplicado(novoCurso.getNome(), novoCurso.getId());
        repository.save(novoCurso);
    }
    public void editarCursoParcialmente(CursoModel novoCurso) {

        var cursoCadastrado = this.repository.findById(novoCurso.getId());
        var nome = this.valorNovoOuPadrao(novoCurso.getNome(), cursoCadastrado.get().getNome());
        var descricao = this.valorNovoOuPadrao(novoCurso.getDescricao(), cursoCadastrado.get().getDescricao());
        var escola = this.valorNovoOuPadrao(novoCurso.getEscola(), cursoCadastrado.get().getEscola());
        var vagas = this.valorNovoOuPadrao(novoCurso.getVagas(), cursoCadastrado.get().getVagas());

        var cursoMerge = new CursoModel(novoCurso.getId(), nome, descricao, escola, vagas);
        this.validarCursoDuplicado(cursoMerge.getNome(), cursoMerge.getId());
        this.repository.save(cursoMerge);

    }
    private <T> T valorNovoOuPadrao(T novoValor, T valorCadastrado) {

        if (novoValor == null) {

            return valorCadastrado;
        } else {
            return novoValor;
        }
    }
    private void validarCursoDuplicado(String nome, Long id) {

        var quantidade = repository.contarPorNomeEComIdDiferente(nome, id);
        if (quantidade != 0) {
            throw new RegistroJaCadastrado("Registro já cadastrado");
        }
    }
}

