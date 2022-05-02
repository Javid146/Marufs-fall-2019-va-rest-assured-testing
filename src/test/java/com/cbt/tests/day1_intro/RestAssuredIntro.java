package com.cbt.tests.day1_intro;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RestAssuredIntro {
    /** make a get request to api.zippopotam.us/us/90210
     * verify that status code is 200*/
    @Test
    public void testStatusCode(){
        RestAssured.
                given().
                when().get("https://api.zippopotam.us/").
                then().statusCode(200); }
    /*make a get request to http://api.openrates.io/latest
     * verify that status code is 200
     * 1 --> information
     * 2 --> success
     * 3 --> redirect
     * 4 --> client error
     * 5 --> server error*/
    @Test
    public void testStatusCode2(){
        RestAssured.
                when().get("https://exchangeratesapi.io/").
                then().statusCode(200); }

    @Test
    public void printResponse(){
        // prettyPeek --> prints the response
        RestAssured.
                when().get("https://exchangeratesapi.io/").
                        prettyPeek().
                then().statusCode(200); }

    /* make a get request to http://api.openrates.io/latest
     *  verify that status code is 200
     *  verify header response type application/json*/
    @Test
    public void verifyContentType(){
        // contentType --> verify the header content type
        RestAssured.
                when().get("https://exchangeratesapi.io/").
                        prettyPeek().
                then().statusCode(200).contentType("text/html");///////////////////////////////////////////////////////

        // SAME TEST BUT VERIFY HEADER DIFFERENTLY
        RestAssured.
                when().get("https://exchangeratesapi.io/").
                    prettyPeek().
                then().statusCode(200).contentType("text/html");
        // SAME TEST BUT VERIFY HEADER DIFFERENTLY DIFFERENTLY
        RestAssured.
                when().get("https://exchangeratesapi.io/").
                prettyPeek().
                then().statusCode(200).header("Content-Type", "text/html"); }/////////////////
    /**
     * make a get request to http://api.zippopotam.us/us/22102
     *  verify that status code is 200
     *  verify header response type application/json
     *  verify header Charset UTF-8
     */
    @Test
    public void testStatusCodeAndHeader(){
        RestAssured.
                when().
                        get("http://api.zippopotam.us/us/20007").
                        prettyPeek().
                then().statusCode(200).
                    and().contentType(ContentType.JSON).
                    and().header("Charset", equalTo("UTF-8")); }}//////////////////////////////////////







