package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanWithXML extends SpartanAuthTestBase {

    @DisplayName("GET request to api/spartans and verify xml")
    @Test
    public void getSpartanXml() {

        given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/xml;charset=UTF-8")
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].gender", is("Male"))
                .log().all();
    }

    @DisplayName("GET request to api/spartans with xmlPath")
    @Test
    public void getSpartanXmlPAth() {

        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans");


        // get response xml body/payload and save inside the xmlpath object
        XmlPath xmlPath = response.xmlPath();

        // get first spartan name
        String name = xmlPath.getString("List.items[0].name");
        System.out.println("name = " + name);

        // get the 3rd spartan id number
        int id = xmlPath.getInt("List.item[2].id");
        System.out.println("id = " + id);

        //how to get all name and save into lst of string
        List<String> names = xmlPath.getList("List.item.name");
        System.out.println("names = " + names);

        // how to get value of attribute



    }

}
