package com.cybertek.day6;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.Region;
import com.cybertek.pojo.Regions;
import com.cybertek.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest() {

        JsonPath jsonPath = get("/regions").then().statusCode(200)
                .extract().jsonPath();

        Region regions1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println(regions1);
        System.out.println("regions1.getRegion_id() = " + regions1.getRegionId());
        System.out.println("regions1.getRegion_name() = " + regions1.getRegion_name());
        System.out.println("regions1.getLinks().get(0).getHref() = " + regions1.getLinks().get(0).getHref());

    }

    @DisplayName("GET request to/employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet() {
        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);
        System.out.println(employee1);
    }

    @DisplayName("GET request to /regions only some fields test ")
    @Test
    public void regionPojoTest() {

        // send a get request and save everything imnside the regions object
        //since we prepare pojo also for the all properties, we don't need to use any path, so as() method is enough
        Regions regions = get("regions").then().statusCode(200).extract().response().as(Regions.class);

        //verify count is 4
        assertThat(regions.getCount(),is(4));

        //create empty list to store values
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        //get list of regions out of regions object
        List<Region> items = regions.getItems();

        //loop through each of the region, save their ids and names to empty list that we prepare
        for (Region eachRegionItem : items) {
            regionIds.add((eachRegionItem.getRegionId()));
            regionNames.add(eachRegionItem.getRegion_name());
        }
        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);

        // prepare expected result
        List<Integer> expectedRegionIDs = Arrays.asList(1, 2, 3, 4);
        List<String>expectedRegionNames =Arrays.asList("Europe","Americas","Asia","Middle East and Africa");

        //compare two result
        assertThat(regionIds, is(expectedRegionIDs));
        assertThat(regionNames, is(equalTo(expectedRegionNames)));

    }

    // send a get request to regions
    // with pojo
    // verify that region ids are 1,2,3,4
    // verify that regions names Europe, Americas, Asia, Middle East and Africa
    // verify that count is 4


}
