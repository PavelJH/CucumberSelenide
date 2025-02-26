package step;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.en.Given;
import io.qameta.allure.selenide.AllureSelenide;

public class BeforeStep {

    @Given("Open webSite {string}")
    public void openWebSite(String url) {
        Configuration.timeout = 60000;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.headless = true;
        Selenide.open(url);
    }
}
