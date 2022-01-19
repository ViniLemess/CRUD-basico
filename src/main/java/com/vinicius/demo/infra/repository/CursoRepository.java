package com.vinicius.demo.infra.repository;

import com.vinicius.demo.model.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Long> {


}
