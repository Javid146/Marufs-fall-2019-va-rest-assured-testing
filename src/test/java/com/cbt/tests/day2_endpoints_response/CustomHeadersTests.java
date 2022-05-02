package com.cbt.tests.day2_endpoints_response;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CustomHeadersTests {
/*Accept --> is a header that is part of request.
It is used to indicate in what format we want the response in
if i want the response to json, i send with header application/json */
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://52.207.61.129:8000";
    }
    /**
     * send a get request to http://54.224.118.38:8000/api
     * include header Accept, value = application
     * verify that response content type is xml
     */

    @Test
    public void xmlTest(){
        given().
                log().all().
                header("Accept", "application/xml").///////////////////////////////////////////////
        when().
                get("/api/spartans").
        then().
                log().all().
                contentType(ContentType.XML); }

    @Test
    public void testJson(){
        given().
                log().all().
                header("Accept", "application/json").
        when().
                get("/api/spartans").
        then().
                log().all().
                contentType(ContentType.JSON); }

    @Test
    public void defaultType(){
        given().
                log().all().
        when().
                get("/api/spartans").
        then().
                log().all().
                contentType(ContentType.JSON); }}
