package com.cbt.tests.day7_more_serialization_deserialization_post_delete;
import com.cbt.pojos.Spartan;
import com.cbt.utilities.ConfigurationReader;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class GetAndPostExample {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan_base_url");
    }

    @Test
    public void testGet(){
        Response response =
                given().
                        auth().basic("admin", "admin").
                        pathParam("id", 13).
                when().
                        get("/api/spartans/{id}").prettyPeek();

        response.then().statusCode(200);
//        Spartan spartan = response.as(Spartan.class);
//        System.out.println("spartan.getName() = " + spartan.getName());
        Map<String,Object> list = response.as(Map.class);
        System.out.println(list); }

    @Test
    public void testPost(){
        // create a spartan pojo with some random name, gender, phone number
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender;
        if (faker.bool().bool()) {
            gender = "Female";
        } else {
            gender="Male";
        }
        String phone = faker.number().digits(10);

        Spartan newSpartan = new Spartan(name, gender, phone);
        System.out.println("newSpartan = " + newSpartan);

        given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                body(newSpartan).///////////////////////////////////////////////////////////////////////////////////////
                log().all().
        when().
                post("/api/spartans/").prettyPeek().
        then().
                statusCode(201); }

    // end to end testing
    // create a new spartan
    // get the id of the new spartan
    // use the get single spartan endpoint to get that new spartan info using it
    // verify 200
    // delete that spartan using the id
    // use the get single spartan endpoint to get that new spartan info using it
    // 404
    @Test
    public void endToEndTesting(){
        // create pojo
        Faker faker = new Faker();
        String name = faker.name().firstName();

        String gender;
        if (faker.bool().bool()) {
            gender = "Female";
        } else {
            gender="Male"; }

        String phone = faker.number().digits(10);
        Spartan spartan = new Spartan(name, gender, phone);
        // create new spartan, verify 201, save id
        Response postResponse =
                                given().
                                    log().all().
                                    auth().basic("admin", "admin").
                                    contentType(ContentType.JSON).
                                    body(spartan).//////////////////////////////////////////////////////////////////////
                                when().
                                    post("/api/spartans").prettyPeek();

        postResponse.then().statusCode(201).and().body("success", is("A Spartan is Born!"));/////////////////////////
        //data is detail from console when spartan is created
        int id = postResponse.path("data.id");/////////////////////////////////////////////////////////////////////
       String ad = postResponse.path("data.name");///////////////////////data.name is also from console
        System.out.println("id = " + id);
        System.out.println("ad = " + ad);
        // get the spartan information using the id
       Response getResponse= given().
                auth().basic("admin", "admin").
                pathParam("id", id).
        when().
                get("api/spartans/{id}").prettyPeek();
        getResponse.then().statusCode(200);

//        Spartan newBornSpartan = getResponse.as(Spartan.class);
        spartan.setId(id);
//        assertThat(newBornSpartan, samePropertyValuesAs(spartan));
        // delete that spartan using id
        given().
                auth().basic("admin", "admin").
                pathParam("id", id).
        when().
                delete("api/spartans/{id}").
                prettyPeek().
        then().
                statusCode(204);
       // verify that thing is deleted
       given().
//            auth().basic("admin", "admin").
            pathParam("id", id).
       when().
                get("api/spartans/{id}").prettyPeek().
       then().
               statusCode(404).
       and().
                body("error", is("Not Found")); }}///////////////////////////////////////////////////////////
