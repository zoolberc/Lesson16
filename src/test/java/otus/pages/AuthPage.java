package otus.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class AuthPage{
    public Logger logger = LogManager.getLogger(AuthPage.class);

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
        }

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[contains(text(), 'Авторизуйтесь чтобы продолжить')]")
    private WebElement checkAuthpage;

    @FindBy(css = "div.new-input-line_slim:nth-child(3) > input:nth-child(1)")
    private WebElement login;

    @FindBy(xpath = "//*[contains(text(), 'Такая пара логин/пароль не существует')]")
    private WebElement incorrectLoginOrPassword;

    @FindBy(css = ".js-psw-input")
    private WebElement password;

    @FindBy(xpath = "//*[contains(text(),'Войти')]")
    private WebElement submit;

    @FindBy(xpath = "//*[@title='Забыли пароль?']")
    private WebElement forgotPasswordButton;

    @FindBy(xpath = "//*[contains(text(), 'Восстановление пароля')]")
    private WebElement labelPasswordRecovery;

    @FindBy(xpath = "//*[contains(text(), 'Восстановить')]")
    private WebElement recoveryButton;


    public void waitVisibility(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    public void isElementDisplayed(WebElement webElement){
        waitVisibility(webElement);
        assertTrue(webElement.isDisplayed());
    }
    private void checkAuthPage() {
        isElementDisplayed(checkAuthpage);
        logger.debug("Checking whether it is on the authorization pages");
    }

    public void clearAndSendKeys(WebElement webElement, String text){
        webElement.clear();
        webElement.sendKeys(text);
    }

    private void fillEmail(String text) {
        isElementDisplayed(login);
        clearAndSendKeys(login, text);
        logger.debug("Filling in the E-mail field");
    }

    private void fillPassword(String text) {
        isElementDisplayed(password);
        clearAndSendKeys(password, text);
        logger.debug("Filling in the password field");
    }

    private void submitAuth() {
        submit.click();
    }

    public void checkSignIn(String firstName, String lastName) {
        String iconLocator = ".header2-menu__item_dropdown_no-border";
        WebElement icon = driver.findElement(By.cssSelector(iconLocator));
        Actions action = new Actions(driver);
        action.moveToElement(icon).build().perform();
        driver.findElement(By.xpath("//*[contains(text(),'"+firstName+" "+lastName+"')]"));
        logger.debug("Checking the login to my profile");
        logger.info("Authorization passed");
    }

    public void login(String email, String password) {
        checkAuthPage();
        fillEmail(email);
        fillPassword(password);
        submitAuth();
        logger.info("Authorization passed");
    }

    public void checkTextIncorrectLoginOrPassword(){
        isElementDisplayed(incorrectLoginOrPassword);
    }

    public void forgotPassword(String myemail) throws InterruptedException {
        sleep(5000);
        isElementDisplayed(forgotPasswordButton);
        forgotPasswordButton.click();
        isElementDisplayed(labelPasswordRecovery);
        WebElement email = driver.findElements(By.xpath("//*[@placeholder='Электронная почта']")).get(3);
        isElementDisplayed(email);
        clearAndSendKeys(email,myemail);
        recoveryButton.click();

    }
    public void checkSendMail(String myemail){
        WebElement successSendMail = driver.findElement(By.xpath("//*[contains(text(),'Письмо с ссылкой для восстановление пароля отправлено на почту "+myemail+"')]"));
        isElementDisplayed(successSendMail);
    }


}
