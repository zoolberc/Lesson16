package otus.steps;

import io.cucumber.java.ru.Пусть;
import otus.pages.BasePage;
import otus.pages.MainPage;

public class MyStepdefs extends BasePage {
    MainPage mainPage = new MainPage();

    @Пусть("^Перейти на главную страницу$")
    public void openStartPage() {
        mainPage.goToStartPage();
    }
}
