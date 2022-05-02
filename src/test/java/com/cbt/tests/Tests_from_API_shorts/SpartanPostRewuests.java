package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanPostRewuests {

    @BeforeClass
    public void setup() {
        baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void postWithString() {
        //create new spartan with java
        Response response=given().accept(ContentType.JSON)//means we also accept json type
                .and().contentType(ContentType.JSON)//means we are sending json type
                .body("{\n" +
                        "            \"name\": \"Mike\",\n" +
                        "            \"gender\": \"Male\",\n" +
                        "            \"phone\": 4758237746\n" +
                        "        }")
                .when().post("api/spartans/");

                response.prettyPeek();

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.contentType(), "application/json");
        //verify success message
        Assert.assertEquals(response.path("success"),"A Spartan is Born!");

        //verify request body OF NEWLY CREATED SPARTAN
        JsonPath json = response.jsonPath();
        //"data" here is json that is printed in console when we run
        Assert.assertEquals(json.getString("data.name"), "Mike");
        Assert.assertEquals(json.getString("data.gender"), "Male");
        Assert.assertEquals(json.getLong("data.phone"), 4758237746l); }

    @Test
    public void PostMethodWithMap(){
        //create a map to be used as a body for post request
        //repeat same process as above but use map this time
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "MikeMap");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 4758237746l);

        Response response=given().accept(ContentType.JSON)//means we also accept json type
                .and().contentType(ContentType.JSON)//means we are sending json type
                .body(requestMap)
                .when().post("api/spartans/");

        Assert.assertEquals(response.statusCode(),201);
        response.prettyPeek(); }

    @Test
    public void PostWithPojos(){
        //create Spartan.class object and use as a body for post request
        Spartan spartan = new Spartan();
        spartan.setName("MikePOJO");
        spartan.setGender("Male");
        spartan.setPhone(2312423423432l);

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/api/spartans/");
        response.prettyPeek();

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.contentType(),"application/json");

        //----GET REQUEST------
       Response response2 = given().accept(ContentType.JSON).and().
                pathParams("id", "1339")
                .when().get("api/spartans/{id}");

       Spartan spartanResponse = response2.body().as(Spartan.class);
        System.out.println("spartanResponse = " + spartanResponse);
        System.out.println(spartanResponse.getGender());//from spartan class
        System.out.println(spartanResponse.getName());//from spartan class
    }
}