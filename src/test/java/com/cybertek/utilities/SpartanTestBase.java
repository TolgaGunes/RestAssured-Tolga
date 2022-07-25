package com.cybertek.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;


public class SpartanTestBase {
    @BeforeAll
    public static void init() {
        // save baseurl inside this variable so that we don't need to type each https method
        baseURI = "http://3.80.210.194:1521";

        String dbUrl = "jdbc:oracle:thin:@3.80.210.194:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";
    }

    @AfterAll
    public static void teardown() {

        DBUtils.destroy();
    }
}
