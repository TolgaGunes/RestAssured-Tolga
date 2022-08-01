package com.cybertek.day10;
import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SSLTest {

    @Test
    public void test1(){
        given().
                relaxedHTTPSValidation(). //even if it doesnt have valid certificate I want to send request
                when().get("https://untrusted-root.badssl.com/")
                .prettyPrint();
    }

    @Test
    public void keyStore() {

        given()
                .keyStore("pathtofile","password")
                .when().get("apiurl");
    }
}
