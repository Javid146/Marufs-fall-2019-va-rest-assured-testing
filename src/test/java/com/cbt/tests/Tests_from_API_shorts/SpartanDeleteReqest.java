package com.cbt.tests.Tests_from_API_shorts;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanDeleteReqest {

    @BeforeClass
    public void setup() {
        baseURI = "http://52.207.61.129:8000/";
    }

    @Test
    public void putRequest() {
        //ways to send json body: 1.String, 2.collection (Map), 3.POJO

        //we send delete request to json body
        given().contentType(ContentType.JSON)
                .pathParam("id", 28)
                .when().delete("api/spartans/{id}").prettyPeek()
                .then().assertThat().statusCode(204);
        //we verify whether deleted spartan will be shown.
        given().pathParam("id", 130)
        .when().get("api/spartans/{id}")
                .then().statusCode(404);//if data is deleted it will show 404

        //SECOND TIME IF I RUN THIS TEST IT WILL SHOW ERROR. BECAUSE I ALREADY DELETED SPARTAN
        //AND IN ORDER TO RUN IT CORRECTLY AGAIN WE NEED TO WRITE ID OF ANOTHER SPARTAN AND DELETE THAT
    }
}
