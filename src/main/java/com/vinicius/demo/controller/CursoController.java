package com.vinicius.demo.controller;

import com.vinicius.demo.infra.repository.CursoRepository;
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

//    @GetMapping
//    public List<CursoModel> getCursos(@RequestParam("nome") String nome) {
//
//        return service.buscarPorNome(nome);
//    }

    @PostMapping
    public void criarCurso(@RequestBody @Valid CursoModel cursoNovo) {

        service.salvar(cursoNovo);
    }
}
