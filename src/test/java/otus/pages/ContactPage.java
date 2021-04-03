package otus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ContactPage {
    public ContactPage (WebDriver driver) {
        this.driver = driver;
    }
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"__next\"]/div[3]/div/div[3]/div[1]/div[3]/div[2]/a")
    private WebElement contact;

    public void checkContact(String phone){
        assertEquals(phone, contact.getText());
    }
}
