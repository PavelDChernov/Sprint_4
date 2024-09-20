import constants.Color;
import constants.RentalPeriod;
import constants.UsedWebDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import pageObjects.MainPageScooter;
import pageObjects.OrderPageScooter;
import service.AbstractAutoTest;

import java.util.List;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class OrderCreationChecks extends AbstractAutoTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phone;
    private final String deliveryDate;
    private final RentalPeriod rentalPeriod;
    private final Color color;
    private final String comment;

    public OrderCreationChecks( UsedWebDriver webDriver, List<String> driverOptions, String name, String surname,
                                String address, String subwayStation, String phone, String deliveryDate,
                                RentalPeriod rentalPeriod, Color color, String comment) {
        super(webDriver,driverOptions);
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
                {
                    UsedWebDriver.CHROME,
                    List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                    "Акакий",
                    "Башмачкин",
                    "Москва, Рахмановский пер., д. 3",
                    "Бульвар Адмирала Ушакова",
                    "+79991841843",
                    "30.09.2024",
                    RentalPeriod.SEVEN_DAYS, Color.GRAY,
                    ""
                },
                {   UsedWebDriver.CHROME,
                    List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage"),
                    "Хо",
                    "Мин",
                    "Москва",
                    "ВДНХ",
                    "88121841843",
                    "01.10.2024",
                    RentalPeriod.THREE_DAYS,
                    Color.BLACK,
                    "Не кантовать"
                }
        };
    }

    @Test
    public void checkOrderCreationUsingBottomButtonsOnlyRequiredFields() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPageScooter objMainPage = new MainPageScooter(driver);

        assertTrue("BottomOrderButton is disabled or missing", objMainPage.isBottomOrderButtonDisplayedAndEnabled());
        objMainPage.clickBottomOrderButton();

        OrderPageScooter objOrderPage = new OrderPageScooter(driver);

        objOrderPage.waitForWhomFormDisplayed();
        objOrderPage.fillForWhomForm(name, surname, address, subwayStation, phone);
        assertTrue("NextButton is disabled or missing", objOrderPage.isNextButtonDisplayedAndEnabled());
        objOrderPage.clickNextButton();
        objOrderPage.waitForAboutRentFormDisplayed();
        objOrderPage.fillAboutRentFormRequiredOnly(deliveryDate, rentalPeriod);
        assertTrue("TopOrderButton is disabled or missing", objOrderPage.isBottomOrderButtonDisplayedAndEnabled());
        objOrderPage.clickBottomOrderButton();
        objOrderPage.waitForPlaceOrderModalDisplayed();
        assertTrue("YesButton is disabled or missing", objOrderPage.isYesButtonDisplayedAndEnabled());
        objOrderPage.clickYesButton();
        objOrderPage.waitForOrderCreatedModalDisplayed();
    }

    @Test
    public void checkOrderCreationUsingTopButtonsFullFields() {
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
}