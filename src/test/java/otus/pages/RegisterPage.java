package otus.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegisterPage {
    public Logger logger = LogManager.getLogger(RegisterPage.class);

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        basePage = new BasePage(driver);
    }

    WebDriver driver;
    BasePage basePage;

    @FindBy(xpath = "//*[@data-tab-id='register']")
    private WebElement registrationButton;

    @FindBy(xpath = "//*[@placeholder='Электронная почта *']")
    private WebElement registrationEmail;

    @FindBy(xpath = "//*[@name='fname'")
    private WebElement fname;

    @FindBy(xpath = "//*[contains(text(),'Зарегистрироваться')]")
    private WebElement submit;

    @FindBy(xpath = "//*[@name='fname']")
    private List<WebElement> registrationFirstName;

    @FindBy(xpath = "//*[@name='lname']")
    private List<WebElement> registrationLastName;

    @FindBy(css = ".header2-menu__item_dropdown_no-border")
    private WebElement iconLocator;

    public void registrationButton() {
        basePage.waitVisibility(registrationButton);
        registrationButton.click();
    }

    public void checkRegistration(String firstName, String lastName) {
        Actions action = new Actions(driver);
        action.moveToElement(iconLocator).build().perform();
        driver.findElement(By.xpath("//*[contains(text(),'" + firstName + " " + lastName + "')]"));
        logger.debug("Checking the login to my profile");
        logger.info("Registration passed");
    }

    public void fillRegistrationField(String firstName, String lastName, String email) {
        basePage.checkElement(registrationFirstName.get(1));
        basePage.clearAndSendKeys(registrationFirstName.get(1), firstName);
        basePage.checkElement(registrationLastName.get(1));
        basePage.clearAndSendKeys(registrationLastName.get(1), lastName);
        basePage.checkElement(registrationEmail);
        basePage.clearAndSendKeys(registrationEmail, email);
        basePage.checkElement(submit);
        submit.click();

    }
}
