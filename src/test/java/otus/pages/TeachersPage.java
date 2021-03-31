package otus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class TeachersPage {
    public TeachersPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    WebDriver driver;
    WebDriverWait wait;

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

    public void waitVisibility(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void isElementDisplayed(WebElement webElement) {
        waitVisibility(webElement);
        assertTrue(webElement.isDisplayed());
    }

    public void clearAndSendKeys(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void openPage() {
        driver.get("https://otus.ru/teachers-invite/");
    }

    public void filingForm() {
        isElementDisplayed(teachersButton);
        teachersButton.click();
        isElementDisplayed(labelBecomeTeacher);
        clearAndSendKeys(fullName, "Иванов Иван Иваныч");
        clearAndSendKeys(phone, "79999999999");
        clearAndSendKeys(email,"test@test.com");
        buttonBecomeTeacher.click();
    }

    public void  checkSuccessRequest(){
        isElementDisplayed(labelSuccessSend);
    }

}
