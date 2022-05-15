package com.cbt.tests.day11_cookie_and_extent_report;
import com.cbt.utilities.ExtentConfig;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

@ExtendWith(ExtentConfig.class)
public class AnotherCookieTests {
    /*login to zero bank app using form params
        user_login: username and user_password: password*/
    @DisplayName("Another Get a cookie and access the reports using cookie")
    @Test
    public void getCookieTest() {
        // GET THE COOKIE USING LOGIN
        ExtentConfig.test.info("send request to get a cookie");
        Response postResponse =
                given().
                        formParam("user_login", "username").
                        formParam("user_password", "password").
                        log().all().
                        when().
                        post("http://zero.webappsecurity.com/signin.html");
        // cookie is returned as part of response.
        // getDetailedCookie  --> returns the cookie with given name
        Cookie cookie = postResponse.getDetailedCookie("JSESSIONID");////////////////////////////////////////////
        ExtentConfig.test.info("cookie = " + cookie.toString());
        System.out.println("cookie = " + cookie);
        System.out.println("cookie.getName() = " + cookie.getName());////////////////////////////////////////////////
        System.out.println("cookie.getValue() = " + cookie.getValue());////////////////////////////////////////////////

        // ACCESS THE APP USING THE COOKIE
        // send the request with cookie attached
        given().
                cookie(cookie).
                when().
                get("http://zero.webappsecurity.com/bank/online-statements.html").
                prettyPeek().
                then().
                statusCode(200).
                body(not(containsString("login"))); }

    @DisplayName("Another Make your own a cookie and access the reports using cookie")/////////////////////////////////
    @Test
    public void makeACookie() {
        // MAKE A COOKIE
        // cookies expire so if this does not work that means you need to change the cookie value
        // you can get the cookie value from the output of the previous example  on top
        Cookie cookie = new Cookie.Builder("JSESSIONID", "3899A142").build();////////////////////////////////
        ExtentConfig.test.info("Cookie: " + cookie.toString());
        // ACCESS THE APP USING THE COOKIE
        given().
                cookie(cookie).////////////////////////////////////////////////////////////////////////////////////////
                when().
                get("http://zero.webappsecurity.com/bank/online-statements.html").
                prettyPeek().
                then().
                statusCode(200).
                body(not(containsString("login")));
                ExtentConfig.test.info("done"); }}
