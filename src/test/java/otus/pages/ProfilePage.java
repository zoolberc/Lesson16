package otus.pages;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otus.configs.VariableConfig;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfilePage {
    public static VariableConfig config = ConfigFactory.create(VariableConfig.class);
    public Logger logger = LogManager.getLogger(ProfilePage.class);

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "#id_fname_latin")
    private WebElement firstNameLatin;

    @FindBy(css = "#id_lname_latin")
    private WebElement lastNameLatin;

    @FindBy(css = "button.button_md-4:nth-child(2)")
    private WebElement saveButton;

    @FindBy(id = "id_contact-0-value")
    private WebElement firstContact;

    @FindBy(id = "id_contact-1-value")
    private WebElement secondContact;

    @FindBy(xpath = "//*[@type='file']")
    private WebElement uploadAvatar;

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

    public void fillFirstName(String text) {
        isElementDisplayed(firstNameLatin);
        clearAndSendKeys(firstNameLatin, text);
        logger.debug("Filing in the latin first name field");
    }
    public void fillLastName(String text) {
        isElementDisplayed(lastNameLatin);
        clearAndSendKeys(lastNameLatin, text);
        logger.debug("Filing in the lastin last name field");
    }
    public void save() {
        isElementDisplayed(saveButton);
        saveButton.click();
        driver.findElement(By.xpath("//*[@title = 'Редактировать резюме']")).isDisplayed(); // проверка что данные сораннены
        logger.debug("Checking data storage");
        logger.info("Data change successfully");
    }
    public void check(String latinFirstName, String latinLastName){
        driver.findElement(By.xpath("//*[@title = 'Редактировать резюме']")).click();
        assertEquals("Персональные данные", driver.findElement(By.cssSelector("h3.text")).getText());
        assertEquals(latinFirstName, firstNameLatin.getAttribute("value"));
        assertEquals(latinLastName, lastNameLatin.getAttribute("value"));
    }
    public void addFirstContact(String text) {
        driver.findElement(By.xpath("//*[@class ='placeholder']//..")).click();
        driver.findElement(By.xpath("//*[@title = 'VK']")).click(); // выбор способа связи ВК
        isElementDisplayed(firstContact);
        firstContact.sendKeys(text);
        logger.debug("Adding an additional contact");
    }
    public void addSecondContact(String text) {
        driver.findElement(By.xpath("//*[contains(@class,'js-lk-cv-custom-select-add')]")).click();
        driver.findElements(By.xpath("//*[@class ='placeholder']//..")).get(0).click();
        driver.findElements(By.xpath("//*[@title = 'Skype']")).get(1).click();
        isElementDisplayed(secondContact);
        secondContact.sendKeys(text);
        logger.debug("Adding an additional contact");
    }

    public void checkingContactStorage(){
        WebElement edit = driver.findElement(By.xpath("//*[@title = 'Редактировать резюме']"));
        isElementDisplayed(edit);
        edit.click();
        assertEquals("Персональные данные", driver.findElement(By.cssSelector("h3.text")).getText());
        assertEquals(config.skypeContact(), firstContact.getAttribute("value"));
        assertEquals(config.vkContact(), secondContact.getAttribute("value"));
    }
    //удаление контактов
    public void deleteAdditionalContacts() {
        List<WebElement> conts = driver.findElements(By.xpath("//*[contains(text(), 'Удалить')]"));
        conts.get(1).click();
        conts.get(3).click();
        driver.findElement(By.cssSelector("button.button_md-4:nth-child(2)")).click(); // сохранение данных
        driver.findElement(By.xpath("//*[@title = 'Редактировать резюме']")).isDisplayed(); // проверка что данные сораннены
    }

    public void uploadImage(){
        File file = new File("src/test/resources/images/img.png");
        //isElementDisplayed(uploadAvatar);
        uploadAvatar.sendKeys(file.getAbsolutePath());
        WebElement select = driver.findElement(By.xpath("//*[contains(text(), 'Выбрать')]"));
        isElementDisplayed(select);
        select.click();
        assertEquals("Персональные данные", driver.findElement(By.cssSelector("h3.text")).getText());
    }

}
