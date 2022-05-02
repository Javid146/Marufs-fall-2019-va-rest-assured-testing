package com.cbt.tests.Tests_from_API_shorts;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.given;

public class SpartanTestWithPathParameters {

    @BeforeClass
    public void setup(){
        baseURI= "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        System.out.println("json version = " + response.prettyPeek());

        /*print values of json keys. So EXAMPLE BELOW IS JSON TYPE OF RESPONSE.
        id, name, gender, phone are json keys
        AND BY USING System.out.println(response.body().path()); WE CAN FIND KEYS OF JSON
        {      "id": 500,
                "name": "Zarrina",
                "gender": "Female",
                "phone": 12345678900    }*/

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.body().path("gender");
        long phone = response.body().path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id,500);
        assertEquals(name, "Zarrina");
        assertEquals(gender, "Female"); }

    @Test
    public void test2(){
        Response response=get("/api/spartans");
        //extract first spartan's id
        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);
        
        //first spartan firstname
        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);
        //last first name
        String lastFirstname = response.path("name[-1]");
        System.out.println("lastFirstname = " + lastFirstname);
        //extract all firstnames and print
        List<String> names = response.path("name");
        System.out.println("names.size() = " + names.size());
        System.out.println("names = " + names);
        List <Object> phoneNumber = response.path("phone");
        for(Object phone : phoneNumber){
            System.out.println("phone = " + phone);}}}
