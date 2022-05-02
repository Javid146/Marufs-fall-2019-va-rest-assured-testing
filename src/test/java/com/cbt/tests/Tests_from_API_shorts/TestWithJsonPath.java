package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestWithJsonPath {

    @BeforeClass
    public void setup(){
        baseURI= "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        Response response= given().accept(ContentType.JSON)
                .pathParam("id", 550)
                .when().get("api/spartans/{id}");

        response.then().statusCode(200);
        //read id with path()
        int id = response.path("id");///////////////////////////////////////////////////
        String name = response.body().path("name");
        System.out.println("name = " + name);
        System.out.println("id = " + id);
        //read value/id with jsonpath()
        JsonPath jsonData=response.jsonPath();///////////////////////////////////////////////

        int id1 = jsonData.getInt("id");
        String name1 = jsonData.getString("name");
        String gender = jsonData.getString("gender");
        long phone = jsonData.getLong("phone");

        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        Assert.assertEquals(id1, 550);
        Assert.assertEquals(name1, "Jude");
        Assert.assertEquals(gender, "Female");
        Assert.assertEquals(phone, 8165328758l); }}
