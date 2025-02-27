package step;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AfterSteps {

    // Путь к папке, где сохраняются скриншоты (по умолчанию Selenide сохраняет их в указанной папке)
    private static final String SCREENSHOT_DIR = "build/reports/tests";

    @After
    public void tearDown(){
        WebDriverRunner.getWebDriver().close();
        WebDriverRunner.getWebDriver().quit();
    }

    @AfterStep
    public void makeScreenshot(Scenario scenario){
        if (scenario.isFailed()){
            // Создаем папку для скриншотов, если её нет
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Проверяем количество скриншотов и удаляем самый старый, если их 10 или больше
            File[] screenshots = screenshotDir.listFiles((dir, name) -> name.startsWith("failedStep_") && name.endsWith(".png"));
            if (screenshots != null && screenshots.length >= 10) {
                File oldest = screenshots[0];
                for (File file : screenshots) {
                    if (file.lastModified() < oldest.lastModified()){
                        oldest = file;
                    }
                }
                oldest.delete();
            }

            // Форматирование текущей даты и времени для имени файла
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String failedStep = "failedStep"; // Здесь можно заменить на текст шага, если он доступен
            String fileName = failedStep + "_" + timestamp;

            // Создаем скриншот и получаем путь к файлу
            String screenshotPath = Selenide.screenshot(fileName);

            // Прикрепляем скриншот к Allure-отчету
            try (InputStream is = new FileInputStream(new File(screenshotPath))) {
                Allure.addAttachment("Screenshot on failure", "image/png", is, "png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
