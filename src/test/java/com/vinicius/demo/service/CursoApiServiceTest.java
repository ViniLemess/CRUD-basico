package com.vinicius.demo.service;

import com.vinicius.demo.infra.repository.CursoRepository;
import com.vinicius.demo.model.CursoModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CursoApiServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    private CursoApiService cursoApiService;

    @BeforeEach
    protected void inicializar() {

        MockitoAnnotations.openMocks(this);
        cursoApiService = new CursoApiService(cursoRepository);
    }
    @Test
    protected void deveCadastrarCursoComSucesso() {

        var curso = new CursoModel(66748229065L, "Curso Técnico Informática", "Curso com enfâse em programação", "Fundatec", 20);
        cursoApiService.salvar(curso);
//        Mockito.verify(cursoRepository, Mockito.times(1)).contarCursoPorNome(anyString());
        Mockito.verify(cursoRepository, Mockito.times(1)).save(any(CursoModel.class));
    }
    @Test
    protected void deveValidarIdInvalidoDeCurso() {

        try {
            var curso = new CursoModel(111L, "Curso Técnico Informática", "Curso com enfâse em programação", "Fundatec", 20);
            cursoApiService.salvar(curso);

        } catch (Exception exception) {

            Assertions.assertEquals("Id inválida!", exception.getMessage());
        }
    }
//    @Test
//    protected void deveValidarCursoDuplicado() {
//
//        try {
//            when(cursoRepository.contarCursoPorNome(anyString())).thenReturn(1L);
//            var curso = new CursoModel(66748229065L, "Curso Técnico Informática", "Curso com enfâse em programação", "Fundatec", 20);
//            cursoApiService.salvar(curso);
//        } catch (Exception exception) {
//
//            Assertions.assertEquals("Registro já cadastrado", exception.getMessage());
//        }
//    }
}