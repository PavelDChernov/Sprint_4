package pageObjects;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


// Локаторы и методы для главной страницы
public class MainPageScooter {
    private WebDriver driver;
    // локатор кнопки "Заказать" в хедере
    private By orderButtonTop = By.xpath(".//div[contains(@class,'Header')]//button[text()='Заказать']");
    // локатор кнопки "Заказать" внизу страницы
    private By orderButtonBottom = By.xpath(".//div[contains(@class,'Finish')]//button[text()='Заказать']");

    // конструктор класса
    public MainPageScooter(WebDriver driver){
        this.driver = driver;
    }

    // метод закрывает баннер про куки, если он есть
    public void acceptCookie() {
        List<WebElement> elements =  driver.findElements(By.xpath(".//div[contains(@class,'App_CookieConsent')]"));
        if (!elements.isEmpty()) {
            driver.findElement(By.id("rcc-confirm-button")).click();
        }
    }
    // метод проверяет доступность кнопки "Заказать" в хедере
    public boolean isTopOrderButtonDisplayedAndEnabled() {
        return driver.findElement(orderButtonTop).isDisplayed() && driver.findElement(orderButtonTop).isEnabled();
    }
    // метод кликает по кнопке "Заказать" в хедере
    public void clickTopOrderButton() {
        driver.findElement(orderButtonTop).click();
    }
    // метод проверяет доступность кнопки "Заказать" в хедере
    public boolean isBottomOrderButtonDisplayedAndEnabled() {
        return driver.findElement(orderButtonBottom).isDisplayed() && driver.findElement(orderButtonBottom).isEnabled();
    }
    // метод кликает по кнопке "Заказать" внизу страницы
    public void clickBottomOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonBottom));
        driver.findElement(orderButtonBottom).click();
    }

    // метод возвращает локатор на вкладку списка "Вопросы о важном"
    public By getFaqPanelButton(String faqPanelButtonText) {
        return By.xpath(".//div[text()='" + faqPanelButtonText + "']/..");
    }

    // метод ожидает отображение вкладки списка "Вопросы о важном"
    public void waitForFaqPanelButtonDisplayed(String faqPanelButtonText) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(getFaqPanelButton(faqPanelButtonText)));
    }

    // метод проверяет доступность вкладки списка "Вопросы о важном"
    public boolean isFaqPanelButtonDisplayedAndEnabled(String faqPanelButtonText) {
        return driver.findElement(getFaqPanelButton(faqPanelButtonText)).isDisplayed() && driver.findElement(getFaqPanelButton(faqPanelButtonText)).isEnabled();
    }

    // метод кликает по вкладке списка "Вопросы о важном"
    public void clickFaqPanelButton(String faqPanelButtonText) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(getFaqPanelButton(faqPanelButtonText)));
        driver.findElement(getFaqPanelButton(faqPanelButtonText)).click();
    }

    // метод возвращает локатор на открытую вкладку списка "Вопросы о важном"
    public By getFaqPanel(String faqPpanelText) {
        return By.xpath(".//p[text()='" + faqPpanelText + "']/..");
    }

    // метод ожидает отображение открытой вкладки списка "Вопросы о важном"
    public void waitForFaqPanelDisplayed(String faqPpanelText) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(getFaqPanel(faqPpanelText)));
    }

    // метод проверяет отображение открытой вкладки списка "Вопросы о важном"
    public boolean isFaqPanelDisplayed(String faqPpanelText) {
        return driver.findElement(getFaqPanel(faqPpanelText)).isDisplayed();
    }
}
