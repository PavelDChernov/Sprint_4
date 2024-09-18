package constants;
import com.google.common.collect.ImmutableMap;

import java.util.Map;// Вопросы о важном

// класс, т.к. для создания просто Map в анонимном классе требуется подключить SDK 21
public class FaqOptions {
    public static final Map<String, String> FAQ = ImmutableMap.<String, String>builder()
            .put(   "Сколько это стоит? И как оплатить?",
                    "Сутки — 400 рублей. Оплата курьеру — наличными или картой.")
            .put(   "Хочу сразу несколько самокатов! Так можно?",
                    "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.")
            .put(   "Как рассчитывается время аренды?",
                    "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.")
            .put(   "Можно ли заказать самокат прямо на сегодня?",
                    "Только начиная с завтрашнего дня. Но скоро станем расторопнее.")
            .put(   "Можно ли продлить заказ или вернуть самокат раньше?",
                    "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.")
            .put(   "Вы привозите зарядку вместе с самокатом?",
                    "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.")
            .put(   "Можно ли отменить заказ?",
                    "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.")
            .put(   "Я жизу за МКАДом, привезёте?",
                    "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
            .build();
}