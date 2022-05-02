package com.cbt.tests.day4_query_params_jsonpath_resp_body_validation;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class QueryParamMovieAPI {
    /*title = Avatar
    apiKey = e0484f01 */
    @Test
    public void test(){
        given().
               log().all().
               queryParam("apiKey", "e0484f01").
               queryParam("t", "Titanic").
        when().
            get("https://omdbapi.com/").
            prettyPeek().
        then().statusCode(200); }

    @Test
    public void test2(){
        Map<String, String> movie = new HashMap<>();
        movie.put("apiKey", "e0484f01");
        movie.put("t", "Titanic");

        given().params(movie).
                when().get("https://omdbapi.com/").prettyPeek().
                then().statusCode(200); }}
