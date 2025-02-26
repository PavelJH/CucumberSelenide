package step;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;


public class KlavaGonkiStep {

    private final SelenideElement closeWindowButton = $x("//input[@value='Закрыть']");
    private final SelenideElement startGameButton = $x("//a[@id='host_start']");
    private final SelenideElement highlightWord = $x("//span[@id='typefocus']");
    private final SelenideElement inputField = $x("//input[@id='inputtext']");
    private final SelenideElement afterFocusWord = $x("//span[@id='afterfocus']");
    private final SelenideElement resultText = $x("//td[text()='Это вы']//ancestor-or-self::div//div[@class='stats']//div[2]/span/span");

    private String getCurrentWord(){
       return highlightWord.getText().replaceAll("c", "с").replaceAll("o", "о");
    }


    @When("Begin game")
    public void startGame() {
        closeWindowButton.click();
        if (startGameButton.isDisplayed()){
            startGameButton.click();
        }
    }
    @And("Wait start game")
    public void waiterGame(){
        highlightWord.click();
    }

    @And("Put highlight word in a cycle")
    public void playGame() {
        while (true){
            String currentWord = getCurrentWord();
            String afterFocusSymbol = afterFocusWord.getText();
            
            inputField.sendKeys(currentWord);
            if (afterFocusSymbol.equals(".")){
                inputField.sendKeys(".");
                break;
            }
            inputField.sendKeys(Keys.SPACE);
        }
    }

    @Then("Fixing game is over and symbols in min more then {int}")
    public void endGameAndCheckSymbols(int minValue) {
        String result = resultText.getText();
        int resultNumber = Integer.parseInt(result);
        System.out.println("Its how many symbol was typed, min: " + resultNumber);
        Assertions.assertTrue(resultNumber > minValue, "Actual result was: " + resultNumber);
    }
}
