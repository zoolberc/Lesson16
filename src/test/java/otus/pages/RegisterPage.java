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

import static org.junit.Assert.assertTrue;

public class RegisterPage {
   public Logger logger = LogManager.getLogger(RegisterPage.class);

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    WebDriver driver;
    WebDriverWait wait;
    WebElement registrationFirstName;
    WebElement registrationLastName;

    @FindBy(xpath = "//*[@data-tab-id='register']")
    private WebElement registrationButton;

    @FindBy(xpath = "//*[@placeholder='Электронная почта *']")
    private WebElement registrationEmail;

    @FindBy(xpath = "//*[@name='fname'")
    private WebElement fname;

    @FindBy(xpath = "//*[contains(text(),'Зарегистрироваться')]")
    private WebElement submit;


    public void waitVisibility(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void isElementDisplayed(WebElement webElement) {
        waitVisibility(webElement);
        assertTrue(webElement.isDisplayed());
    }
    public void clearAndSendKeys(WebElement webElement, String text){
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void registrationButton(){
        waitVisibility(registrationButton);
        registrationButton.click();
    }
    public void checkRegistration(String firstName, String lastName) {
        String iconLocator = ".header2-menu__item_dropdown_no-border";
        WebElement icon = driver.findElement(By.cssSelector(iconLocator));
        Actions action = new Actions(driver);
        action.moveToElement(icon).build().perform();
        driver.findElement(By.xpath("//*[contains(text(),'"+firstName+" "+lastName+"')]"));
        logger.debug("Checking the login to my profile");
        logger.info("Registration passed");
    }

    public void fillRegistrationField(String firstName, String lastName, String email){
        registrationFirstName  = driver.findElements(By.xpath("//*[@name='fname']")).get(1);
        registrationLastName = driver.findElements(By.xpath("//*[@name='lname']")).get(1);
        isElementDisplayed(registrationFirstName);
        clearAndSendKeys(registrationFirstName, firstName);
        isElementDisplayed(registrationLastName);
        clearAndSendKeys(registrationLastName, lastName);
        isElementDisplayed(registrationEmail);
        clearAndSendKeys(registrationEmail,email);
        isElementDisplayed(submit);
        submit.click();

    }
}
