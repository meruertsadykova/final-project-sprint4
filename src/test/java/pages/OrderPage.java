package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    // Локаторы полей для кого самокат
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By metroInputValue = By.xpath("//div[contains(@class,'select-search__select')]//div[text()='Бульвар Рокоссовского']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Локаторы полей про аренду
    private final By whenToDeliverField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentTime = By.xpath("//*[@class='Dropdown-arrow']");
    private final By rentTimeValue = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']");
    private final By colorCheckbox = By.id("black");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Локатор кнопки заказать внизу формы
    private final By orderBtnLoc = By.xpath("(//button[text()='Заказать'])[2]");
    // Локатор поля 'Хотите оформить заказ?'
    private final By orderConfirmLbl = By.xpath("//*[text()='Хотите оформить заказ?']");
    // Локатор кнопки да
    private final By yesBtn = By.xpath("//button[text()='Да']");
    // Локатор кнопки принять куки
    private final By acceptCookies = By.id("rcc-confirm-button");
    // Локатор текста успешного оформления заказа
    private final By orderSuccessLbl = By.xpath("//*[contains(text(), 'Заказ оформлен')]");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void fillOrderForm(String name, String surname, String address, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(metroInput).sendKeys("Бульвар Рокоссовского");
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroInputValue)).click();
    }

    public void clickNextBtn() {
        driver.findElement(nextButton).click();
    }

    public void fillRentDetails(String date, String text) {
        driver.findElement(whenToDeliverField).sendKeys(date);
        driver.findElement(rentTime).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentTimeValue)).click();
        driver.findElement(colorCheckbox).click();
        driver.findElement(commentField).sendKeys(text);
    }

    public void clickOrderBtn() {
        driver.findElement(orderBtnLoc).click();
    }

    public void clickYesBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmLbl));
        driver.findElement(yesBtn).click();
    }

    public void clickAcceptCookies() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookies)).click();
        } catch (TimeoutException e) {
            System.out.println("Cookie button was not present in time.");
        }
    }

    public boolean isOrderSuccessLblDisplayed() {
        return !driver.findElements(orderSuccessLbl).isEmpty();
    }
}
