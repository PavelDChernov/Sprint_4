import static org.junit.Assert.*;
import constants.UsedWebDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.MainPageScooter;
import service.AbstractAutoTest;

import java.util.List;

@RunWith(Parameterized.class)
public class FaqPanelContentsTest extends AbstractAutoTest {
    private final String faqPanelButtonText;
    private final String faqPanelText;

    public FaqPanelContentsTest(UsedWebDriver webDriver, List<String> driverOptions, String faqPanelButtonText, String faqPanelText) {
        super(webDriver,driverOptions);
        this.faqPanelButtonText = faqPanelButtonText;
        this.faqPanelText = faqPanelText;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {
                        UsedWebDriver.CHROME,
                        List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                        "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                },
                {
                        UsedWebDriver.CHROME,
                        List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                        "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
                },
                {
                        UsedWebDriver.CHROME,
                        List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                        "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."
                },
                {
                        UsedWebDriver.CHROME,
                        List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                        "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
                },
                {
                        UsedWebDriver.CHROME,
                        List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                        "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."
                },
                {
                        UsedWebDriver.CHROME,
                        List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                        "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."
                },
                {
                        UsedWebDriver.CHROME,
                        List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                        "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."
                },
                {
                        UsedWebDriver.CHROME,
                        List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                        "Я жизу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."
                }
        };
    }

    @Test
    public void checkFaqPanelsOpenAndHaveAppropriateText() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPageScooter objMainPage = new MainPageScooter(driver);

        objMainPage.acceptCookie();
        objMainPage.waitForFaqPanelButtonDisplayed(faqPanelButtonText);;
        assertTrue(faqPanelButtonText + " faqPanelButton is disabled or missing", objMainPage.isFaqPanelButtonDisplayedAndEnabled(faqPanelButtonText));
        objMainPage.clickFaqPanelButton(faqPanelButtonText);
        objMainPage.waitForFaqPanelDisplayed(faqPanelText);
        assertTrue(faqPanelText + " faqPanel is not visible", objMainPage.isFaqPanelDisplayed(faqPanelText));
    }
}
