package service;

import constants.UsedWebDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.List;


public abstract class AbstractAutoTest {
    protected WebDriver driver;
    private final UsedWebDriver webDriver;
    private final List<String> driverOptions;

    public AbstractAutoTest(UsedWebDriver webDriver, List<String> driverOptions) {
        this.webDriver = webDriver;
        this.driverOptions = driverOptions;
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

    @After
    public void teardown() {
        driver.quit();
    }
}
