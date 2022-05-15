package com.cbt.tests.day4_query_params_jsonpath_resp_body_validation;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class JsonPathExample {
    @Test
    public void jsonPathExamples(){
        Response response = given().
                queryParam("symbols", "USD").
                queryParam("base", "PHP").
                when().
                get("http://api.exchangeratesapi.io/v1/latest?access_key=273c015b35128643a62a0ceabe06073a");
                //get("https://api.exchangeratesapi.io/latest");

        JsonPath jsonPath = response.jsonPath();////////////////////////////////////////////////////////
        jsonPath.prettyPrint();
        // STRING
        // get the base, date: keys in the top level object
        String base = jsonPath.getString("base");///////////////////////////////////////////////////
        System.out.println("base = " + base);
        String date = jsonPath.getString("date");
        System.out.println("date = " + date);
        // get the rate USD (not top level object)
        String usd = jsonPath.getString("rates.USD");
        System.out.println("usd = " + usd);

        // Jsonpath class can do automatic conversions to different types
        // DOUBLE
        double usdDouble = jsonPath.getDouble("rates.USD");////////////////////////////////////////////////
        System.out.println("usdDouble = " + usdDouble);
        // MAP. only object or key/value pairs can converted to map
        Map<String, Object> rates = jsonPath.getMap("rates");/////////////////////////////////////////////
        System.out.println("rates = " + rates);
        System.out.println("rates.get(\"USD\") = " + rates.get("USD"));

        // get the top level json into a map
        Map<String, Object> wholeObject = jsonPath.getMap("");
        System.out.println("wholeObject = " + wholeObject); }

    /*make query with base PHP verify that body contains base with value PHP, content type, status use body method*/
    @Test
    public void testEUR(){
        given().
                queryParam("base", "EUR").
        when().
                get("http://api.exchangeratesapi.io/v1/latest?access_key=273c015b35128643a62a0ceabe06073a").
        then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("base", is("EUR")); }
    /* make query with base PHP verify that body contains base with value PHP, content type, status use jsonpath */
    @Test
    public void testPHPAgain(){
        Response response = given().
                queryParam("base", "EUR").
                when().
                get("http://api.exchangeratesapi.io/latest?access_key=273c015b35128643a62a0ceabe06073a");
        response.then().statusCode(200).
                contentType(ContentType.JSON);

        String base = response.jsonPath().getString("base");
        assertThat(base, is("EUR")); }

    @Test
    public void jsonPathListExamples(){
        JsonPath jsonPath = when().get("http://api.cybertektraining.com/student/all").jsonPath();//////////////////////////////////////////////////
        // get the name of the first student from text in json format we got with the link above.
        String s1FirstName = jsonPath.getString("students[0].firstName");/////////////////////////////////////////////////////////////
        System.out.println("s1FirstName = " + s1FirstName);
        String s2FirstName = jsonPath.getString("students[1].firstName");
        System.out.println("s2FirstName = " + s2FirstName);
        // last students name
        String lastStudent = jsonPath.getString("students[-1].firstName");
        System.out.println("lastStudent = " + lastStudent);
        // get all first name
        List<String> list = jsonPath.getList("students.firstName");/////////////////////////////////////////////
        System.out.println("list.size() = " + list.size());
        System.out.println("list = " + list);
        // get first students id
        int s1Id = jsonPath.getInt("students[0].studentId");////////////////////////////////////////////////////
        System.out.println("s1Id = " + s1Id);
        // get first student on object
        Map<String, Object> s1 = jsonPath.getMap("students[0]");////////////////////////////////////////////////
        System.out.println("s1 = " + s1); }

    @Test
    public void jsonPathGetMatchingValue(){
        // find single match
        // let's find Elvira. get last name of the student whose name is equal to Vasyl
        JsonPath jsonPath = when().get("http://api.cybertektraining.com/student/all").jsonPath();
        String JohnsLastName = jsonPath.getString("students.find{it.firstName == 'John'}.lastName");/////////////////////////////////////////
        System.out.println("JohnsLastName = " + JohnsLastName);
        // find multiple matches
        // let's find the phone number of everyone whose firstName is vasyl
        List<String> CompanyIDs = jsonPath.getList("students.findAll{it.firstName=='John'}.company.companyId");//////////////////////////////
        System.out.println("CompanyIDs = " + CompanyIDs);}

    /* get all students verify that every student has a valid id (0 or bigger number)  */
    @Test
    public void verifyIds(){
        when().
                get("http://api.cybertektraining.com/student/all").
        then().
                statusCode(200).
                // everyItem --> from hamcrest, used to verify every element from a list separately
                body("students.studentId", everyItem(greaterThanOrEqualTo(10663)));}///////////////////////////////////

    // body( gpath, hamcrest matcher () )
        // "students.studentId" --> jsonpath
    @Test
    public void verifyIdsAgain(){
        JsonPath jsonPath = when().get("http://api.cybertektraining.com/student/all").jsonPath();//////////////////////////////
        // "students.studentId" --> jsonpath
        List<Integer> isList = jsonPath.getList("students.studentId");
        System.out.println("isList = " + isList);
        for (Integer id : isList) {
            Assertions.assertTrue(id>=0); }}}
        // body -> finds values based on given jsonpath and verifies based on given match and expected value

