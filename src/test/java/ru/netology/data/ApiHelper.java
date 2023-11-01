package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;

public class ApiHelper {
    private static final String sutUrl = System.getProperty("sut.url");
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(sutUrl)
            .setBasePath("/api/v1")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void paymentApprovedRequest(CardInfo card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/pay")
                .then()
                .statusCode(200)
                .body(anything("APPROVED"))
                .extract()
                .response()
                .asString();
    }

    public static void creditApprovedRequest(CardInfo card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/credit")
                .then()
                .statusCode(200)
                .body(anything("APPROVED"))
                .extract()
                .response()
                .asString();
    }
    public static void paymentDeclinedRequest(CardInfo card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/pay")
                .then()
                .statusCode(200)
                .body(anything("DECLINED"))
                .extract()
                .response()
                .asString();
    }
    public static void creditDeclinedRequest(CardInfo card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/credit")
                .then()
                .statusCode(200)
                .body(anything("DECLINED"))
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
                .statusCode(500)
                .body(Matchers.anything("400 Bad Request"));
    }

    public static void creditInvalidRequest(CardInfo card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/credit")
                .then()
                .statusCode(500)
                .body(Matchers.anything("400 Bad Request"));

    }

}
