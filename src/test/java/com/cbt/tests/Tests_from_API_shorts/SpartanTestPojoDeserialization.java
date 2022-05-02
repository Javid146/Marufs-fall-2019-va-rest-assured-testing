package com.cbt.tests.Tests_from_API_shorts;
import com.google.gson.Gson;
import com.mongodb.util.JSON;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SpartanTestPojoDeserialization {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI= "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when().get("api/spartans/{id}").prettyPeek();

    //convert json response to Spartan class
    Spartan spartan1 = response.body().as(Spartan.class);
        System.out.println("spartan1 = " + spartan1);

        //verify each key with Spartan class objects
        Assert.assertEquals(spartan1.getId(), 500);
        Assert.assertEquals(spartan1.getName(), "Zarrina");
        Assert.assertEquals(spartan1.getGender(), "Female");
        Assert.assertEquals(spartan1.getPhone(), new Long(12345678900l)); }

    @Test
    public void gsonExample(){
        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "    \"id\": 500,\n" +
                "    \"name\": \"Zarrina\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 12345678900\n" +
                "}";

        //using gson method to deserialize json response (deserialize -> from json to java)
        Spartan spartanMeta = gson.fromJson(myJsonBody, Spartan.class);
        System.out.println(spartanMeta);

        //serialization: java object -> JSON body
        Spartan spartan = new Spartan(101, "Mike", "Male", 321342123l);
        //converting custom class to json (serialization)
        String jsonbody = gson.toJson(spartan);
        System.out.println("jsonbody = " + jsonbody); }}
