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
        basePage = new BasePage(driver);
    }

    WebDriver driver;
    BasePage basePage;

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

    @FindBy(xpath = "//*[@title = 'Редактировать резюме']")
    private WebElement titleEditResume;

    @FindBy(css = "h3.text")
    private WebElement titlePersonalData;

    @FindBy(xpath = "//*[@title = 'VK']")
    private WebElement titleVK;

    @FindBy(xpath = "//*[@title = 'Skype']")
    private List<WebElement> titleSkype;

    @FindBy(xpath = "//*[contains(@class,'js-lk-cv-custom-select-add')]")
    private WebElement addSecContact;

    @FindBy(xpath = "//*[contains(text(), 'Выбрать')]")
    private List<WebElement> selectButton;



    public void fillFirstName(String text) {
        basePage.checkElement(firstNameLatin);
        basePage.clearAndSendKeys(firstNameLatin, text);
        logger.debug("Filing in the latin first name field");
    }

    public void fillLastName(String text) {
        basePage.checkElement(lastNameLatin);
        basePage.clearAndSendKeys(lastNameLatin, text);
        logger.debug("Filing in the lastin last name field");
    }

    public void save() {
        basePage.checkElement(saveButton);
        saveButton.click();
        titleEditResume.isDisplayed();
        logger.debug("Checking data storage");
        logger.info("Data change successfully");
    }

    public void check(String latinFirstName, String latinLastName) {
        titleEditResume.click();
        assertEquals("Персональные данные", titlePersonalData.getText());
        assertEquals(latinFirstName, firstNameLatin.getAttribute("value"));
        assertEquals(latinLastName, lastNameLatin.getAttribute("value"));
    }

    public void addFirstContact(String text) {
        driver.findElement(By.xpath("//*[@class ='placeholder']//..")).click();
        titleVK.click();
        basePage.checkElement(firstContact);
        firstContact.sendKeys(text);
        logger.debug("Adding an additional contact");
    }

    public void addSecondContact(String text) {
        addSecContact.click();
        driver.findElements(By.xpath("//*[@class ='placeholder']//..")).get(0).click();
        titleSkype.get(1).click();
        basePage.checkElement(secondContact);
        secondContact.sendKeys(text);
        logger.debug("Adding an additional contact");
    }

    public void checkingContactStorage() {
        basePage.checkElement(titleEditResume);
        titleEditResume.click();
        assertEquals("Персональные данные", titlePersonalData.getText());
        assertEquals(config.skypeContact(), firstContact.getAttribute("value"));
        assertEquals(config.vkContact(), secondContact.getAttribute("value"));
    }

    //удаление контактов
    public void deleteAdditionalContacts() {
        List<WebElement> conts = driver.findElements(By.xpath("//*[contains(text(), 'Удалить')]"));
        conts.get(1).click();
        conts.get(3).click();
        saveButton.click();
        titleEditResume.isDisplayed();
    }

    public void uploadImage() {
        File file = new File("src/test/resources/images/img.png");
        uploadAvatar.sendKeys(file.getAbsolutePath());
        basePage.checkElement(selectButton.get(1));
        selectButton.get(1).click();
        assertEquals("Персональные данные", titlePersonalData.getText());
    }

}
