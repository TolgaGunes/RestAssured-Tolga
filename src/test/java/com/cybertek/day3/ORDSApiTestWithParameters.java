package com.cybertek.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithParameters {

    @BeforeAll
    public static void init() {

        //save baseURL inside this variable so that we do not need to type each http method
        baseURI = "http://3.80.210.194:1000/ords/hr";

    }

    @DisplayName("Get request to /countries")
    @Test
    public void test1() {
        Response response= given().accept(ContentType.JSON).and().queryParam("q","{\"region_id\": 3}").
        log().all()
                .when()
                .accept(ContentType.JSON).get(baseURI);


        assertEquals(200, response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();
    }




}
