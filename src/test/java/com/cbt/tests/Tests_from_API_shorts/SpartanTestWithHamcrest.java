package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class SpartanTestWithHamcrest {

    @BeforeClass
    public void setup(){
        baseURI= "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
                //request
                given().accept(ContentType.JSON)
                .pathParams("id", 550)
                .when().get("api/spartans/{id}").prettyPeek()
               //response
                .then().statusCode(200).and().
                assertThat().contentType("application/json"); }

    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .pathParams("id", 550)
                .when().get("api/spartans/{id}")
                .then().statusCode(200)
        .and().assertThat().contentType(ContentType.JSON)
                //body here is Hamcrest matcher that allows you to navigate and verify json file body
        .and().assertThat().body("id", Matchers.equalTo(550), "name", Matchers.equalTo("Jude"), "gender",
                Matchers.equalTo("Female"), "phone", Matchers.equalTo(8165328758l)); }}
