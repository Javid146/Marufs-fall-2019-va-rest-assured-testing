package com.cbt.tests.day5_authentication_authorization;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class day5practice {
    @BeforeAll
    public static void setup(){RestAssured.baseURI="https://library2.cybertekschool.com/rest/v1/"; }

    @Test
    public void test(){
        String token = given().log().all().////////////////////////////////////////////////////////////////////////////
                formParam("email", "student27@library").
                formParam("password", "kkMksO2i").
                when().post("/login").
                jsonPath().getString("token");//gets token from json text printed in console
        System.out.println("TOKEN: "+token);
        given().
                header("x-library-token", token).
        when().
                post("/add_book").
                prettyPeek().
                then().
                statusCode(403);//403 means unauthorized
    }}
