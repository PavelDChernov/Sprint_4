package pageObjects;

import constants.Color;
import constants.RentalPeriod;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// Локаторы и методы для страницы оформления заказа форма "Для кого самокат"
public class OrderPageScooter {
    private WebDriver driver;
    // локатор заголовка формы "Для кого самокат"
    private By forWhomFormTitle = By.xpath(".//div[text()='Для кого самокат']");
    //  локатор поля "Имя"
    private By nameField = By.xpath(".//input[contains(@placeholder,'Имя')]");
    //  локатор поля "Фамилия"
    private By surnameField = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    //  локатор поля "Адрес"
    private By addressField = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    //  локатор поля "Станция метро"
    private By subwayStationField = By.xpath(".//input[contains(@placeholder,'Станция')]");
    //  локатор поля "Телефон"
    private By phoneField = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    // локатор кнопки "Далее"
    private By nextButton = By.xpath(".//button[text()='Далее']");
    // локатор заголовка формы "Про аренду"
    private By aboutRentFormTitle = By.xpath(".//div[text()='Про аренду']");
    //  локатор поля "Когда привезти"
    private By deliveryDateField = By.xpath(".//input[contains(@placeholder,'Когда')]");
    //  локатор поля "Срок аренды"
    private By rentalPeriodField = By.xpath(".//div[contains(text(),'Срок')]");;
    //  локатор чекбокса "Цвет самоката - Черный"
    private By blackColorCheckbox = By.id("black");
    //  локатор чекбокса "Цвет самоката - Серый"
    private By grayColorCheckbox = By.id("grey");
    //  локатор поля "Комментарий"
    private By commentField = By.xpath(".//input[contains(@placeholder,'Комментарий')]");
    // локатор кнопки "Заказать" в хедере
    private By orderButtonTop = By.xpath(".//div[contains(@class,'Header')]//button[text()='Заказать']");
    // локатор кнопки "Заказать" внизу страницы
    private By orderButtonBottom = By.xpath(".//div[contains(@class,'Order_Buttons')]//button[text()='Заказать']");
    // локатор кнопки "Да"
    private By yesButton = By.xpath(".//button[text()='Да']");
    // локатор модального окна "Хотите оформить заказ?"
    private By placeOrderModalTitle = By.xpath(".//div[text()='Хотите оформить заказ?']");
    // локатор модального окна "Заказ оформлен"
    private By orderCreatedModalTitle = By.xpath(".//div[text()='Заказ оформлен']");

    // конструктор класса
    public OrderPageScooter(WebDriver driver){
        this.driver = driver;
    }

    // метод заполняет поле "Имя"
    public void fillName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }
    // метод заполняет поле "Фамилия"
    public void fillSurname(String surname) {
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
    }
    // метод заполняет поле "Адрес"
    public void fillAddress(String address) {
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }
    // метод заполняет поле "Станция метро"
    public void fillSubwayStation(String subwayStation) {
        driver.findElement(subwayStationField).clear();
        driver.findElement(subwayStationField).sendKeys(subwayStation);
        driver.findElement(By.xpath(".//*[@class='select-search__select']//*[contains(text(),'" + subwayStation + "')]")).click();
    }
    // метод заполняет поле "Телефон"
    public void fillPhone(String phone) {
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
    }
    //  метод проверяет доступность кнопки "Далее"
    public boolean isNextButtonDisplayedAndEnabled() {
        return driver.findElement(nextButton).isDisplayed() && driver.findElement(nextButton).isEnabled();
    }
    // метод кликает по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    // метод заполняет поле "Когда привезти"
    public void fillDeliveryDate(String deliveryDate) {
        driver.findElement(deliveryDateField).clear();
        driver.findElement(deliveryDateField).sendKeys(deliveryDate);
        driver.findElement(deliveryDateField).sendKeys(Keys.RETURN);
    }
    // метод заполняет поле "Срок аренды": кликает по полю "Срок аренды", прокручивает список и кликает по переданной опции
    public void fillRentalPeriod(RentalPeriod period) {
        driver.findElement(rentalPeriodField).click();
        List<WebElement> rentalPeriodOptions =  driver.findElements(By.xpath(".//div[@class='Dropdown-menu']/*"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", rentalPeriodOptions.get(period.ordinal()));
        rentalPeriodOptions.get(period.ordinal()).click();
    }
    // метод заполняет поле "Комментарий"
    public void fillComment(String comment) {
        driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(comment);
    }
    // метод переключает чекбокс "Цвет самоката - Черный"
    public void toggleBlackColorCheckbox() {
        driver.findElement(blackColorCheckbox).click();
    }
    // метод переключает чекбокс "Цвет самоката - серый"
    public void toggleGrayColorCheckbox() {
        driver.findElement(grayColorCheckbox).click();
    }
    // метод устанавливает "Цвет самоката"
    public void fillColor(Color color) {
        switch (color) {
            case BLACK:
                if (!driver.findElement(blackColorCheckbox).isSelected()) {
                    toggleBlackColorCheckbox();
                }
                break;
            case GRAY:
                if (!driver.findElement(grayColorCheckbox).isSelected()) {
                    toggleGrayColorCheckbox();
                }
                break;
            default:
                break;
        }
    }
    //  метод проверяет доступность кнопки "Заказать" в хедере
    public boolean isTopOrderButtonDisplayedAndEnabled() {
        return driver.findElement(orderButtonTop).isDisplayed() && driver.findElement(orderButtonTop).isEnabled();
    }
    // метод кликает по кнопке "Заказать" в хедере
    public void clickTopOrderButton() {
        driver.findElement(orderButtonTop).click();
    }
    //  метод проверяет доступность кнопки "Заказать" в форме
    public boolean isBottomOrderButtonDisplayedAndEnabled() {
        return driver.findElement(orderButtonBottom).isDisplayed() && driver.findElement(orderButtonBottom).isEnabled();
    }
    // метод кликает по кнопке "Заказать" в форме
    public void clickBottomOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonBottom));
        driver.findElement(orderButtonBottom).click();
    }
    //  метод проверяет доступность кнопки "Да"
    public boolean isYesButtonDisplayedAndEnabled() {
        return driver.findElement(yesButton).isDisplayed() && driver.findElement(yesButton).isEnabled();
    }
    // метод кликает по кнопке "Да"
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }
    // метод ожидает отображение формы "Для кого самокат"
    public void waitForWhomFormDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(forWhomFormTitle) != null));
    }
    // метод ожидает отображение формы "Про аренду"
    public void waitForAboutRentFormDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(aboutRentFormTitle) != null));
    }
    // метод ожидает отображение окна "Хотите оформить заказ?"
    public void waitForPlaceOrderModalDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(placeOrderModalTitle) != null));
    }
    // метод ожидает отображение окна "Заказ оформлен"
    public void waitForOrderCreatedModalDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(orderCreatedModalTitle) != null));
    }
    // метод заполняет форму "Для кого самокат"
    public void fillForWhomForm(String name, String surname, String address, String subwayStation, String phone) {
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        fillSubwayStation(subwayStation);
        fillPhone(phone);
    }

    // метод заполняет только обязательные поля формы "Про аренду"
    public void fillAboutRentFormRequiredOnly(String deliveryDate, RentalPeriod rentalPeriod) {
        fillDeliveryDate(deliveryDate);
        fillRentalPeriod(rentalPeriod);
    }

    // метод заполняет все поля формы "Про аренду"
    public void fillAboutRentFormFull(String deliveryDate, RentalPeriod rentalPeriod, Color color, String comment) {
        fillDeliveryDate(deliveryDate);
        fillRentalPeriod(rentalPeriod);
        fillColor(color);
        fillComment(comment);
    }
}
