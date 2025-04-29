package tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import pages.MainPage;
import pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String phone;

    public OrderTest(String name, String surname, String address, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }

    // Параметризация, чтобы тест можно было запускать с разными входными данными
    @Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                { "Иван", "Иванов", "Москва, ул. Пушкина", "+79998887766" },
                { "Петр", "Петров", "Санкт-Петербург, Невский пр.", "+78887776655" },
                { "Анна", "Смирнова", "Екатеринбург, ул. Мира", "+75556667788" }
        });
    }

    @Test
    public void orderTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderBtn();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, surname, address, phone);
        orderPage.clickNextBtn();

        orderPage.fillRentDetails("30", "Autotest");
        orderPage.clickAcceptCookies();
        orderPage.clickOrderBtn();
        orderPage.clickYesBtn();

        Assert.assertTrue("Всплывающее окно с сообщением об успешном созданий заказа не появилось!",
                orderPage.isOrderSuccessLblDisplayed());
    }

}
