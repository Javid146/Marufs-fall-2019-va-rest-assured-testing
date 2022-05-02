package com.cbt.tests.day4_query_params_jsonpath_resp_body_validation;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MoreQueryParameters {

    @BeforeAll
    public static void setUp(){RestAssured.baseURI = "http://api.exchangeratesapi.io/latest?access_key=273c015b35128643a62a0ceabe06073a"; }

    @Test
    public void symbolsTest(){
        given().
               queryParam("symbols","USD").
               log().all().
        when().
//                get("/latest").
//                prettyPeek().
        then().
                statusCode(200); }

    @Test
    public void baseAndSymbolsTest(){
        given().
                log().all().
                queryParam("symbols", "USD").
                queryParam("base", "PHP").
        when().
        then().
                statusCode(200); }

    @Test
    public void baseAndSymbolsTestWithMap(){
        Map<String, String> parametersMap=new HashMap<>();
        parametersMap.put("base", "PHP");
        parametersMap.put("symbols", "USD,GBP");

        given().
                log().all().
                queryParams(parametersMap).
        when().
//                get("/latest").
//                prettyPeek().
        then().statusCode(200); }}
