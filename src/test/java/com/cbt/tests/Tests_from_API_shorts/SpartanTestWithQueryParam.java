package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanTestWithQueryParam {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI= "http://52.207.61.129:8000";
    }

    @Test
    public void QueryParamTest(){
        /*given accept type: ContentType.JSON
         * And query param values:
         * gender: Female/Male
         * nameContains: default e/a
         * Better to check queryparams from Spartan documentation and verify with Postman first
         * queryparams should be written under Params section in Postman*/
        Response response = given().accept(ContentType.JSON)
                .queryParam("gender", "Female")
                .when().get("/api/spartans/search");
        System.out.println(response.statusCode());
        System.out.println("response.prettyPeek() = " + response.prettyPeek());
        Assert.assertTrue(response.body().asString().contains("Female")); }///////////////////////////////////

    @Test
    public void QueryParamTest2(){
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "Male");
        paramsMap.put("nameContains", "John");

        Response response = given().accept(ContentType.JSON)
                .queryParams(paramsMap)
                .when().get("/api/spartans/search");

        System.out.println(response.statusCode());
        System.out.println("response.asString() = " + response.asString());

        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("John")); }}
