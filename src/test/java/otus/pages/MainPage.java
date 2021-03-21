package otus.pages;

import static otus.driver.DriverManager.driver;


public class MainPage extends BasePage {


    public MainPage() {
        super();
    }

    public void goToStartPage(){
        driver.get("https://otus.ru/");
    }
}