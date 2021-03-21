package otus.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    public static WebDriver driver;
    private static String browser;
    public static Logger logger = LogManager.getLogger(DriverManager.class);

    public static void setupDriver() {
        if (System.getProperty("Browser") == null) {
            browser = "";
        } else {
            browser = System.getProperty("Browser").toLowerCase();
        }
        switch (browser) {
            case ("\'opera\'"):
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;

            case ("\'firefox\'"):
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        logger.info("Browser driver open");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    public void quitDriver(){
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            logger.info("Browser driver closed");
        }
    }
}
