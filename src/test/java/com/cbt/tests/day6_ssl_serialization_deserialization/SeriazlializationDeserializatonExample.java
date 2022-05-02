package com.cbt.tests.day6_ssl_serialization_deserialization;
import com.cbt.pojos.Car;
import com.cbt.pojos.Spartan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SeriazlializationDeserializatonExample {

    @Test
    public void readToMap() throws FileNotFoundException {
        FileReader fileReader = new FileReader("src/test/resources/car.json");//////////////////////////////////
        // convert the file into object that we can use more easily
       // let's create a map from this
       // we do the conversion using Gson or Jackson-databind
        // in out framework we use Gson
        // json --> file type (like xml, pdf, doc)
        // gson --> library used for conversion(serialization and deserialization)
        Gson gson = new Gson();
        // we converted the file into a map // we deserialized file to java object
        Map<String, ?> myCar = gson.fromJson(fileReader, Map.class);///////////////////////////////////////////////////
        System.out.println(myCar);
        System.out.println(myCar.get("doors"));
        System.out.println(myCar.get("make")); }

    @Test
    public void readToObject() throws FileNotFoundException {
        FileReader fileReader = new FileReader("src/test/resources/car.json");
        // deserialize into pojo - from json file to java class
        Gson gson = new Gson();
        Car myCar = gson.fromJson(fileReader, Car.class);//////////////////////////////////////////////////////////////
        System.out.println(myCar.getMake());
        System.out.println(myCar.getDoors()); }

    @Test
    public void writeToJsonFile() throws IOException {
        Car myCar = new Car("Corolla", "2004 one", 4, 98);
        System.out.println(myCar);
        // write to file aka serialize it
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();/////////////////////////////////////////////////////
        FileWriter fileWriter = new FileWriter("src/test/resources/new_car.json");//////////////////////////////
        gson.toJson(myCar, fileWriter);
        fileWriter.flush();
        fileWriter.close(); }

    @Test
    public void readAndWriteItback() throws IOException {
        FileReader reader = new FileReader("src/test/resources/car.json");
        Gson gson = new Gson();
        Car car = gson.fromJson(reader, Car.class);
        System.out.println(car);

        car.setPrice(80);
        System.out.println(car);

        FileWriter writer = new FileWriter("src/test/resources/another_new_car.json");
        gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(car, writer);
        writer.flush();
        writer.close();
        reader.close(); }

    @Test
    public void readAsSpartanObject(){
        RestAssured.baseURI= "http://52.207.61.129:8000/";
        Response response = given().
                auth().basic("admin", "admin").
                pathParam("id", 138).
           when().
                get("/api/spartans/{id}");
        response.prettyPeek();
//        // as() --> de serialize response body into Car type (custom type)
//        Spartan spartan = response.as(Spartan.class);///////////////////////////////////////////////////////////////////
//        System.out.println(spartan);
        // as() --> de serialize response body into map type (not custom type)
        Map<String, ?> spartanMap = response.as(Map.class);/////////////////////////////////////////////////////////////
        System.out.println(spartanMap); }}
