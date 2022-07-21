package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with PAth method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"region_id\":2}").when()
                .get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        //print first CountryID
        String firstCountryID = response.path("items[0].country_id");
        System.out.println("firstCountryID = " + firstCountryID);

        // print second country name

        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // print "http://3.80.210.194:1000/ords/hr/countries/CA"
        String link = response.path("items[2].links[0].href");
        System.out.println("link = " + link);


        // get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions ids are equals to 2
        List<Integer> allRegionIDs = response.path("items.region_id");

        for (Integer regionID : allRegionIDs) {
            System.out.println("regionID = " + regionID);
            assertEquals(2, regionID);
        }

    }

    @DisplayName("Get request to /employees with query param")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG", jobID);
        }
        //TASK
        //print each name of IT Programmers

    }
}
