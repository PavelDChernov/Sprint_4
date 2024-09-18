import static org.junit.Assert.*;

import constants.FaqOptions;
import constants.WebDriverOption;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageObjects.MainPageScooter;

@RunWith(Parameterized.class)
public class FaqCheck {
    private WebDriver driver;
    private final WebDriverOption webDriver;

    public FaqCheck (WebDriverOption webDriver) {
        this.webDriver = webDriver;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                { WebDriverOption.CHROME }
        };
    }

    @Test
    public void checkFaqPanelsOpenAndHaveAppropriateText() {
        switch (webDriver) {
            case CHROME:
                ChromeOptions chromeDriverOptions = new ChromeOptions();
                chromeDriverOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeDriverOptions);
                break;
            case FIREFOX:
                FirefoxOptions firefoxDriverOptions = new FirefoxOptions();
                firefoxDriverOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxDriverOptions);
                break;
            default:
                break;
        }

        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPageScooter objMainPage = new MainPageScooter(driver);

        final int numberOfFaqPanels = objMainPage.getNumberOfFaqPanels();
        for (int i = 0; i < numberOfFaqPanels; ++i) {
            objMainPage.waitForFaqPanelButtonDisplayed(i);;
            assertTrue("faqPanelButton is disabled or missing", objMainPage.isFaqPanelButtonDisplayedAndEnabled(i));
            objMainPage.clickFaqPanelButton(i);
            objMainPage.waitForFaqPanelDisplayed(i);
            assertTrue("faqPanel is not visible", objMainPage.isFaqPanelDisplayed(i));
            assertEquals(FaqOptions.FAQ.get(objMainPage.getFaqPanelButtonText(i)), objMainPage.getFaqPanelText(i));
        }
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
