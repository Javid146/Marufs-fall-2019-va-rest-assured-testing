package com.cbt.tests.day10_log4j;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

public class Log4JExamples {
    private static final Logger logger = LogManager.getLogger(Log4JExamples.class);

    @Test
    public void testLogger() {
        System.out.println("wooden spoon");
        logger.info("wooden spoon");
        logger.info("hello world"); }

    @Test
    public void anotherOne() {
        logger.setLevel(Level.TRACE);
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal"); }

    // logger object --> captures the messages and passes to appender for each output type
    // appender -->  specific to each output type. captures messages and passes to layout
    // layout --> writes the message based on given layout format

    @Test
    public void testThatUsesThatLoggerThing() {
        logger.info("making api call");

        Response response = RestAssured.
                when().get("http://api.exchangeratesapi.io/v1/latest?access_key=273c015b35128643a62a0ceabe06073a");
        logger.info("done making api call");
        logger.debug(response.asString());
        logger.info("verifying status code");
        logger.debug(response.statusCode());
        response.then().statusCode(200);
        logger.info("test passed"); }}
