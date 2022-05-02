package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SpartanTestWithPathParams {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI= "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).
                //parth param is part of endpoint in url
                and().pathParam("id", 29)
                .when().get("/api/spartans/{id}");//{id} means 18

        Assert.assertEquals(response.statusCode(),200);
       Assert.assertEquals(response.contentType(),"application/json");
        System.out.println(response.prettyPeek());
        //Assert.assertTrue();
    }

    @Test
    public void NegativePathParam(){
        //make test fail by using wrong id number
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 55023)
                .when().get("/api/spartans/{id}");
        System.out.println("statusCode(): "+response.statusCode());
        System.out.println(response.prettyPeek());
        Assert.assertEquals(response.statusCode(),404);
       Assert.assertTrue(response.body().asString().contains("Not Found")); }}
