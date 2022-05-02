package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTests {

    /*IN API TESTING ALL DATA SHOULD BE CASE SENSITIVE.
    IP ADDRESS, HOST, ENDPOINTS (GET FROM API DOC OF WEBSITE),
    * QUERY PARAMS (GET FROM API DOC OF WEBSITE AND CAN VERIFY WITH POSTMAN)*/
    String spartBaseURI = "http://52.207.61.129:8000";

    @Test
    public void viewSpartanTest1(){
    Response response=RestAssured.get(spartBaseURI+"/api/spartans");
        System.out.println(response.statusLine());
       // System.out.println(response.asString());
        System.out.println(response.body().prettyPeek()); }

    @Test
    public void viewSpartanTest2(){
        Response response=RestAssured.get(spartBaseURI+"/api/spartans");
        Assert.assertTrue(response.statusCode()==200);
        Assert.assertEquals(response.statusCode(),200);

        Assert.assertTrue(response.body().asString().contains("John")); }///////////////////////////////////////////

    @Test
    public void viewSpartanTest3(){
        //accept means give us contentType in json not xml
       Response response = RestAssured.given().accept(ContentType.JSON).//can be ContentType.XML too
                when().get(spartBaseURI+"/api/spartans");

        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.contentType(): "+response.contentType()); }}
