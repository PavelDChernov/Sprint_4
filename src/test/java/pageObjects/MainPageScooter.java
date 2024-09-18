package pageObjects;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


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

    // метод получения числа вкладок списка "Вопросы о важном"
    public int getNumberOfFaqPanels() {
        return driver.findElements(By.className("accordion__item")).size();
    }

    // метод возвращает локатор на вкладку списка "Вопросы о важном"
    public By getFaqPanelButton(int i) {
        return By.id("accordion__heading-" + i);
    }

    // метод ожидает отображение вкладки списка "Вопросы о важном"
    public void waitForFaqPanelButtonDisplayed(int i) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(getFaqPanelButton(i)));
    }

    // метод проверяет доступность вкладки списка "Вопросы о важном"
    public boolean isFaqPanelButtonDisplayedAndEnabled(int i) {
        return driver.findElement(getFaqPanelButton(i)).isDisplayed() && driver.findElement(getFaqPanelButton(i)).isEnabled();
    }

    // метод кликает по вкладке списка "Вопросы о важном"
    public void clickFaqPanelButton(int i) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(getFaqPanelButton(i)));
        driver.findElement(getFaqPanelButton(i)).click();
    }

    // метод возвращает текст вкладки списка "Вопросы о важном"
    public String getFaqPanelButtonText(int i) {
        return driver.findElement(getFaqPanelButton(i)).getText();
    }

    // метод возвращает локатор на открытую вкладку списка "Вопросы о важном"
    public By getFaqPanel(int i) {
        return By.id("accordion__panel-" + i);
    }

    // метод ожидает отображение открытой вкладки списка "Вопросы о важном"
    public void waitForFaqPanelDisplayed(int i) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(getFaqPanel(i)));
    }

    // метод проверяет отображение открытой вкладки списка "Вопросы о важном"
    public boolean isFaqPanelDisplayed(int i) {
        return driver.findElement(getFaqPanel(i)).isDisplayed();
    }

    // метод возвращает текст открытой вкладки списка "Вопросы о важном"
    public String getFaqPanelText(int i) {
        return driver.findElement(getFaqPanel(i)).getText();
    }
}
