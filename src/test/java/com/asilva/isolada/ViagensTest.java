package com.asilva.isolada;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ViagensTest {

    @BeforeEach
    public void setup(){
        baseURI = "http://localhost";
        port = 8089;
        basePath = "/api";
    }

    @Test
    void testCadastroDeViagemValidaRetornoComSucesso() {

        String token =
        given()
            .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"email\": \"admin@email.com\",\n" +
                        "  \"senha\": \"654321\"\n" +
                        "}")
        .when()
            .post("/v1/auth")
        .then()
            .extract()
                .path("data.token");

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", token)
            .body("{\n" +
                    "  \"acompanhante\": \"Maria\",\n" +
                    "  \"dataPartida\": \"2021-12-12\",\n" +
                    "  \"dataRetorno\": \"2021-12-12\",\n" +
                    "  \"localDeDestino\": \"Manaus\",\n" +
                    "  \"regiao\": \"Norte\"\n" +
                    "}")
        .when()
            .post("/v1/viagens")
        .then()
                .statusCode(201);

    }
}
