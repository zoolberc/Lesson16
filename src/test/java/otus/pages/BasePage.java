package otus.pages;

import org.openqa.selenium.support.ui.WebDriverWait;

import static otus.driver.DriverManager.driver;

public class BasePage {
    public WebDriverWait wait;

    public BasePage() {
        this.wait = new WebDriverWait(driver, 15);
    }


}
