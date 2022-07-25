package com.cybertek.day5;

import com.cybertek.utilities.DBUtils;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanAPIvsDB {

    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1() {
        // get id,name,gender phone from database
        //get same information from api
        //compare

        String query = "SELECT SPARTAN_ID,NAME,GENDER,PHONE FROM SPARTANS\n" +
                "WHERE SPARTAN_ID =15;";

        //save data inside yhe map
        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);

        //2. get info from api
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType("application/json").extract().response();

        //deserialization here JSON to Java with Jackson objectMapper
        Map<String, Object> apiMap = response.as(Map.class);
        System.out.println(apiMap);

        //3. compare database vs api
        assertThat(apiMap.get("id"),is(dbMap.get("SPARTAN_ID")));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")) );

    }
}
