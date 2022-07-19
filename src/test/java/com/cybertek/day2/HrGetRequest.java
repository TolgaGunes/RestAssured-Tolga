package com.cybertek.day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HrGetRequest {

    //BeforeAll is an annotation equals to @BeforeClass in TestNg, we use static method name
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.80.210.194:1000/ords/hr";




    }



}
