package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {

    @BeforeAll
    public static void init() {

        //save baseURL inside this variable so that we do not need to type each http method
        baseURI = "http://3.80.210.194:1000/ords/hr";

    }
}
