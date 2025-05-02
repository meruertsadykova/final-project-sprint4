package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {

    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    // Локатор кнопки принять куки
    private final By acceptCookies = By.id("rcc-confirm-button");
    // Локаторы поля вопросов
    private final By issueTitle = By.xpath("//*[@class='accordion__button']");
    // Локаторы поля ответов
    private final By issueAnswer = By.xpath("//*[@class='accordion__panel']");

    private final By costAnswer = By.xpath("//*[@aria-labelledby='accordion__heading-0']/p");
    private final By multipleScooterAnswer = By.xpath("//*[@aria-labelledby='accordion__heading-1']/p");
    private final By rentTimeAnswer = By.xpath("//*[@aria-labelledby='accordion__heading-2']/p");
    private final By rentTodayAnswer = By.xpath("//*[@aria-labelledby='accordion__heading-3']/p");
    private final By prolongAnswer = By.xpath("//*[@aria-labelledby='accordion__heading-4']/p");
    private final By chargerAnswer = By.xpath("//*[@aria-labelledby='accordion__heading-5']/p");
    private final By cancelAnswer = By.xpath("//*[@aria-labelledby='accordion__heading-6']/p");
    private final By toMKADAnswer = By.xpath("//*[@aria-labelledby='accordion__heading-7']/p");

    // Локатор поля Вопросы
    private final By faqs = By.xpath("//*[contains(text(), 'Вопросы о важном')]");
    // Локатор кнопки заказать
    private final By orderUpperBtn = By.xpath("(//button[text()='Заказать'])[1]");
    private final By orderBottomBtn = By.xpath("(//button[text()='Заказать'])[2]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickAcceptCookies() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookies)).click();
        } catch (TimeoutException e) {
            System.out.println("Cookie button was not present in time." + e.getMessage());
        }
    }

    public void clickIssueTitleByIndex(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(issueTitle));
        js.executeScript("arguments[0].click();", driver.findElements(issueTitle).get(index));
    }

    public String getIssueTitleTextByIndex(int index) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(issueTitle)).get(index).getText();
    }

    public String getIssueAnswerByIndex(int index) {
        switch (index) {
            case 0:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(costAnswer)).getText();
            case 1:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(multipleScooterAnswer)).getText();
            case 2:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(rentTimeAnswer)).getText();
            case 3:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(rentTodayAnswer)).getText();
            case 4:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(prolongAnswer)).getText();
            case 5:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(chargerAnswer)).getText();
            case 6:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(cancelAnswer)).getText();
            case 7:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(toMKADAnswer)).getText();
        }
        throw new RuntimeException("Index is out of range! Use between [0-7] to get correct answer.");
    }

    public void scrollToTheFAQs() {
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(faqs));
    }

    public void clickUpperOrderBtn() {
        driver.findElement(orderUpperBtn).click();
    }

    public void clickBottomOrderBtn() {
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(orderBottomBtn));
        driver.findElement(orderBottomBtn).click();
    }
}
