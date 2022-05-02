package com.cbt.tests.day3_path_query_params;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class QueryParamsExamples {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://api.exchangeratesapi.io/v1/latest?access_key=273c015b35128643a62a0ceabe06073a";
    }
    /*
    call the exchangeratesapi with query param base=PHP
    verify status code 200
     */
    @Test
    public void testPHP(){
        given().
                log().all().
                queryParam("base", "EUR").///////////////////////////////////////////////////
        when().
////                get("/latest").
//                prettyPeek().
        then().statusCode(200); }}
