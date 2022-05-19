package com.vinicius.demo.infra.repository;

import com.vinicius.demo.model.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Long> {

    @Query("SELECT c FROM CursoModel c WHERE c.nome LIKE %:nome%")
    public List<CursoModel> buscarCursos(@Param("nome") String nome);

    @Query("SELECT c FROM CursoModel c WHERE c.nome = :nome")
    public CursoModel buscarCursoPorNome(@Param("nome") String nome);

    @Query("SELECT count(c) FROM CursoModel c WHERE c.nome = :nome and (c.id != :id or :id is null)")
    public Long contarPorNomeEComIdDiferente(@Param("nome") String nome, @Param("id") Long id);
}
