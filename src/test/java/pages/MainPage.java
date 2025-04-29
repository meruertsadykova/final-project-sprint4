package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    // Локатор поля вопроса
    private final By question1 = By.id("accordion__heading-0");
    private final By question2 = By.id("accordion__heading-4");
    // Локатор поля ответа
    private final By answer1 = By.xpath("//*[@aria-labelledby='accordion__heading-0']/p");
    private final By answer2 = By.xpath("//*[@aria-labelledby='accordion__heading-4']/p");
    // Локатор поля Вопросы
    private final By faqs = By.xpath("//*[contains(text(), 'Вопросы о важном')]");
    // Локатор кнопки заказать
    private final By orderBtn = By.xpath("//button[text()='Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    public void clickQuestion(int index) {
        switch (index) {
            case 1:
                wait.until(ExpectedConditions.visibilityOfElementLocated(question1));
                js.executeScript("arguments[0].click();", driver.findElement(question1));
                break;
            case 2:
                wait.until(ExpectedConditions.visibilityOfElementLocated(question2));
                js.executeScript("arguments[0].click();", driver.findElement(question2));
                break;
        }
    }

    public String getAnswer(int index) {
        switch (index) {
            case 1:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(answer1)).getText();
            case 2:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(answer2)).getText();
        }
        return null;
    }

    public void scrollToTheFAQs() {
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(faqs));
    }

    public void clickOrderBtn() {
        driver.findElement(orderBtn).click();
    }
}
