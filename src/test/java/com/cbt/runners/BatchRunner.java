package com.cbt.runners;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/*// we need extentreports, junit-jupiter-engine, junit-platform-runner dependencies to run tests with JUNIT
//if you want to run tests with MAVEN add maven-surefire dependency and add name of this class in pom file, inside of that dependency like below:
                               /*git <plugin>
                                <groupId>org.apache.maven.plugins</groupId>
                             <artifactId>maven-surefire-plugin</artifactId>
                                <version>3.0.0-M4</version>
                                <configuration>
                                    <includes>
                                        <include>**///RegressionRunner.java</include>
                    //                    </includes>
                  //                      <testFailureIgnore>false</testFailureIgnore>
                 //                       </configuration>
             //                           </plugin>


@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Automated api tests") //shows the name of suite (test in console) like that
@SelectPackages("com.cbt.tests.day11_cookie_and_extent_report") //paste path of package you want to run
//@SelectClasses() //todo you select separate classes to run to. But make sure classname has the word test in it, otherwise will not run.
public class BatchRunner {
}


// @RunWith(JUnitPlatform.class) --> create a batch runner. It means we can use several packages or classes together.
/*@RunWith(JUnitPlatform.class) is from JUNIT4 AND 5. As they don't have annotations like @BeforeAll, @AfterAll like TestNG
* we need to do like that*/