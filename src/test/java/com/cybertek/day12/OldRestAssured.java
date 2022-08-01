package com.cybertek.day12;

import com.cybertek.utilities.SpartanNewBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OldRestAssured extends SpartanNewBase {


    @Test
    public void getAllSpartan() {

        given()
                .accept(ContentType.JSON)
        .and()
                .auth().basic("admin", "admin")
                .log().all()
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .body("id[5]",is(199))
                .log().all();
    }

    @Test
    public void test2() {
        given()
                .accept(ContentType.JSON)
        .and()
                .auth().basic("admin", "admin")
                .log().all()
        .expect()
                .statusCode(200)
        .and()
                .contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .body("id[5]",is(199))
        .when()
                .get("/spartans");
    }
}
