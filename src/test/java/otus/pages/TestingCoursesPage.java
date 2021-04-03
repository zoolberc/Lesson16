package otus.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestingCoursesPage {
    public Logger logger = LogManager.getLogger(TestingCoursesPage.class);

    public TestingCoursesPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void checkTestingCourse(String courseName) {
        WebElement qaTestingCourseLocator = driver.findElement(By.xpath("//*[contains(text(), '" + courseName + "')]"));
        qaTestingCourseLocator.isDisplayed();
        logger.info(courseName + "is displayed");
    }
}
