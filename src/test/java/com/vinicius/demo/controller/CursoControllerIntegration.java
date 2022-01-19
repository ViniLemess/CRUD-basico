package com.vinicius.demo.controller;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

class CursoControllerIntegration {

    @Test
    void deveCadastrarCursoComSucesso() {

        String request = "{\"nome\" : \"Curso Técnico em Informática\",\n" +
                "\"descricao\" : \"Curso de Informática com enfâse em programação.\",\n" +
                "\"escola\" : \"Escola Profissional Fundatec\",\n" +
                "\"vagas\" : 30\n" +
                "}";

        given()
                .header("Content-type", "application/json")
                .and()
                .body(request)
                .when()
                .post("/curso")
                .then()
                .statusCode(200);
    }
}