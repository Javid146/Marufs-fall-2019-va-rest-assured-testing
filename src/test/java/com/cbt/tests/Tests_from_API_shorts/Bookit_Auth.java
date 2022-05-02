package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Bookit_Auth {
/*AUTHENTICATION -> requires authenticity your id, by proving your details you access data
* AUTHORIZATION -> is about permission, you might have it or you might not no matter what*/
    String accessToken ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";
    @BeforeMethod
    public void setup(){ RestAssured.baseURI="https://cybertek-reservation-api-qa3.herokuapp.com/";}

    @Test
    public void test1(){
        //header is part of website requirement, where you need to type these details
        //can easily check these in postman first
        Response response=given().header("Authorization", accessToken)
                .when().get("/api/campuses");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPeek(); }}
