package com.vinicius.demo.controller;

import com.vinicius.demo.model.CursoModel;
import com.vinicius.demo.service.CursoApiService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private CursoApiService service;

    public CursoController(CursoApiService cursoApiService) {
        this.service = cursoApiService;
    }

    @GetMapping(params = {"filtro"})
    public List<CursoModel> getCursos(@RequestParam("filtro") String filtro) {

        return service.listarPorNome(filtro);
    }
    @GetMapping
    public CursoModel getCurso(@RequestParam("nome") String nome) {

        return service.buscarPorNome(nome);
    }
    @PostMapping
    public void criarCurso(@RequestBody @Valid CursoModel cursoNovo) {

        service.salvar(cursoNovo);
    }
    @DeleteMapping
    public void deletarCurso(@RequestParam("Id") Long id) {

        service.deletarCurso(id);
    }
    @PutMapping
    public void atualizarCurso(@RequestBody @Valid CursoModel cursoAtualizado) {
        if(cursoAtualizado.getId() == null) {
            throw new IllegalArgumentException("Id deve ser informado para o update!");
        }
        service.editarCurso(cursoAtualizado);
    }
    @PatchMapping
    public void atualizarCursoParcialmente(@RequestBody CursoModel cursoAtualizado) {
        if(cursoAtualizado.getId() == null) {
            throw new IllegalArgumentException("Id deve ser informado para o update parcial!");
        }
        service.editarCursoParcialmente(cursoAtualizado);
    }
}
