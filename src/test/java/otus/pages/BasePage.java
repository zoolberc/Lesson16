package otus.pages;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otus.configs.VariableConfig;
import otus.hooks.DriverHooks;

import static org.junit.Assert.assertTrue;

public class BasePage {

    public BasePage(WebElement driver) {
        this.driver = driver;
    }
    WebElement driver;

    //Wait Wrapper Method
//    public void waitVisibility(WebElement webElement){
//        ;
//        wait.until(ExpectedConditions.visibilityOf(webElement))
//    }
//
//

    //Is element displayed


}
