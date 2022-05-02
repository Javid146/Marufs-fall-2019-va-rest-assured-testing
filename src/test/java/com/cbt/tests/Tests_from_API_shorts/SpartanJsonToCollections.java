package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanJsonToCollections {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI= "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when().get("api/spartans/{id}").prettyPeek();

        //convert json response to java collection (map)
        Map<String, Object> spartanMap = response.body().as(Map.class);//////////////////////////////////////////////
        System.out.println("id = " + spartanMap.get("id"));
        System.out.println("name = " + spartanMap.get("name"));
        //one example verification one side map/expected value
        Assert.assertEquals(spartanMap.get("name"), "Zarrina"); }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans/").prettyPeek();

        //below list of spartans is created and each item/spartan will be within map. so list will cover maps
        List<Map<String, Object>> listOfSpartans = response.body().as(List.class);
        //print all data of first spartan
        System.out.println("SPARTAN #0: "+listOfSpartans.get(0));//returns map for first spartan
        Map<String, Object> firstSpartan = listOfSpartans.get(0);//same as above
        System.out.println("firstSpartan = " + firstSpartan);
        System.out.println(firstSpartan.get("name"));

        int counter = 1;
        for (Map<String, Object> map : listOfSpartans){
            System.out.println(counter+" - spartan "+map);
            counter++;
        }
    }
}
