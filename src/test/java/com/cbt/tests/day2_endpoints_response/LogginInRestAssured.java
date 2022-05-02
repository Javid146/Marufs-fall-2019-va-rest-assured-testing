package com.cbt.tests.day2_endpoints_response;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LogginInRestAssured {

    @BeforeAll
    public static void setup(){ RestAssured.baseURI = "http://api.exchangeratesapi.io/v1/"; }

    @Test
    public void test1(){
        // request logging
        // log everything
        RestAssured.
                given().log().everything().
                when().get("latest?access_key=273c015b35128643a62a0ceabe06073a").
                then().statusCode(200);
        // log only the request url
        RestAssured.
                given().log().uri().
                when().get("latest?access_key=273c015b35128643a62a0ceabe06073a").
                then().statusCode(200);
        // log only the request url
        RestAssured.
                given().log().method().
                when().get("latest?access_key=273c015b35128643a62a0ceabe06073a").
                then().statusCode(200); }

    @Test
    public void logReqifFails(){
        // log if the test fails
        RestAssured.
                given().log().ifValidationFails()./////////////////////////////////////////////////////////////////
            when().get("latest?access_key=273c015b35128643a62a0ceabe06073a").
                then().statusCode(200); }

    @Test
    public void test2(){
        // print only body
        RestAssured.
                when().get("latest?access_key=273c015b35128643a62a0ceabe06073a").
                then().log().body().
                statusCode(200); }

    @Test
    public void printFailedResp(){
        RestAssured.
                when().get("latest?access_key=273c015b35128643a62a0ceabe06073a").
                then().log().ifValidationFails().
                statusCode(200); }

    @Test
    public void printReqAndRes(){
        RestAssured.
                given().
                        log().ifValidationFails().
                when().
                    get("latest?access_key=273c015b35128643a62a0ceabe06073a").
                then().
                        log().ifValidationFails().
                statusCode(200); }}
