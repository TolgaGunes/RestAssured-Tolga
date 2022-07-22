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
                        "gender", is("Female"),
                        "phone", is(1938695106));
    }

    @DisplayName("CBTraining Teacher ")
    @Test
    public void test2() {

        given().accept(ContentType.JSON).pathParam("id", 20450)
                .when().get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length", is("236"))
                .and()
                .header("Date", notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName", is("Alexander"),"teachers[0].lastName",is("Syrup"))
                .body("teachers[0].gender",equalTo("male"));
    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void test3() {

        //verify Alexander, Darleen,Sean inside all teachers
        given().accept(ContentType.JSON).get("http://api.cybertektraining.com/teacher/all")
                .then().statusCode(200)
                .body("teachers.firstName", hasItems("Alexander"));
    }
}
