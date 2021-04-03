package otus.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class TestingCoursesPage {
    public Logger logger = LogManager.getLogger(TestingCoursesPage.class);
    public TestingCoursesPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

     public  void checkTestingCourse(String courseName) {
        WebElement qaTestingCourseLocator = driver.findElement(By.xpath("//*[contains(text(), '"+courseName+"')]"));
        qaTestingCourseLocator.isDisplayed();
        logger.info(courseName + "is displayed");
     }
}
