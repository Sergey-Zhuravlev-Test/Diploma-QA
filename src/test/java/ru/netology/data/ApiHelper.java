package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setBasePath("/api/v1")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void paymentRequest(CardInfo card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/pay")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
    }

    public static void creditRequest(CardInfo card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/credit")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
    }

    public static void paymentInvalidRequest(CardInfo card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/pay")
                .then()
                .statusCode(400);
    }

    public static void creditInvalidRequest(CardInfo card) {
        given()
                .spec(requestSpec)
                .body(card)
                .post("/credit")
                .then()
                .statusCode(200);
    }

}
