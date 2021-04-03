package otus.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;
import otus.pages.*;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    public WebDriver driver;
    protected MainPage mainPage;
    protected AuthPage authPage;
    protected RegisterPage registerPage;
    protected TestingCoursesPage testingCoursesPage;
    protected ProfilePage profilePage;
    protected TeachersPage teachersPage;
    protected ContactPage contactPage;

    public Logger logger = LogManager.getLogger(DriverManager.class);

    public void setupDriver(DriverType typeDriver) {

        switch (typeDriver) {
            case OPERA:
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        logger.info("Browser driver open");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        authPage = PageFactory.initElements(driver, AuthPage.class);
        registerPage = PageFactory.initElements(driver, RegisterPage.class);
        testingCoursesPage = PageFactory.initElements(driver, TestingCoursesPage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
        teachersPage = PageFactory.initElements(driver, TeachersPage.class);
        contactPage = PageFactory.initElements(driver, ContactPage.class);
    }

    public void quitDriver() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            logger.info("Browser driver closed");

        }
    }
}
