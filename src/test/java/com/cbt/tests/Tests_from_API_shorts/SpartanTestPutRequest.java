package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanTestPutRequest {

    @BeforeClass
    public void setup() {
        baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void putRequest() {
        //ways to send json body: 1.String, 2.collection (Map), 3.POJO
        Map<String,Object> putMap =new HashMap<>();
        putMap.put("name", "JavidUpdate");
        putMap.put("gender", "Male");
        putMap.put("phone", 4758237746l);

        //we send request put with updated json body
        given().contentType(ContentType.JSON)
                .pathParam("id", 29)
                .body(putMap)
                .when().put("api/spartans/{id}").prettyPeek()
        .then().assertThat().statusCode(204);//204 means no content, but later if we run details of spartan with get request
        //it will be shown in console.
        given().when().get("api/spartans/29")
        //verifying data like below without creating response object is called CHAINING
                .prettyPeek().then().statusCode(200); }

    @Test
    public void patchRequest() {
        Map<String,Object> patchMap =new HashMap<>();
        /*for patch request we don't need whole json body all needed is one line from body that identifies the spartan
        * So we change that line and use patch request to change it in json.
        * later we use get request to see whether it changed*/
        patchMap.put("phone", 5555555555l);

        given().contentType(ContentType.JSON)
                .pathParam("id", 29)
                .body(patchMap)
                .when().patch("api/spartans/{id}").prettyPeek()
                .then().assertThat().statusCode(204);//204 means no content, but later if we run details of spartan with get request
        //it will be shown in console.
        given().when().get("api/spartans/29")
                //verifying data like below without creating response object is called CHAINING
                .prettyPeek().then().statusCode(200); }}