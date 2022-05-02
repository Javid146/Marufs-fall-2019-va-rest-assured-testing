package com.cbt.tests.day5_authentication_authorization;
import groovy.json.JsonException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
public class TokenBasedAuthentication {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI="https://library2.cybertekschool.com/rest/v1/"; }

    @Test
    public void tokenAuthentication() throws JsonException {
        // get token from the login method
        String token = given().
                            log().all().
                            formParam("email", "student27@library").
                            formParam("password", "kkMksO2i").
                     when().post("/login").jsonPath().getString("token");
        // use the token to get all book categories
        given().
                header("x-library-token", token).
                log().all().
        when().
            get("/get_book_categories").
            prettyPeek().
        then().statusCode(200); }

    /*   get token as student then add a new book verify status code 403  */
    @Test
    public void getTokenAddBook(){
//        Map<String, Object>list = new HashMap<>();
//        list.put("name","Javid"); list.put("isbn","weq"); list.put("year",2000); list.put("author","Javid"); list.put("book_category_id", 5); list.put("description","yeap");
        String token = given().////////////////////////////////////////////////////////////////////////////////////////
                            formParam("email", "student27@library").
                            formParam("password", "kkMksO2i").
                    when().
                            post("/login").
                            jsonPath().getString("token");
        System.out.println("token = " + token);

        given().
//                formParams(list).
                header("x-library-token", token).
        when().
                post("/add_book").
                prettyPeek().
        then().
                statusCode(403); }
//
//    @Test
//    public void oauth2(){
//        // this token is temporary. after sometime this code may not work
//        given().
//               auth().oauth2("06d395e2eabddd0ab67e7573b65fe640c469226f").
//        when().
////                get("https://api.github.com/repos/javid146/git").
//                get("https://api.github.com/repos/marufjont/secret-repository").
//                prettyPeek().
//        then().
//                statusCode(200); }
}
