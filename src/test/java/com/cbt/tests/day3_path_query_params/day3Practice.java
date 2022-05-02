package com.cbt.tests.day3_path_query_params;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class day3Practice {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI="https://api.github.com";
    }
    @Test
    public void test(){
       given().
                pathParam("username", "Sevda")./////////////////////////////////////////////
                when().get("/users/{username}").prettyPeek().
               then().statusCode(200);}

    @Test
    public void test2(){
        Map<String, String> map = new HashMap<>();//////////////////////////////////////////////////////////////////
        map.put("username", "Javid");//username-in value = Javid
        map.put("endpoint", "users"); //endpoint value = users
        //when many we use pathParams in plural
        given().pathParams(map).
                when().get("{endpoint}/{username}").prettyPeek().
                then().statusCode(200);}

    @Test
    public void test3(){
        Map<String, String> userler = new HashMap<>();
        userler.put("username", "Nizami");
        userler.put("endpoint", "users");

       Response response = given().
                pathParams(userler).
                when().get("{endpoint}/{username}").prettyPeek();
        response.then().statusCode(200);
        System.out.println("--statusCode--: "+response.statusCode()); }

    @Test
    public void test4(){
        Response response = given().
                pathParam("endpoint", "users").pathParam("username", "Qargi").
        when().
                get("{endpoint}/{username}").prettyPeek();
        response.then().statusCode(404);
        System.out.println("response.asString(): "+response.asString());
        assertThat(response.asString(),containsString("Not Found")); }

        @Test
    public void test5(){
        given().baseUri("http://api.cybertektraining.com").pathParam("id", 1).
               when().
                      get("/student/{id}").prettyPeek().
                then().
                statusCode(404); }}

