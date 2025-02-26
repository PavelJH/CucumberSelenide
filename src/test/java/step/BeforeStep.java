package step;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;

public class BeforeStep {

    @Given("Open webSite {string}")
    public void openWebSite(String url) {
        Configuration.timeout = 60000;
//        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Selenide.open(url);
    }
}
