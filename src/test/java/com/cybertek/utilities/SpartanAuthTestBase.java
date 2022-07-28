package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanAuthTestBase {,

    @BeforeAll
    public void init() {
    //save base url inside this variable so that we do not need to type each http method

        baseURI= "http://3.80.210.194:7000/";





    }


}
