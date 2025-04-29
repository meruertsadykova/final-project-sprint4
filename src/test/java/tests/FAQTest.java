package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.MainPage;

public class FAQTest extends BaseTest {

    private final String expectedLabel1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private final String expectedLabel2 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";

    @Test
    public void faqTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.scrollToTheFAQs();
        mainPage.clickQuestion(1);
        Assert.assertEquals("Фактический текст не соответствует ожидаемому!", expectedLabel1, mainPage.getAnswer(1));

        mainPage.clickQuestion(2);
        Assert.assertEquals("Фактический текст не соответствует ожидаемому!", expectedLabel2, mainPage.getAnswer(2));
    }

}
