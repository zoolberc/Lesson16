package otus.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;


public class MainPage {
    public Logger logger = LogManager.getLogger(MainPage.class);


    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    WebDriver driver;
    WebDriverWait wait;

    public void waitVisibility(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void isElementDisplayed(WebElement webElement) {
        waitVisibility(webElement);
        assertTrue(webElement.isDisplayed());
    }
     @FindBy(xpath = "//*[@title='Контакты']")
     private WebElement contactButton;

    public void goToStartPage() {
        driver.get("https://otus.ru/");
        assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", driver.getTitle());
        logger.info("Home page is open");

    }

    public void openAuthPage() {
        WebElement signIn = driver.findElement(By.cssSelector(".header2__auth-reg"));
        isElementDisplayed(signIn);
        signIn.click();
        logger.info("Open authorization page");
    }

    public void openTestingCoursePage(){
        String iconLocator = ".header2-menu__item-wrapper";
        WebElement icon = driver.findElement(By.cssSelector(iconLocator));
        Actions action = new Actions(driver);
        action.moveToElement(icon).build().perform();
        WebElement testing = driver.findElement(By.xpath("//*[contains(text(), 'Тестирование')]"));
        isElementDisplayed(testing);
        testing.click();

    }
    public  void entranceToLK(){
        String iconLocator = ".header2-menu__item_dropdown_no-border";
        WebElement icon = driver.findElement(By.cssSelector(iconLocator));
        Actions action = new Actions(driver);
        action.moveToElement(icon).build().perform();
        driver.findElement(By.cssSelector(".header2-menu__dropdown-text")).click();
        logger.info("Go to my profile");
    }

    public void goToContactPage() {
        isElementDisplayed(contactButton);
        contactButton.click();
    }
}