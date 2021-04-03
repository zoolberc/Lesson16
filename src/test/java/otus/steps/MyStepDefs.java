package otus.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import otus.configs.VariableConfig;
import otus.driver.DriverManager;
import otus.driver.DriverType;

public class MyStepDefs extends DriverManager {

    public static VariableConfig config = ConfigFactory.create(VariableConfig.class);
    String firstNameRandom = RandomStringUtils.random(10, true, false);
    String lastNameRandom = RandomStringUtils.random(10, true, false);
    String emailNameRandom = RandomStringUtils.random(10, true, false);

    @Before
    public void start() {
        setupDriver(DriverType.CHROME);
    }

    @After
    public void stop() {
        quitDriver();
    }

    @Given("Перейти на главную страницу")
    public void openStartPage() {
        mainPage.goToStartPage();
    }

    @When("Ввести данные логин и пароль")
    public void inputLogAndPass() {
        authPage.login(config.emailOtus(), config.passwordOtus());

    }

    @And("Открыть страницу авторизации")
    public void openAuthPage() {
        mainPage.openAuthPage();
    }

    @Then("Проверить, что пользователь авторизирован")
    public void checkAuth() {
        authPage.checkSignIn("Турал", "Алиев");
    }

    @When("Ввести некорректный логин и пароль")
    public void inputIncorrectPassword() {
        authPage.login(config.emailOtus(), "1234");
    }

    @Then("Проверить, что появилась надпись о неккоррктном логине или пароле")
    public void checkTextIncorrectLoginOrPassword() {
        authPage.checkTextIncorrectLoginOrPassword();
    }

    @And("Открыть страницу регистрации")
    public void openRegisterPage() {
        mainPage.openAuthPage();
        registerPage.registrationButton();
    }

    @When("Заполнить поля регистрации")
    public void fillRegistrationData() {
        registerPage.fillRegistrationField(firstNameRandom, lastNameRandom, emailNameRandom + "@gmail.com");
    }

    @Then("Проветь, что пользователь успешно зарегистрировался")
    public void checkUserRegistration() {
        registerPage.checkRegistration(firstNameRandom, lastNameRandom);
    }

    @When("Восстановить пароль с помощью email")
    public void passwordRecovery() throws InterruptedException {
        authPage.forgotPassword(config.emailOtus());
    }

    @Then("Проверить, что письмо было успешно отправлено")
    public void checkSuccessMailSend() {
        authPage.checkSendMail(config.emailOtus());
    }

    @When("Открыть куры по тестированию")
    public void openTestingCourses() {
        mainPage.openTestingCoursePage();
    }

    @Then("Проверить, что курс отображается на странице")
    public void checkVisibleCourse() throws InterruptedException {
        testingCoursesPage.checkTestingCourse("Java QA Engineer");
    }

    @When("Перейти на страницу с данными профиля")
    public void goToMyProfile() {
        mainPage.entranceToLK();
    }

    @And("Изменить имя и фамилию, сохранить")
    public void changeLastAndFirstName() {
        profilePage.fillLastName(lastNameRandom);
        profilePage.fillFirstName(firstNameRandom);
        profilePage.save();
    }

    @Then("Проверить, что имя и фамилия изменились")
    public void checkingChanges() {
        profilePage.check(firstNameRandom, lastNameRandom);
    }

    @And("Добавть дополнительные каналы связи и сохранить")
    public void addingСommunicationСhannels() {
        profilePage.addFirstContact(config.vkContact());
        profilePage.addSecondContact(config.skypeContact());
        profilePage.save();
    }

    @Then("Проверить, что данные были сохранены")
    public void checkingDataStorage() {
        profilePage.checkingContactStorage();
        profilePage.deleteAdditionalContacts();
    }

    @Given("Перейти на страницу отправки заявки")
    public void goToTeachersTab() {
        teachersPage.openPage();
    }

    @When("Заполнить и отправить заявку")
    public void fillingForm() {
        teachersPage.filingForm();
    }

    @Then("Проверить, что завяка была отправлена")
    public void checkSuccessRequest() {
        teachersPage.checkSuccessRequest();
    }

    @Then("Сменить фото профиля")
    public void change() {
        profilePage.uploadImage();
        profilePage.save();
    }

    @When("Перейти в раздел Контакты")
    public void goToContactPage() {
        mainPage.goToContactPage();
    }

    @Then("Проверить проверить, что номер телефона {string}")
    public void checkNumberOnPage(String arg1) {
        contactPage.checkContact(arg1);

    }
}
