package com.cybertek.day8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import com.cybertek.utilities.SpartanAuthTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class BookItTest {


    @BeforeAll
    public static void init() {
        //save base url inside this variable so that we do not need to type each http method

        baseURI = "https://cybertek-reservation-api-qa2.herokuapp.com";

    }

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjAyIiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.UQnmL58LBoFW-Opm5OPIv7AgFvupRq4cANOIBQdOlpI";

    @DisplayName("GET all campuses")
    @Test
    public void testAuth1 () {

        given().header("Authorization", accessToken)
                .and().accept(ContentType.JSON)
                .when().get("/api/campuses")
                .then().statusCode(200).log().all();

    }
}
