package com.cybertek.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {

        given().
                accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when()
                .get("http://3.80.210.194:8000/api/spartans/{id}").
                then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and()
                .body("id", equalTo(15),
                        "name", is("Meta"),
                        "gender",is("Female"),
                        "phone", is(1938695106));
    }
}
