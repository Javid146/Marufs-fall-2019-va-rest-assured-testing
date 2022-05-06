package com.cbt.utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.extension.*;

/*THIS CLASS SHOWS THE WAY OF CREATING REPORT VIA USING JUNIT5. So we implement 4 INTERFACES as below.
* and Override them. These interfaces are like @BeforeSuite, @BeforeClass annotations of TestNG, but they are from JUNIT5.
* They will trigger and compile report after testing is done.
* todo In order to use this reporting all we need to do is to add @ExtendWith(ExtentConfig.class), on top of the class we want to make report from.*/



public class ExtentConfig implements BeforeAllCallback, BeforeTestExecutionCallback, AfterAllCallback, AfterTestExecutionCallback{
    static ExtentReports reports;
    public static ExtentTest test;

    // todo before each class (BeforeAllCallback)
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        // this line create a report under the test-output folder with the name of the class
        String filename = System.getProperty("user.dir") + "/test-output/" + context.getDisplayName() + "_Results.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter); }

    // todo before each test in that class (BeforeTestExecutionCallback)
    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        test = reports.createTest(context.getDisplayName());
        test.log(Status.INFO, context.getDisplayName() + " - started"); }

    // todo after each test in that class (AfterTestExecutionCallback)
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (!context.getExecutionException().isPresent()) {
            test.pass(context.getDisplayName() + " - passed");
        } else if (context.getExecutionException().get().toString().contains("AssertionError")) {
            test.fail(context.getExecutionException().get().toString());
            test.fail(context.getExecutionException().get().getCause());
        } else {
            test.fatal(context.getExecutionException().get().toString());
            test.fatal(context.getExecutionException().get().getCause()); }}

    // todo after each class (AfterAllCallback)
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        reports.flush();
    }
}
