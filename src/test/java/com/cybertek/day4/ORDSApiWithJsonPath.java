package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.geom.RectangularShape;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/countries");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));

        // get second country name with JsonPath
        JsonPath jsonPath = response.jsonPath();
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        //items.country_id
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);

        //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
    }


    @DisplayName("GET request to /employees with query param")
    @Test
    public void test2() {

        // we added limit query param to get 107 employes
        Response response = given().accept(ContentType.JSON)
                .queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> employeeITProgs = jsonPath.getList("items.findAll{it.job_id== \"IT_PROG\"}.email");
        System.out.println(employeeITProgs);

        // get me first name of employees who is making more than 10000
        List<String> firstName = jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println(firstName);

        // get the max salary first_name
        String kingFirstName = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);

        String kingNameWithPathMethod = response.path("items.max{it.salary}.first_name");
        System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);

    }
}
