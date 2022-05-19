package com.vinicius.demo.service;

import com.vinicius.demo.infra.repository.CursoRepository;
import com.vinicius.demo.model.CursoModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

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
        Mockito.verify(cursoRepository, Mockito.times(1)).contarPorNomeEComIdDiferente(anyString(), anyLong());
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
    @Test
    protected void deveDeletarCursoComSucesso() {

        cursoApiService.deletarCurso(1L);
        verify(cursoRepository, times(1)).deleteById(anyLong());
    }
    @Test
    protected void deveValidarDelecaoNula() {

        try {

            cursoApiService.deletarCurso(null);
        } catch (Exception exception) {

            Assertions.assertEquals("Id não pode ser null!", exception.getMessage());
        }
    }
    @Test
    protected void deveListarPorNomeComSucesso() {

        var cursoBuscado = "Técnico";
        var curso1 = new CursoModel(1L, "Técnico TI", "Técnico TI enfâse programação", "Fundatec", 20);
        var curso2 = new CursoModel(2L, "Técnico Redes", "Técnico Redes enfâse Cabeamento", "Fundatec", 31);
        List<CursoModel> cursosEsperados = List.of(curso1, curso2);
        when(cursoRepository.buscarCursos(anyString())).thenReturn(cursosEsperados);
        var cursosRecebidos = cursoApiService.listarPorNome(cursoBuscado);
        Assertions.assertEquals(cursosEsperados, cursosRecebidos);
        verify(cursoRepository, times(1)).buscarCursos(anyString());
    }

    @Test
    protected void deveValidarCursoDuplicado() {

        try {
            when(cursoRepository.contarPorNomeEComIdDiferente(anyString(), anyLong())).thenReturn(1L);
            var curso = new CursoModel(66748229065L, "Curso Técnico Informática", "Curso com enfâse em programação", "Fundatec", 20);
            cursoApiService.salvar(curso);
        } catch (Exception exception) {

            Assertions.assertEquals("Registro já cadastrado", exception.getMessage());
        }
    }
}