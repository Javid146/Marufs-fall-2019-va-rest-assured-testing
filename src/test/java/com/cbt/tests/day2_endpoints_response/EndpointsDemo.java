package com.cbt.tests.day2_endpoints_response;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EndpointsDemo {
    // FULL URL :   http://api.openrates.io/latest
   // BASE -->  http://api.openrates.io
   // endpoint --> /latest
    // rest assured will add the base to the path to create the final url

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://api.exchangeratesapi.io/v1/"; }

    @Test
    public void getLatest(){
        RestAssured.
                when().get("latest?access_key=273c015b35128643a62a0ceabe06073a").
                        prettyPeek().
                then().statusCode(200); }

    @Test
    public void getHistoricRate(){
        RestAssured.
                when().get("/2022-01-01?access_key=273c015b35128643a62a0ceabe06073a").prettyPeek().
                then().statusCode(200);
    }}
