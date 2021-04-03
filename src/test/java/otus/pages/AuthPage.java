package otus.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.lang.Thread.sleep;

public class AuthPage {
    public Logger logger = LogManager.getLogger(AuthPage.class);

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        basePage = new BasePage(driver);

    }

    WebDriver driver;
    BasePage basePage;


    @FindBy(xpath = "//*[contains(text(), 'Авторизуйтесь чтобы продолжить')]")
    private WebElement checkAuthPage;

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

    @FindBy(css = ".header2-menu__item_dropdown_no-border")
    private WebElement icon;

    @FindBy(xpath = "//*[@placeholder='Электронная почта']")
    private List<WebElement> email;

    private void checkAuthPage() {
        basePage.checkElement(checkAuthPage);
        logger.debug("Checking whether it is on the authorization pages");
    }

    private void fillEmail(String text) {
        basePage.checkElement(login);
        basePage.clearAndSendKeys(login, text);
        logger.debug("Filling in the E-mail field");
    }

    private void fillPassword(String text) {
        basePage.checkElement(password);
        basePage.clearAndSendKeys(password, text);
        logger.debug("Filling in the password field");
    }

    private void submitAuth() {
        submit.click();
    }

    public void checkSignIn(String firstName, String lastName) {
        Actions action = new Actions(driver);
        action.moveToElement(icon).build().perform();
        driver.findElement(By.xpath("//*[contains(text(),'" + firstName + " " + lastName + "')]"));
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

    public void checkTextIncorrectLoginOrPassword() {
        basePage.checkElement(incorrectLoginOrPassword);
    }

    public void forgotPassword(String myEmail) throws InterruptedException {
        sleep(1000);
        basePage.checkElement(forgotPasswordButton);
        forgotPasswordButton.click();
        basePage.checkElement(labelPasswordRecovery);
        basePage.checkElement(email.get(3));
        basePage.clearAndSendKeys(email.get(3), myEmail);
        recoveryButton.click();

    }

    public void checkSendMail(String myEmail) {
        WebElement successSendMail = driver.findElement(By.xpath("//*[contains(text(),'Письмо с ссылкой для восстановление пароля отправлено на почту " + myEmail + "')]"));
        basePage.checkElement(successSendMail);
    }


}
