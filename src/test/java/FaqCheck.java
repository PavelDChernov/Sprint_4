import static org.junit.Assert.*;

import constants.FaqOptions;
import constants.UsedWebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageObjects.MainPageScooter;

import java.util.List;

@RunWith(Parameterized.class)
public class FaqCheck {
    private WebDriver driver;
    private final UsedWebDriver webDriver;
    private final List<String> driverOptions;

    public FaqCheck (UsedWebDriver webDriver, List<String> driverOptions) {
        this.webDriver = webDriver;
        this.driverOptions = driverOptions;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                { UsedWebDriver.CHROME, List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage" ) }
        };
    }

    @Before
    public void initWebDriver() {
        switch (webDriver) {
            case CHROME:
                ChromeOptions chromeDriverOptions = new ChromeOptions();
                chromeDriverOptions.addArguments(driverOptions);
                driver = new ChromeDriver(chromeDriverOptions);
                break;
            case FIREFOX:
                FirefoxOptions firefoxDriverOptions = new FirefoxOptions();
                firefoxDriverOptions.addArguments(driverOptions);
                driver = new FirefoxDriver(firefoxDriverOptions);
                break;
            default:
                break;
        }
    }

    @Test
    public void checkFaqPanelsOpenAndHaveAppropriateText() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPageScooter objMainPage = new MainPageScooter(driver);

        for (int i = 0; i < objMainPage.getNumberOfFaqPanels(); ++i) {
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
