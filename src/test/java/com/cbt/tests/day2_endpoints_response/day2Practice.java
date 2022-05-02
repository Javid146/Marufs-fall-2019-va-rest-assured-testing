package com.cbt.tests.day2_endpoints_response;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class day2Practice {

  @BeforeAll
  public static void base(){
  RestAssured.baseURI = "http://52.207.61.129:8000";}

  @Test
  public void test(){
        given().log().all().
        when().get("api/spartans/").prettyPeek().
        then().statusCode(200); }

  @Test
  public void test2(){
    Response response =  given().log().all().
            when().get("api/spartans/19").prettyPeek();
           response.then().statusCode(200);
    System.out.println("response.statusCode() = " + response.statusCode());
    System.out.println("response.statusLine() = " + response.statusLine());
    System.out.println("CONTENT TYPE: "+response.header("Content-Type"));/////////////////////////////////////
    System.out.println("HEADERS: "+response.headers());///////////////////////////////////////////////////////////
    System.out.println("DATE HEADER: "+response.header("date"));
    System.out.println("Keep-Alive HEADER: "+response.header("Keep-Alive"));

    String name = response.asString();
            //"Jeanelle";
      assertThat(name,containsString("Peri"));
      response.print();//prints all details about Jeanelle///////////////////////////////////////////////////////////
  }

  @Test
  public void test3(){
    Response response = given().log().all().
            when().get("api/spartans/62").prettyPeek();
            response.then().statusCode(200);
    
    List<String> adlar = Arrays.asList(response.asString());//////////////////////////////////////////////////
    System.out.println("adlar = " + adlar);
    assertThat(adlar, containsInAnyOrder(response.asString())); }}
