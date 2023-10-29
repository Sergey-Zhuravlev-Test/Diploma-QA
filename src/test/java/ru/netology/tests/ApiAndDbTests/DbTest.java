package ru.netology.tests.ApiAndDbTests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.DBHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.PaymentPage;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;

public class DbTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        DBHelper.clearDB();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("1. Проверка корректности внесенных данных в БД через PAYMENT GATE карты со статусом APPROVED")
    void checkBdForEnteredDataApprovedPayment() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        val startPage = new StartPage();
        startPage.buy();
        val paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        assertEquals("APPROVED", DBHelper.getPaymentQuery());
    }

    @Test
    @DisplayName("2. Проверка корректности внесенных данных в БД через PAYMENT GATE карты со статусом DECLINED")
    void checkBdForEnteredDataDeclinedPayment() {
        CardInfo cardInfo = new CardInfo(getDeclinedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        val startPage = new StartPage();
        startPage.buy();
        val paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        assertEquals("DECLINED", DBHelper.getPaymentQuery());
    }

    @Test
    @DisplayName("3. Проверка корректности внесенных данных в БД через CREDIT GATE карты со статусом APPROVED")
    void checkBdForEnteredDataApprovedCredit() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        val startPage = new StartPage();
        startPage.buyInCredit();
        val creditPage = new CreditPage();
        creditPage.fillingCreditForm(cardInfo);
        assertEquals("APPROVED", DBHelper.getCreditQuery());
    }

    @Test
    @DisplayName("4. Проверка корректности внесенных данных в БД через CREDIT GATE карты со статусом DECLINED")
    void checkBdForEnteredDataDeclinedCredit() {
        CardInfo cardInfo = new CardInfo(getDeclinedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        val startPage = new StartPage();
        startPage.buy();
        val paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        assertEquals("DECLINED", DBHelper.getCreditQuery());
    }
}
