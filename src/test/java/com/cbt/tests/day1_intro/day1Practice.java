package com.cbt.tests.day1_intro;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class day1Practice {
    @Test
    public void test(){
    String name = "Javid";
    String ad = "Javid";
    int num = 5;
    int sum = 6;
    int bum = 6;

        assertThat(name, is(ad));
        Assertions.assertNotEquals(num, sum);
        Assertions.assertNotEquals(num, lessThan(sum));
        assertThat(sum, is(bum));
        assertThat(bum, is(not(num)));

        List<String> list = new ArrayList<>();
        list.add("Javid"); list.add("Qargi");
        assertThat(list, hasItems("Javid", "Qargi"));
        assertThat(list, hasSize(2));
        assertThat(list, notNullValue());

        List<String> list2 =  Arrays.asList("Shah", "Gurbat", "Khayal");
        assertThat(list2, containsInAnyOrder("Khayal","Shah","Gurbat"));/////////////////////////
        assertThat(list, is(not(list2)));///////////////////////////////////////////////////////////////

        List<String> list3 = Arrays.asList();
        assertThat(list3, empty());
        assertThat(list3, not(list2)); }

    @Test
    public void test2(){
        given().
                when().get("https://www.youtube.com/").prettyPeek().
                then().statusCode(200).and().contentType(ContentType.HTML).
                and().contentType("text/html; charset=utf-8");
}}
