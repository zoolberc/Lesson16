package otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class TestingCoursesPage {

    public TestingCoursesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    WebDriver driver;
    WebDriverWait wait;


    public void waitVisibility(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    public void isElementDisplayed(WebElement webElement){
        waitVisibility(webElement);
        assertTrue(webElement.isDisplayed());
    }
     public  void checkTestingCourse(String courseName){
        WebElement qaTestingCourseLocator = driver.findElement(By.xpath("//*[contains(text(), '"+courseName+"')]"));
        isElementDisplayed(qaTestingCourseLocator);
     }
}
