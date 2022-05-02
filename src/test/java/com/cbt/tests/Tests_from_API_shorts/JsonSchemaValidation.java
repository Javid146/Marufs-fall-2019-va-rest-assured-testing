package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidation {
/*1. WE ADD SCHEMA VALIDATOR IN POM.XML
2. WE TAKE JSON BODY LIKE BELOW :
{   "id": 44,
    "name": "javid",
    "gender": "Male",
    "phone": 231312323}
3. CONVERT IT INTO JSON SCHEMA IN https://jsonschema.net/home
4. VERIFY SCHEMA IN https://www.jsonschemavalidator.net/
5. WE CREATE spartan-schema.json FILE IN resources folder AND ADD JSON SCHEMA THERE.
* THEN WE GET ANY JSON BODY (ANY SPARTAN) FROM SPARTAN WEBSITE AND COMPARE WITH (matchesJsonSchemaInClasspath) ITS STRUCTURE TO
* JSON SCHEMA IN spartan-schema.json. IF IT MATCHES, TEST WILL PASS*/
    @BeforeClass
    public void setup(){
        RestAssured.baseURI= "http://52.207.61.129:8000";
    }
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when().get("api/spartans/{id}").then()
                //COMPARE JSON BODY TO JSON SCHEMA IN spartan-schema.json
        .assertThat().body(matchesJsonSchemaInClasspath("spartan-schema.json"));
    }
}
