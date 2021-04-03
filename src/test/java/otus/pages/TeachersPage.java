package otus.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class TeachersPage {
    public Logger logger = LogManager.getLogger(TeachersPage.class);

    public TeachersPage(WebDriver driver) {
        this.driver = driver;
        basePage = new BasePage(driver);
    }

    WebDriver driver;
    BasePage basePage;

    @FindBy(xpath = "//*[@data-modal-id='teachers-request']")
    private WebElement teachersButton;

    @FindBy(xpath = "//*[@id='formPlan']/div[1]/div[1]")
    private WebElement labelBecomeTeacher;

    @FindBy(xpath = "//*[@autocomplete='fullname']")
    private WebElement fullName;

    @FindBy(xpath = "//*[@autocomplete='phone']")
    private WebElement phone;

    @FindBy(xpath = "//*[@autocomplete='email']")
    private WebElement email;

    @FindBy(xpath = "//*[@id='formPlan']/div[1]/div[7]/button")
    private WebElement buttonBecomeTeacher;

    @FindBy(xpath = "//*[contains(text(), 'Спасибо!')]")
    private WebElement labelSuccessSend;



    public void openPage() {
        driver.get("https://otus.ru/teachers-invite/");
        logger.info("Teachers page is open");
    }

    public void filingForm() {
        basePage.checkElement(teachersButton);
        teachersButton.click();
        basePage.checkElement(labelBecomeTeacher);
        basePage.clearAndSendKeys(fullName, "Иванов Иван Иваныч");
        basePage.clearAndSendKeys(phone, "79999999999");
        basePage.clearAndSendKeys(email, "test@test.com");
        buttonBecomeTeacher.click();
        logger.info("The form is completed");
    }

    public void checkSuccessRequest() {
        basePage.checkElement(labelSuccessSend);
        logger.info("Application has been successfully sent");
    }

}
