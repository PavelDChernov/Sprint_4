import constants.Color;
import constants.RentalPeriod;
import constants.WebDriverOption;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.junit.After;
import org.junit.Test;
import pageObjects.MainPageScooter;
import pageObjects.OrderPageScooter;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class OrderCreationChecks {
    private WebDriver driver;
    private final WebDriverOption webDriver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phone;
    private final String deliveryDate;
    private final RentalPeriod rentalPeriod;
    private final Color color;
    private final String comment;

    public OrderCreationChecks(WebDriverOption webDriver, String name, String surname, String address, String subwayStation, String phone, String deliveryDate, RentalPeriod rentalPeriod, Color color, String comment) {
        this.webDriver = webDriver;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {WebDriverOption.CHROME, "Акакий", "Башмачкин", "Москва, Рахмановский пер., д. 3", "Бульвар Адмирала Ушакова", "+79991841843", "30.09.2024", RentalPeriod.SEVEN_DAYS, Color.GREY, ""},
                {WebDriverOption.CHROME, "Хо", "Мин", "Москва", "ВДНХ", "88121841843", "01.10.2024", RentalPeriod.THREE_DAYS, Color.BLACK, "Не кантовать"},
        };
    }


    @Test
    public void checkOrderCreationUsingBottomButtonsOnlyRequiredFields() {
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

        assertTrue("BottomOrderButton is unclickable or missing", objMainPage.isBottomOrderButtonDisplayedAndEnabled());
        objMainPage.clickBottomOrderButton();

        OrderPageScooter objOrderPage = new OrderPageScooter(driver);

        objOrderPage.waitForWhomFormDisplayed();
        objOrderPage.fillForWhomForm(name, surname, address, subwayStation, phone);
        assertTrue("NextButton is unclickable or missing", objOrderPage.isNextButtonDisplayedAndEnabled());
        objOrderPage.clickNextButton();
        objOrderPage.waitForAboutRentFormDisplayed();
        objOrderPage.fillAboutRentFormRequiredOnly(deliveryDate, rentalPeriod);
        assertTrue("TopOrderButton is unclickable or missing", objOrderPage.isBottomOrderButtonDisplayedAndEnabled());
        objOrderPage.clickBottomOrderButton();
        objOrderPage.waitForPlaceOrderModalDisplayed();
        assertTrue("YesButton is unclickable or missing", objOrderPage.isYesButtonDisplayedAndEnabled());
        objOrderPage.clickYesButton();
        objOrderPage.waitForOrderCreatedModalDisplayed();
    }

    @Test
    public void checkOrderCreationUsingTopButtonsFullFields() {
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

        assertTrue("TopOrderButton is disabled or missing", objMainPage.isTopOrderButtonDisplayedAndEnabled());
        objMainPage.clickTopOrderButton();

        OrderPageScooter objOrderPage = new OrderPageScooter(driver);

        objOrderPage.waitForWhomFormDisplayed();
        objOrderPage.fillForWhomForm(name, surname, address, subwayStation, phone);
        assertTrue("NextButton is disabled or missing", objOrderPage.isNextButtonDisplayedAndEnabled());
        objOrderPage.clickNextButton();
        objOrderPage.waitForAboutRentFormDisplayed();
        objOrderPage.fillAboutRentFormFull(deliveryDate, rentalPeriod, color, comment);
        assertTrue("TopOrderButton is disabled or missing", objOrderPage.isTopOrderButtonDisplayedAndEnabled());
        objOrderPage.clickTopOrderButton();
        objOrderPage.waitForPlaceOrderModalDisplayed();
        assertTrue("YesButton is disabled or missing", objOrderPage.isYesButtonDisplayedAndEnabled());
        objOrderPage.clickYesButton();
        objOrderPage.waitForOrderCreatedModalDisplayed();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}