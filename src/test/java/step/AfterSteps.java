package step;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;

public class AfterSteps {
    @After
    public void tearDown(){
        WebDriverRunner.getWebDriver().close();
        WebDriverRunner.getWebDriver().quit();
    }

//    @AfterStep
//    public void makeScreenshot(){
//        Selenide.screenshot(System.currentTimeMillis() + "step");
//    }

}
