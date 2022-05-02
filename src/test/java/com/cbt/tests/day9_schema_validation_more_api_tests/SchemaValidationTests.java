package com.cbt.tests.day9_schema_validation_more_api_tests;
import com.cbt.pojos.Spartan;
import com.cbt.utilities.ConfigurationReader;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

/*json schema --> document that describes a json file, it tells how the json must be structured, what fields it will have, what data types they will have
xsd file --> document that describes an xml file, it tells how the xml must be structured, what fields it will have, what data types they will have*/
public class SchemaValidationTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan_base_url");
        RestAssured.authentication = basic("admin", "admin"); }/////////////////////////////////////////////

    @Test
    public void validateJsonInClassPath() {
             given().
                    pathParam("id", 44).//spartan websitedan id 44 olan spartan gotur
             when().
                     get("api/spartans/{id}").prettyPeek().
             then().
                     statusCode(200).//check if that spartan matches with json file type (spartan-schema.json in resources) using JsonSchemaValidator
                     body(JsonSchemaValidator.matchesJsonSchemaInClasspath("spartan-schema.json"));///////////////////////////////////////
            }

    @Test
    public void validateJsonNotInClassPath(){
        // when we want to use a file from specific location, we create file object, then pass with to the JsonSchemaValidator
        File file = new File("src/test/resources/spartan-schema.json");/////////////////////////////////////////////////////////////

        given().
                pathParam("id", 45).
        when().
                get("api/spartans/{id}").prettyPeek().
        then().
                statusCode(200). //check if that spartan matches with json file type (spartan-schema.json in resources) using JsonSchemaValidator
                body(JsonSchemaValidator.matchesJsonSchema(file)); }///////////////////////////////////////////////////////

    @Test
    public void validatePostSpartanResponse(){
        // create spartan pojo object
        Faker faker = new Faker();
        String gender = faker.bool().bool()? "Male" : "Female";
        Spartan spartan = new Spartan(faker.name().firstName(), gender, faker.number().digits(10));
        System.out.println("spartan = " + spartan);

        given().
                contentType(ContentType.JSON).
                body(spartan)./////////////////////////////////////////////////////////////////////
        when().
                post("api/spartans").prettyPeek().
        then().
                statusCode(201).
                body(JsonSchemaValidator.matchesJsonSchemaInClasspath("post-spartan-schema.json")); }

    @Test
    public  void validateXMLResponse(){
       given().
               accept(ContentType.XML).//////////////////////////////////////////////////////////////////////////////////
       when().
               get("api/spartans").
               prettyPeek().
       then().
               statusCode(200).//check if that spartan matches with xml type (all-spartans.xsd in resources) using RestAssuredMatchers
                body(RestAssuredMatchers.matchesXsdInClasspath("all-spartans.xsd")); }//////////////////////////////////////

    @Test
    public void validateXMLResponseNotInClassPath(){
        File file = new File("src/test/resources/all-spartans.xsd");
        given().
                header("Accept", "application/xml")./////////////////////////////////////////////////////////
        when().
                get("api/spartans").
                prettyPeek().
        then().
                statusCode(200).//check if that spartan matches with xml type (all-spartans.xsd in resources) using RestAssuredMatchers
                body(RestAssuredMatchers.matchesXsd(file)); }}/////////////////////////////////////////////////////////
