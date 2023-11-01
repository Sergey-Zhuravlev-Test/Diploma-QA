package ru.netology.tests.UiTests;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.DBHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.Notifications;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.*;


public class PositiveCreditTest {
    private static final String sutURL = System.getProperty("sut.url");
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setup() {
        open(sutURL);
        DBHelper.clearDB();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("1. Оплата по карте с вводом валидных значений на вкладке “Купить в кредит” и картой со статусом APPROVED")
    void shouldSuccessfulBuyWithValidValuesApproved() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buyInCredit();
        var creditPage = new CreditPage();
        creditPage.fillingCreditForm(cardInfo);
        var notification = new Notifications();
        notification.successNotification();
    }
    @Test
    @DisplayName("2. Оплата по карте с вводом валидных значений на вкладке “Купить в кредит” и картой со статусом DECLINED")
    void shouldSuccessfulBuyWithValidValuesDeclined() {
        CardInfo cardInfo = new CardInfo(getDeclinedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buyInCredit();
        var creditPage = new CreditPage();
        creditPage.fillingCreditForm(cardInfo);
        var notification = new Notifications();
        notification.successNotification();
    }
    @Test
    @DisplayName("3. Оплата по карте с вводом валидных значений по нижней границе срока действия карты на вкладке “Купить в кредит” и картой со статусом APPROVED")
    void shouldSuccessfulBuyWithValidLowerLimitValuesApproved() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buyInCredit();
        var creditPage = new CreditPage();
        creditPage.fillingCreditForm(cardInfo);
        var notification = new Notifications();
        notification.successNotification();
    }
    @Test
    @DisplayName("4. Оплата по карте с вводом валидных значений по верхней границе срока действия карты на вкладке “Купить в кредит” и картой со статусом APPROVED")
    void shouldSuccessfulBuyWithValidUpperLimitValuesApproved() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getLastMonth(), getUpperLimitYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buyInCredit();
        var creditPage = new CreditPage();
        creditPage.fillingCreditForm(cardInfo);
        var notification = new Notifications();
        notification.successNotification();
    }
    @Test
    @DisplayName("5. Оплата по карте с вводом валидных значений и проверкой поля “Владелец” на обработку заглавных и строчных букв на вкладке “Купить в кредит” и картой со статусом APPROVED")
    void shouldSuccessfulBuyWithValidCamelCaseHolderValuesApproved() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getValidCamelCaseHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buyInCredit();
        var creditPage = new CreditPage();
        creditPage.fillingCreditForm(cardInfo);
        var notification = new Notifications();
        notification.successNotification();
    }
    @Test
    @DisplayName("6. Оплата по карте с вводом валидных значений и проверкой поля “Владелец” на обработку одного из вариантов двойной фамилии и имени на вкладке “Купить в кредит” и картой со статусом APPROVED")
    void shouldSuccessfulBuyWithValidFullHolderValuesApproved() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getFullValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buyInCredit();
        var creditPage = new CreditPage();
        creditPage.fillingCreditForm(cardInfo);
        var notification = new Notifications();
        notification.successNotification();
    }
}
