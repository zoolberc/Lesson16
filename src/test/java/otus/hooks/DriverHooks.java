package otus.hooks;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import otus.driver.DriverManager;


public class DriverHooks extends DriverManager {

    @Before
    public void start() {
        setupDriver();
    }

    @After
    public void stop() {
        quitDriver();
    }
}
