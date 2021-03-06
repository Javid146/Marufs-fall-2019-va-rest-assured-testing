package com.cbt.tests.office_hours;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiKeyExample {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://www.zipcodeapi.com/";
    }

    /*
    get distance between 2 zip codes
    verify status 200
    verify distance field not empty
    // /rest/:api_key/distance.json/:zip_code1/:zip_code2/:units
     */
    @Test
    public void testDistance() {
        given().
                pathParam("api_key", "DemoOnly00NR7VRQQ9nQLLuQnn19z0cyVjY3hPZzWGt4XxAIlq5MBfKF5vcVidm0").
                pathParam("zip_code1", "20005").
                pathParam("zip_code2", "20001").
                pathParam("units", "miles").
        when().
                get("/rest/{api_key}/distance.json/{zip_code1}/{zip_code2}/{units}").
                prettyPeek().
        then().
                statusCode(200).
                body("distance", not(emptyOrNullString())); }

    @Test
    public void testMB(){
        given().
                pathParam("api_key", "DemoOnly00NR7VRQQ9nQLLuQnn19z0cyVjY3hPZzWGt4XxAIlq5MBfKF5vcVidm0").
                pathParam("zip_code", "29577").
                pathParam("units", "degrees").
        when().
                get("/rest/{api_key}/info.json/{zip_code}/{units}").
                prettyPeek().
        then().
                assertThat().statusCode(is(200)).
        and().
                assertThat().body("city", is("Myrtle Beach"));

    }

}
